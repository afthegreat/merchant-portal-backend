package merchant_backend.controller;

import lombok.RequiredArgsConstructor;
import merchant_backend.dto.Users.RegisterUser;
import merchant_backend.dto.Users.UserResponse;
import merchant_backend.entities.Users;
import merchant_backend.service.user.GetLoggedInUser;
import merchant_backend.service.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final GetLoggedInUser getLoggedInUser;

    @PostMapping("/regiser")
    public ResponseEntity<String> registerUser(@RequestBody List<RegisterUser> request){
        String response=userService.registerNewUser(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getallusers")
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        List<UserResponse> response= userService.getAllUsers();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getloggedinuser")
    public ResponseEntity<Users> getLoggedInUser(){
        Users response= getLoggedInUser.getLoggedInUser();
        return ResponseEntity.ok(response);
    }
}
