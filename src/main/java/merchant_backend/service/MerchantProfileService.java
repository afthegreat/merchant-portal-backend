package merchant_backend.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import merchant_backend.dto.MerchantProfile.RegisterNewMerchantProfile;
import merchant_backend.entities.BusinessType;
import merchant_backend.entities.MerchantProfile;
import merchant_backend.entities.Users;
import merchant_backend.repository.BusinessTypeRepository;
import merchant_backend.repository.MerchantProfileRepository;
import merchant_backend.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MerchantProfileService {

    private final MerchantProfileRepository merchantProfileRepository;
    private final UsersRepository usersRepository;
    private final BusinessTypeRepository businessTypeRepository;

    @Transactional
    public String createNewProfile(List<RegisterNewMerchantProfile> requests){

        List<MerchantProfile> profileToSave= requests.stream()
                .map(req->{
                    Users user= usersRepository.findById(req.getUserId()).orElseThrow();
                    BusinessType type= businessTypeRepository.findById(req.getBusinessTypeId()).orElseThrow();

                    MerchantProfile p= new MerchantProfile();
                    p.setMerchant(user);
                    p.setBusinessType(type);

                    return p;
                }).toList();

        merchantProfileRepository.saveAll(profileToSave);
        return "Profiles created successfully";
    }

    p
}
