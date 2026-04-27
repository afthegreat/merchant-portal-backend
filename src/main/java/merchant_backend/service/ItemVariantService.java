package merchant_backend.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import merchant_backend.dto.itemVariant.NewItemVariantDTO;
import merchant_backend.entities.Item;
import merchant_backend.entities.ItemVariant;
import merchant_backend.repository.ItemRepository;
import merchant_backend.repository.ItemVariantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ItemVariantService {
    private final ItemVariantRepository itemVariantRepository;
    private final ItemRepository itemRepository;

    @Transactional
    public String registerNewItemVariant(List<NewItemVariantDTO> requests){
        List<Long> itemIds= requests.stream().map(NewItemVariantDTO::getItemId).toList();
        List<Item> items= itemRepository.findAllById(itemIds);

        Map<Long, Item> itemMap= items.stream()
                .collect(Collectors.toMap(Item::getId, item -> item));

        List<ItemVariant> itemVariantsToSave= requests.stream()
                .map(req->{
                    Item item=itemMap.get(req.getItemId());
                    if (item==null) throw new RuntimeException("item not found for id: " +req.getItemId());

                    ItemVariant itemVariant= new ItemVariant();
                    itemVariant.setItem(item);
                    itemVariant.setUnitPrice(req.getPrice());
                    return itemVariant;

                }).toList();
        itemVariantRepository.saveAll(itemVariantsToSave);
        return "ItemVariants registered successfully";

    }

}
