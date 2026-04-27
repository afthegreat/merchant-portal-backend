package merchant_backend.dto.item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllItemsResponseDTO {

    private Long itemId;
    private Long categoryId;
    private String categoryName;
    private String itemName;
    private String unitOfMeasurement;
    private String description;
}
