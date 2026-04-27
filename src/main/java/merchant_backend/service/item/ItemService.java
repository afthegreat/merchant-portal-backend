package merchant_backend.service.item;

import lombok.RequiredArgsConstructor;
import merchant_backend.dto.item.NewItemRegistration;
import merchant_backend.entities.Category;
import merchant_backend.entities.Item;
import merchant_backend.entities.Users;
import merchant_backend.repository.CategoryRepository;
import merchant_backend.repository.ItemRepository;
import merchant_backend.service.user.GetLoggedInUser;
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
}
