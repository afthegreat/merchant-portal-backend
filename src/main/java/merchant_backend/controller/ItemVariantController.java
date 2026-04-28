package merchant_backend.controller;

import lombok.RequiredArgsConstructor;
import merchant_backend.dto.itemVariant.AllVariantsResponse;
import merchant_backend.dto.itemVariant.NewItemVariantDTO;
import merchant_backend.service.ItemVariantService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/getallvariants")
    public ResponseEntity<Page<AllVariantsResponse>>getAllvariants(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam (defaultValue = "10") int size
    )
    {
        return ResponseEntity.ok(itemVariantService.getAllVariants(page, size));
    }

}
