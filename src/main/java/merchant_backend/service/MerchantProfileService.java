package merchant_backend.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import merchant_backend.dto.MerchantProfile.MerchantProfileResponseDTO;
import merchant_backend.dto.MerchantProfile.RegisterNewMerchantProfile;
import merchant_backend.entities.BusinessType;
import merchant_backend.entities.MerchantProfile;
import merchant_backend.entities.Users;
import merchant_backend.repository.BusinessTypeRepository;
import merchant_backend.repository.MerchantProfileRepository;
import merchant_backend.repository.UsersRepository;
import merchant_backend.service.user.GetLoggedInUser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MerchantProfileService {

    private final MerchantProfileRepository merchantProfileRepository;
    private final UsersRepository usersRepository;
    private final BusinessTypeRepository businessTypeRepository;
    private final GetLoggedInUser getLoggedInUser;

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

    @Transactional
    public MerchantProfileResponseDTO getSingleMerchantProfile(){
        Long userId= getLoggedInUser.getLoggedInUser().getId();
        List<MerchantProfile> merchantProfiles= merchantProfileRepository.findByMerchantId(userId);

        if (merchantProfiles.isEmpty()){
        throw new RuntimeException("no profile found for the logged in user");
        }
        List<Long>businessTypeIds=merchantProfiles.stream().map(merchantProfile -> merchantProfile.getBusinessType().getId()).toList();
        return new MerchantProfileResponseDTO(userId,businessTypeIds);

    }
}
