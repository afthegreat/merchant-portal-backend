package merchant_backend.controller;

import lombok.RequiredArgsConstructor;
import merchant_backend.dto.attribute.NewAttributeDTO;
import merchant_backend.service.attribute.AttributeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
