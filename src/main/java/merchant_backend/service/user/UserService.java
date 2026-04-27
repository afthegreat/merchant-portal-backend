package merchant_backend.service.user;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import merchant_backend.dto.Users.RegisterUser;
import merchant_backend.dto.Users.UserResponse;
import merchant_backend.entities.Users;
import merchant_backend.repository.UsersRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    @Transactional
    public String registerNewUser(List<RegisterUser> requests){
    Set<String> incomingUserNames=new HashSet<>();
    List<String> userNames= requests.stream()
            .map(RegisterUser::getUserName)
            .toList();
    boolean isFound= usersRepository.existsByUserNameIn(userNames);
    if (isFound){
        throw new RuntimeException("user already registered");
    }
    List<Users> usersToSave = requests.stream()
            .map(req -> {
                Users user = new Users();
                user.setUserName(req.getUserName());
                user.setPassword(passwordEncoder.encode(req.getPassword())); // Consider passwordEncoder.encode(req.getPassword()) here
                return user;
            })
            .collect(Collectors.toList());
    usersRepository.saveAll(usersToSave);
    return "users saved successfully";
    }
    // Must be public to be accessed by the Controller
    public List<UserResponse> getAllUsers() {
        List<Users> users = usersRepository.findAll();

        // Map the Entity list to a DTO list
        return users.stream()
                .map(user -> new UserResponse(user.getId(), user.getUsername()))
                .collect(Collectors.toList());
    }
    // Inside UsersService.java
    @Transactional
    public void updateLoggedInOnce(merchant_backend.entities.Users user) {
        if (!user.isLoggedInONCE()) {
            user.setLoggedInONCE(true);
            usersRepository.save(user);
        }
    }

    }
