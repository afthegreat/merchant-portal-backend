package merchant_backend.service.authentication;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import merchant_backend.entities.Users;
import merchant_backend.repository.UsersRepository;
import merchant_backend.service.user.GetLoggedInUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LogoutService {
    private final RefreshTokenService refreshTokenService;
    private final GetLoggedInUser getLoggedInUser;
        public void logoutUser(HttpServletResponse response){
    Users user= getLoggedInUser.getLoggedInUser();//get the logged-in user object
            refreshTokenService.deleteByUserId(user.getId());

            SecurityContextHolder.clearContext();//clear the security context that holds logged-in user

            Cookie cookie= new Cookie("refresh_token", null);
            cookie.setHttpOnly(true);
            cookie.setSecure(false);
            cookie.setPath("/");
            cookie.setMaxAge(0);
            response.addCookie(cookie);



    }
}
