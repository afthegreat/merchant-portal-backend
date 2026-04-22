package merchant_backend.dto.Users;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class RegisterUser {
    private String userName;
    private String password;
}
