package merchant_backend.dto.MerchantProfile;

import java.util.List;

public record MerchantProfileResponseDTO(
        Long merchantId,
        List<Long> businessTypeId
) {
}
