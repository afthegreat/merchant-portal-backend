package merchant_backend.service.item;

import lombok.RequiredArgsConstructor;
import merchant_backend.dto.item.AllItemsResponseDTO;
import merchant_backend.dto.item.ItemDTO;
import merchant_backend.dto.item.NewItemRegistration;
import merchant_backend.dto.itemVariant.VariantDto;
import merchant_backend.entities.Category;
import merchant_backend.entities.Item;
import merchant_backend.entities.ItemVariant;
import merchant_backend.entities.Users;
import merchant_backend.repository.CategoryRepository;
import merchant_backend.repository.ItemRepository;
import merchant_backend.repository.ItemVariantRepository;
import merchant_backend.service.user.GetLoggedInUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;
    private final GetLoggedInUser getLoggedInUser;
    private final ItemVariantRepository itemVariantRepository;

    public String registerNewItems(List<NewItemRegistration> request) {
        Users user = getLoggedInUser.getLoggedInUser();

        List<Long> allCategoryIds = request.stream().map(
                NewItemRegistration::getCategory
        ).distinct().toList();

        Map<Long, Category> categoryMap = categoryRepository.findAllById(allCategoryIds).stream()
                .collect(Collectors.toMap(Category::getId, c -> c));
        List<Item> itemsToSave = request.stream()
                .map(req -> {
                    Category category = categoryMap.get(req.getCategory());
                    if (category == null) {
                        throw new RuntimeException("Invalid category ID: " + req.getCategory());
                    }
                    Item item = new Item();
                    item.setUser(user);
                    item.setCategory(category);
                    item.setName(req.getItemName());
                    item.setDescription(req.getDescription());
                    item.setUnitOfMeasurement(req.getUnitOfMeasurement());

                    return item;

                }).toList();
        itemRepository.saveAll(itemsToSave);
        return "Items registered successfully";
    }

    public Page<AllItemsResponseDTO> getAllItems(int page, int size) {
        Users user = getLoggedInUser.getLoggedInUser();
        Pageable pageable = PageRequest.of(page, size);
        Page<Item> items = itemRepository.findAllByUser(user, pageable);

        return items
                .map(item -> new AllItemsResponseDTO(
                        item.getId(),
                        item.getCategory().getId(),
                        item.getCategory().getName(),
                        item.getName(),
                        item.getUnitOfMeasurement(),
                        item.getDescription()
                ));
    }

    public Page<AllItemsResponseDTO> getItemByCategoryId(int page, int size,Long categoryId) {
        Pageable pageable=PageRequest.of(page, size);
    Page<Item> items= itemRepository.findAllByCategoryId(pageable,categoryId);
    return items
            .map(item -> new AllItemsResponseDTO(
                    item.getId(),
                    item.getCategory().getId(),
                    item.getCategory().getName(),
                    item.getName(),
                    item.getUnitOfMeasurement(),
                    item.getDescription()
            ));
    }
    public Page<ItemDTO> getItemDetailsByCategory(Long categoryId, int page, int size ) {
        Pageable pageable= PageRequest.of(page,size);
        Page<Item> itemPage = itemRepository.findAllByCategoryyId(categoryId, pageable);

        return itemPage.map(item -> new ItemDTO(
                item.getId(),
                item.getName(),
                item.getUnitOfMeasurement(),
                item.getDescription(),
                item.getItemVariants().stream().map(variant -> new VariantDto(
                        variant.getId(),
                        variant.getAttributeMappings().stream().collect(Collectors.toMap(
                                map -> map.getAttributeValue().getAttribute().getName(),
                                map -> map.getAttributeValue().getValue()
                        ))
                )).toList()
        ));
    }
}

