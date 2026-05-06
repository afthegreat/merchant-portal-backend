package merchant_backend.controller;

import lombok.RequiredArgsConstructor;
import merchant_backend.dto.item.AllItemsResponseDTO;
import merchant_backend.dto.item.ItemDTO;
import merchant_backend.dto.item.NewItemRegistration;
import merchant_backend.service.item.ItemService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/item")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @PostMapping("/register")
    public ResponseEntity<String> registerItem(@RequestBody List<NewItemRegistration> request){
        String response= itemService.registerNewItems(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("getallitems")
    public ResponseEntity<Page<AllItemsResponseDTO>>getAllItems(@RequestParam(defaultValue = "0") int page , @RequestParam(defaultValue = "10" )int size){
        return ResponseEntity.ok(itemService.getAllItems(page,size));
    }

    @GetMapping("/getitembycategoryid")
    public ResponseEntity<Page<AllItemsResponseDTO>> getItemsByCategory(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam (defaultValue = "10") int size,
            @RequestParam Long categoryId){
        return ResponseEntity.ok(itemService.getItemByCategoryId(page,size,categoryId));
    }
    @GetMapping("/getitemdetailsbycategoryid")
    public ResponseEntity<Page<ItemDTO>> getItemsdetailByCategory(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam (defaultValue = "10") int size,
            @RequestParam Long categoryId){
        return ResponseEntity.ok(itemService.getItemDetailsByCategory(categoryId, page, size));
    }
}
