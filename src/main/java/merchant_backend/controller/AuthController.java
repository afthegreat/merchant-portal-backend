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
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthenticationManager authenticationManager;
	private final JwtUtil jwtUtil;
	private final RefreshTokenService refreshTokenService;
	private final LogoutService logoutService ;
	private final LoginService loginService;

	@PostMapping("/login")
	public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
			JwtResponse response= loginService.loginUser(loginRequest);
			return ResponseEntity.ok(response);
	}

	@PostMapping("/refresh")
	public ResponseEntity<?> refreshToken(@Valid @RequestBody TokenRefreshRequest request) {
		String requestRefreshToken = request.refreshToken();

		return refreshTokenService.findByToken(requestRefreshToken)
				.map(refreshTokenService::verifyExpiration)
				.map(RefreshToken::getUser)
				.map(user -> {
					String token = jwtUtil.generateAccessToken(user.getUsername());
					return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
				})
				.orElseThrow(() -> new RuntimeException("Refresh token is not in database!"));
	}

	@PostMapping("/logout")
	public ResponseEntity<String> logoutUser(){
		String response= logoutService.logoutUser();
		return ResponseEntity.ok(response);
	}
}