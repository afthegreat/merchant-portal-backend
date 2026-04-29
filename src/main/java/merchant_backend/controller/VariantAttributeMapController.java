package merchant_backend.controller;

import lombok.RequiredArgsConstructor;
import merchant_backend.dto.variant_attribute_map.NewVariantAttributeDTO;
import merchant_backend.service.variant_attribute_map.VariantAttributeMapService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/variantattributemap")
@RequiredArgsConstructor
public class VariantAttributeMapController {
    private final VariantAttributeMapService variantAttributeMapService;

    @PostMapping("/register")
    public ResponseEntity<String> registerNewMapping(List<NewVariantAttributeDTO> requests){
        return  ResponseEntity.ok(variantAttributeMapService.registerNewMapping(requests));
    }
}
