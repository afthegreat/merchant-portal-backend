package merchant_backend.service.authentication;

import lombok.RequiredArgsConstructor;
import merchant_backend.entities.Users;
import merchant_backend.repository.UsersRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LogoutService {
    private final RefreshTokenService refreshTokenService;
    private final UsersRepository usersRepository;

    public String logoutUser(Long userId){
        Optional<Users> user= usersRepository.findById(userId);

        refreshTokenService.deleteByUserId(user.get().getId());
        return "Logged out Successfully";

    }
}
