package merchant_backend.service.user;

import lombok.RequiredArgsConstructor;
import merchant_backend.entities.Users;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetLoggedInUser {

    public Users getLoggedInUser(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        if(authentication==null || !authentication.isAuthenticated()){
            throw new RuntimeException("No user Currently logged in");
        }
        Object principal= authentication.getPrincipal();

        if(principal instanceof Users){
            return (Users) principal;
        }
        throw new RuntimeException("principal type mismatch");
    }
}
