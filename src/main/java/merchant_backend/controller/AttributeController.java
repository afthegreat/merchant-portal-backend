package merchant_backend.controller;

import lombok.RequiredArgsConstructor;
import merchant_backend.dto.attribute.AttributeResponseDTO;
import merchant_backend.dto.attribute.NewAttributeDTO;
import merchant_backend.service.attribute.AttributeService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/attribute")
public class AttributeController {

    private final AttributeService attributeService;

    @PostMapping("/register")
    public ResponseEntity<String> registerNewAttribute(@RequestBody List<NewAttributeDTO> requests){
        String response= attributeService.addNewAttribute(requests);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getallattributes")
    public ResponseEntity<Page<AttributeResponseDTO>> getAllAttributes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam (defaultValue = "10") int size
    ){
        return ResponseEntity.ok(attributeService.getAllAttributes(page,size));
    }



}
