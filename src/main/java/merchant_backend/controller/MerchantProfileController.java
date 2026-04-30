package merchant_backend.controller;

import lombok.RequiredArgsConstructor;
import merchant_backend.dto.MerchantProfile.MerchantProfileResponseDTO;
import merchant_backend.dto.MerchantProfile.RegisterNewMerchantProfile;
import merchant_backend.service.MerchantProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/merchantprofile")
@RequiredArgsConstructor
public class MerchantProfileController {

    private final MerchantProfileService merchantProfileService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody List<RegisterNewMerchantProfile> request) {
     return ResponseEntity.ok(merchantProfileService.createNewProfile(request));
    }

    @GetMapping("/getcurrentuserprofile")
    public ResponseEntity<MerchantProfileResponseDTO> getCurrentUserProfile(){
        return ResponseEntity.ok(merchantProfileService.getSingleMerchantProfile());
    }
}