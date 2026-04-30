package merchant_backend.controller;

import lombok.RequiredArgsConstructor;
import merchant_backend.dto.BusinessType.BulkBusinessTypeDto;
import merchant_backend.dto.BusinessType.BusinessTypeResponse;
import merchant_backend.dto.BusinessType.RegisterBusinessType;
import merchant_backend.service.BusinessTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/businesstype")
@RequiredArgsConstructor
public class BusinessTypeController {
    private final BusinessTypeService businessTypeService;

    @PostMapping("/register")
    public ResponseEntity<String> registerBusinessType(@RequestBody List<RegisterBusinessType> request){
        String response= businessTypeService.registerNewBusinessType(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getallbusinesstypes")
    public ResponseEntity<List<BusinessTypeResponse>> getAllTypes(){
        List<BusinessTypeResponse> response= businessTypeService.getAllBusinessTypes();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getloggedinbusinesstypes")
    public ResponseEntity<BulkBusinessTypeDto> getLoggedInBusinessTypes(){
        return ResponseEntity.ok(businessTypeService.getCurrentBusinessTypeNames());
    }
}
