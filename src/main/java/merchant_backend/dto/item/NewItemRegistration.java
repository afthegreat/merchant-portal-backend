package merchant_backend.dto.item;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
public class NewItemRegistration {
    private Long category;
    private String itemName;
    private String unitOfMeasurement;
    private String description;
    private String imageUrl;
}
