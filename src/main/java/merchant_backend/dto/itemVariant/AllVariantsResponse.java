package merchant_backend.dto.itemVariant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllVariantsResponse {
    private Long itemId;
    private String itemName;
    private Double UnitPrice;
}
