package merchant_backend.dto.itemVariant;

import java.util.Map;

public record VariantDto(
        Long variantId,
        Double unitPrice,
        Map<String, String> attributes
) {
}
