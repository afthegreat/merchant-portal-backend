package merchant_backend.controller;

import lombok.RequiredArgsConstructor;
import merchant_backend.dto.itemVariant.NewItemVariantDTO;
import merchant_backend.service.ItemVariantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/itemvariant")
@RequiredArgsConstructor
public class ItemVariantController {
    private final ItemVariantService itemVariantService;

    @PostMapping("/register")
    public ResponseEntity<String> registerItemVariant(@RequestBody List<NewItemVariantDTO> request){
        return ResponseEntity.ok(itemVariantService.registerNewItemVariant(request));
    }
}
