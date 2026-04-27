package merchant_backend.dto.Authentication;


public record TokenRefreshResponse(
    String accessToken,
    String refreshToken,
    String type
) {
    // Constructor for when we only want to send the new Access Token 
    // but keep the Refresh Token in the cookie
    public TokenRefreshResponse(String accessToken, String refreshToken) {
        this(accessToken, refreshToken, "Bearer");
    }
}
