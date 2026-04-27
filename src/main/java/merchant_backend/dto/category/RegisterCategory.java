package merchant_backend.dto.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterCategory {
    private Long businessTypeId;
    private Long parentId;
    private String categoryName;

}
