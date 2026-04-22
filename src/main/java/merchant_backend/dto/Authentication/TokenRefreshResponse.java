package merchant_backend.dto.Authentication;

public record TokenRefreshResponse(
        String accessToken,
        String refreshToken,
        String type
) {
    public TokenRefreshResponse(String accessToken, String refreshToken) {
        this(accessToken, refreshToken, "Bearer");
    }
}
