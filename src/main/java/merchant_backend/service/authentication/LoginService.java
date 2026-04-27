package merchant_backend.service.authentication;

import lombok.RequiredArgsConstructor;
import merchant_backend.config.security.jwt.JwtUtil;
import merchant_backend.dto.Authentication.JwtResponse;
import merchant_backend.dto.Authentication.LoginRequest;
import merchant_backend.entities.RefreshToken;
import merchant_backend.repository.UsersRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;


@Service
@RequiredArgsConstructor
public class LoginService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final RefreshTokenService refreshTokenService;
    private final UsersRepository usersRepository;

// Import jakarta.servlet.http.Cookie and HttpServletResponse
public JwtResponse loginUser(LoginRequest request, HttpServletResponse response) {
    Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.username(), request.password()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    merchant_backend.entities.Users user = (merchant_backend.entities.Users) authentication.getPrincipal();

    String jwt = jwtUtil.generateAccessToken(user.getUsername(), user.getId(), user.isLoggedInONCE());
    RefreshToken refreshToken = refreshTokenService.createRefreshToken(request.username());

    // --- NEW LOGIC: Put the Refresh Token in a Cookie ---
    Cookie cookie = new Cookie("refresh_token", refreshToken.getToken());
    cookie.setHttpOnly(true);   // JavaScript cannot see this!
    cookie.setSecure(false);     // Set to true in production (HTTPS)
    cookie.setPath("/");        // Available for all API calls
    cookie.setMaxAge(24 * 60 * 60); // 1 day
    response.addCookie(cookie);
    // ----------------------------------------------------

    // Return only the Access Token and Username in the JSON body
    return new JwtResponse(jwt, null, request.username());
}
}
