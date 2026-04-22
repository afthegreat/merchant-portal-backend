package merchant_backend.dto.Authentication;

public record JwtResponse(
		String accessToken,
		String refreshToken,
		String username,
		String type // Usually "Bearer"
) {
	public JwtResponse(String accessToken, String refreshToken, String username) {
		this(accessToken, refreshToken, username, "Bearer");
	}
}