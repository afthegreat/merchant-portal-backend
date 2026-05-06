package merchant_backend.dto.category;

import merchant_backend.dto.item.ItemDTO;
import org.springframework.data.domain.Page;

public record CategoryItemsResponse(
        Long categoryId,
        String categoryName,
        Page<ItemDTO> items
) {}