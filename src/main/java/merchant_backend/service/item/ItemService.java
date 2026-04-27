package merchant_backend.service.item;

import lombok.RequiredArgsConstructor;
import merchant_backend.dto.item.AllItemsResponseDTO;
import merchant_backend.dto.item.NewItemRegistration;
import merchant_backend.entities.Category;
import merchant_backend.entities.Item;
import merchant_backend.entities.Users;
import merchant_backend.repository.CategoryRepository;
import merchant_backend.repository.ItemRepository;
import merchant_backend.service.user.GetLoggedInUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;
    private final GetLoggedInUser getLoggedInUser;

    public String registerNewItems(List<NewItemRegistration> request){
        Users user= getLoggedInUser.getLoggedInUser();

        List<Item> itemsToSave = request.stream()
            .map(req->{
                Category cat= categoryRepository.findById(req.getCategory()).orElseThrow();
                Item item=new Item();
                item.setUser(user);
                item.setCategory(cat);
                item.setName(req.getItemName());
                item.setDescription(req.getDescription());
                item.setUnitOfMeasurement(req.getUnitOfMeasurement());

                return item;
            }).toList();
    itemRepository.saveAll(itemsToSave);
    return "Items registered successfully";
    }

    public Page<AllItemsResponseDTO> getAllItems(int page, int size){
        Users user= getLoggedInUser.getLoggedInUser();
        Pageable pageable= PageRequest.of(page,size);
        Page<Item> items= itemRepository.findAllByUser(user,pageable);

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
}
