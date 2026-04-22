package merchant_backend.repository;

import merchant_backend.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface UsersRepository extends JpaRepository<Users, Long> {

    // Spring will automatically map this to the 'userName' field in your Entity
    Optional<Users> findByUserName(String userName);

    boolean existsByUserNameIn(List<String> userNames);
}