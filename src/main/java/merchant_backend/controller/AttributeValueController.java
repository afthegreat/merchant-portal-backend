package merchant_backend.controller;

import lombok.RequiredArgsConstructor;
import merchant_backend.dto.attribute_value.AllIAttributeValuesResponseDTO;
import merchant_backend.dto.attribute_value.NewAttributeValue;
import merchant_backend.service.attribute_value.AttributeValueService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attributevalue")
@RequiredArgsConstructor
public class AttributeValueController {
    private final AttributeValueService attributeValueService;

    @PostMapping("/register")
    public ResponseEntity<String> registerNewAttributeValue(@RequestBody List<NewAttributeValue> requests){
        return ResponseEntity.ok(attributeValueService.registerNewAttributeValue(requests));
    }

    @GetMapping("/getallattributevalues")
    public ResponseEntity<Page<AllIAttributeValuesResponseDTO>> getAllAttributeValues(
            @RequestParam (defaultValue = "0") int page,
            @RequestParam (defaultValue = "10") int size
    ){
        return ResponseEntity.ok(attributeValueService.getAllAttributeValues(page, size));
    }

}
