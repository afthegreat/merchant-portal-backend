package merchant_backend.repository;

import merchant_backend.entities.RefreshToken;
import merchant_backend.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional <RefreshToken> findByToken(String token);

    @Modifying
    int deleteByUser(Users user);
}
