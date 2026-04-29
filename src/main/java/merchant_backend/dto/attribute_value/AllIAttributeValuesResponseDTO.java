package merchant_backend.dto.attribute_value;

public record AllIAttributeValuesResponseDTO(
         Long id,
         Long attributeId,
         String attributeName,
         String value
) {}
