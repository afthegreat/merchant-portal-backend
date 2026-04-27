package merchant_backend.dto.itemVariant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewItemVariantDTO {
    private Long itemId;
    private Double price;
}
