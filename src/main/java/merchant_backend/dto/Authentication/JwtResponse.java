package merchant_backend.dto.Authentication;

public record JwtResponse(
    String accessToken,
    String refreshToken,
    String username,
    String type
) {
    // Secondary constructor for the Service to use
    public JwtResponse(String accessToken, String refreshToken, String username) {
        this(accessToken, refreshToken, username, "Bearer");
    }
}
