package merchant_backend.dto.attribute_value;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewAttributeValue {
    private Long attributeId;
    private String value;
}
