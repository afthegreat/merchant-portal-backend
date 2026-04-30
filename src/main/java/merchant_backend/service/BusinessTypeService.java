package merchant_backend.service;


import lombok.RequiredArgsConstructor;
import merchant_backend.dto.BusinessType.BulkBusinessTypeDto;
import merchant_backend.dto.BusinessType.BusinessTypeResponse;
import merchant_backend.dto.BusinessType.RegisterBusinessType;
import merchant_backend.entities.BusinessType;
import merchant_backend.repository.BusinessTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BusinessTypeService {

    private final BusinessTypeRepository businessTypeRepository;
    private final MerchantProfileService merchantProfileService;

    public String registerNewBusinessType(List<RegisterBusinessType> requests){
        List<BusinessType> businessTypesToSave= requests.stream()
                .map(req->{
                    BusinessType type= new BusinessType();
                    type.setName(req.getName());
                    type.setDescription(req.getDescription());
                    return type;
                })
                .toList();
        businessTypeRepository.saveAll(businessTypesToSave);
        return "Business Types Created Successfully";
    }

    public List<BusinessTypeResponse> getAllBusinessTypes(){
        List<BusinessType> types= businessTypeRepository.findAll();
        return types.stream()
                .map( type-> new BusinessTypeResponse(type.getId(),type.getName()))
                .toList();
    }

    public BulkBusinessTypeDto getCurrentBusinessTypeNames(){
        List<Long>businessTypeIds=merchantProfileService.getSingleMerchantProfile().businessTypeId();

        List<String> allBusinessTypeNames= businessTypeRepository.findAllById(businessTypeIds).stream()
                .map(BusinessType::getName).toList();
        return new BulkBusinessTypeDto(allBusinessTypeNames);
    }
}
