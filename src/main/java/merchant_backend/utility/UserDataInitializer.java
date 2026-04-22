package merchant_backend.utility;

import lombok.RequiredArgsConstructor;
import merchant_backend.entities.Users;
import merchant_backend.repository.UsersRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.List;


@Component
@RequiredArgsConstructor
public class UserDataInitializer implements CommandLineRunner {

    private final UsersRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            String encodedPassword = passwordEncoder.encode("af1532");
            Users user1 = new Users();
            user1.setUserName("abel ferade");
            user1.setPassword(encodedPassword);

            Users user2 = new Users();
            user2.setUserName("retail");
            user2.setPassword(encodedPassword);

            Users user3 = new Users();
            user3.setUserName("utility");
            user3.setPassword(encodedPassword);

            Users user4 = new Users();
            user4.setUserName("admin");
            user4.setPassword(encodedPassword);

            Users user5 = new Users();
            user5.setUserName("test");
            user5.setPassword(encodedPassword);


            // Save all at once
            userRepository.saveAll(List.of(user1, user2, user3, user4));

            System.out.println("✅ Successfully initialized " + userRepository.count() + " sample users.");
        } else {
            System.out.println("ℹ️ Users already exist in DB, skipping initialization.");
        }
    }
}