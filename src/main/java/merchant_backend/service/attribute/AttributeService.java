package merchant_backend.service.attribute;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import merchant_backend.dto.attribute.AttributeResponseDTO;
import merchant_backend.dto.attribute.NewAttributeDTO;
import merchant_backend.dto.category.AllCategoriesResponse;
import merchant_backend.entities.Attribute;
import merchant_backend.repository.AttributeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public Page<AttributeResponseDTO> getAllAttributes(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Attribute> attributes = attributeRepository.findAll(pageable);

        // FIX: Use AttributeResponseDTO instead of AllCategoriesResponse
        return attributes.map(attribute -> new AttributeResponseDTO(
                attribute.getId(),
                attribute.getName()
        ));
    }}
