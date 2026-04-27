package merchant_backend.dto.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllCategoriesResponse {
    private Long id;
    private String categoryName;
    private String businessType;
}
