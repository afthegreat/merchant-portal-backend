package merchant_backend.dto.item;

import merchant_backend.dto.itemVariant.VariantDto;

import java.util.List;

public record ItemDTO(
        Long itemId,
        String itemName,
        String unitOfMeasurement,
        String description,
        List<VariantDto> variants
) {}