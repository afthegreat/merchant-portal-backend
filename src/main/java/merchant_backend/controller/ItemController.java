package merchant_backend.controller;

import lombok.RequiredArgsConstructor;
import merchant_backend.dto.item.NewItemRegistration;
import merchant_backend.service.item.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
