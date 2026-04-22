package merchant_backend.service.authentication;

import lombok.RequiredArgsConstructor;
import merchant_backend.config.security.jwt.JwtUtil;
import merchant_backend.dto.Authentication.JwtResponse;
import merchant_backend.dto.Authentication.LoginRequest;
import merchant_backend.entities.RefreshToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final RefreshTokenService refreshTokenService;

    public JwtResponse loginUser(LoginRequest request){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Generate short-lived Access Token
        String jwt = jwtUtil.generateAccessToken(request.username());

        // Create and save Refresh Token in DB
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(request.username());
        return new JwtResponse(jwt, refreshToken.getToken(), request.username());
    }
}
