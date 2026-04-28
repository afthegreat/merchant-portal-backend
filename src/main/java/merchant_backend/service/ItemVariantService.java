package merchant_backend.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import merchant_backend.dto.itemVariant.AllVariantsResponse;
import merchant_backend.dto.itemVariant.NewItemVariantDTO;
import merchant_backend.entities.Item;
import merchant_backend.entities.ItemVariant;
import merchant_backend.entities.Users;
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
public class ItemVariantService {
    private final ItemVariantRepository itemVariantRepository;
    private final ItemRepository itemRepository;
    private final GetLoggedInUser getLoggedInUser;

    @Transactional
    public String registerNewItemVariant(List<NewItemVariantDTO> requests){
        Users user= getLoggedInUser.getLoggedInUser();
        List<Long> itemIds= requests.stream().map(NewItemVariantDTO::getItemId).toList();
        List<Item> items= itemRepository.findAllByIdInAndUser(itemIds, user);

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

    public Page<AllVariantsResponse> getAllVariants(int page, int size){
        Users user= getLoggedInUser.getLoggedInUser();
        Pageable pageable= PageRequest.of(page, size);
    Page<ItemVariant> itemVariants = itemVariantRepository.findAllByItem_User(user, pageable);
    return itemVariants.map(itemVariant -> new AllVariantsResponse(
            itemVariant.getItem().getId(),
            itemVariant.getItem().getName(),
            itemVariant.getUnitPrice()
    ) );
    }
}
