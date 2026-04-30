package merchant_backend.dto.BusinessType;

import java.util.List;

public record BulkBusinessTypeDto(
        List<String> businessNames
) {
}
