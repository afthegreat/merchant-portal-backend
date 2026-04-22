package merchant_backend.dto.MerchantProfile;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterNewMerchantProfile {
    private Long userId;
    private Long businessTypeId;
}
