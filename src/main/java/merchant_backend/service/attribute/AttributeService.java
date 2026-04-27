package merchant_backend.service.attribute;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import merchant_backend.dto.attribute.NewAttributeDTO;
import merchant_backend.entities.Attribute;
import merchant_backend.repository.AttributeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttributeService {
    private final AttributeRepository attributeRepository;

    @Transactional
    public String addNewAttribute(List<NewAttributeDTO> requests){
        if (requests==null || requests.isEmpty()){
            throw new RuntimeException("invalid requests");
        }
        List<Attribute> attributesToSave= requests.stream()
                .map(request->{
                    Attribute attribute= new Attribute();
                    attribute.setName(request.getAttributeName());
                    return attribute;
                }).toList();
        attributeRepository.saveAll(attributesToSave);
        return "Attributes saved successfully";
    }
}
