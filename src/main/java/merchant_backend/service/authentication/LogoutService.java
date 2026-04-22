package merchant_backend.service.authentication;

import lombok.RequiredArgsConstructor;
import merchant_backend.entities.Users;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogoutService {
    private final RefreshTokenService refreshTokenService;

    public String logoutUser(){
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        Users user= (Users) auth.getPrincipal();

        refreshTokenService.deleteByUserId(user.getId());
        return "Logged out Successfully";

    }
}
