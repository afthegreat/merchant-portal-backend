package merchant_backend.controller;

import lombok.RequiredArgsConstructor;
import merchant_backend.dto.MerchantProfile.RegisterNewMerchantProfile;
import merchant_backend.service.MerchantProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/merchant")
@RequiredArgsConstructor
public class MerchantProfileController {

    private final MerchantProfileService merchantProfileService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody List<RegisterNewMerchantProfile> request) {
     String response = merchantProfileService.createNewProfile(request);
     return ResponseEntity.ok(response);
    }
}