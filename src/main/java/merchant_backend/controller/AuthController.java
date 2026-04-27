package merchant_backend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import merchant_backend.config.security.jwt.JwtUtil;
import merchant_backend.dto.Authentication.*;
import merchant_backend.entities.RefreshToken;
import merchant_backend.entities.Users;
import merchant_backend.service.authentication.LoginService;
import merchant_backend.service.authentication.LogoutService;
import merchant_backend.service.authentication.RefreshTokenService;
import merchant_backend.service.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthenticationManager authenticationManager;
	private final JwtUtil jwtUtil;
	private final RefreshTokenService refreshTokenService;
	private final LogoutService logoutService ;
	private final LoginService loginService;
	private final UserService userService;

	@PostMapping("/login")
	public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, HttpServletResponse response) {
			JwtResponse response1= loginService.loginUser(loginRequest,response);
		merchant_backend.entities.Users user = (merchant_backend.entities.Users)
				SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			userService.updateLoggedInOnce(user);
			return ResponseEntity.ok(response1);
	}

@PostMapping("/refresh")
public ResponseEntity<?> refreshToken(@CookieValue(name = "refresh_token") String requestRefreshToken) {
    return refreshTokenService.findByToken(requestRefreshToken)
            .map(refreshTokenService::verifyExpiration)
            .map(RefreshToken::getUser)
            .map(user -> {
                String token = jwtUtil.generateAccessToken(user.getUsername(), user.getId(), user.isLoggedInONCE());
                // Pass BOTH strings to match the constructor we fixed above
                return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
            })
            .orElseThrow(() -> new RuntimeException("Refresh token is not in database!"));
}

	@PostMapping("/logout/{userId}")
	public ResponseEntity<String> logoutUser(@PathVariable Long userId){
		String response= logoutService.logoutUser(userId);
		return ResponseEntity.ok(response);
	}
}
