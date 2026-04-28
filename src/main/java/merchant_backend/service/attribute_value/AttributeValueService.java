package merchant_backend.service.attribute_value;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import merchant_backend.dto.attribute_value.NewAttributeValue;
import merchant_backend.entities.Attribute;
import merchant_backend.entities.AttributeValue;
import merchant_backend.repository.AttributeRepository;
import merchant_backend.repository.AttributeValueRepository;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AttributeValueService {
    private final AttributeValueRepository attributeValueRepository;
    private final AttributeRepository attributeRepository;

    @Transactional
    public String registerNewAttributeValue(List<NewAttributeValue> requests){

        List<Long> attrIds= requests.stream().map(NewAttributeValue::getAttributeId).distinct().toList();

        Map<Long, Attribute> attrMap= attributeRepository.findAllById(attrIds).stream()
                .collect(Collectors.toMap(Attribute::getId, a->a));

        List<AttributeValue> attributeValuesToSave=requests.stream().map(
                request->{
                    Attribute attribute= attrMap.get(request.getAttributeId());
                    if(attribute==null){
                        throw new RuntimeException("No attribute found for :"+request.getAttributeId());
                    }
                    AttributeValue value=new AttributeValue();
                    value.setAttribute(attribute);
                    value.setValue(request.getValue());
                    return value;
                }
        ).toList();
        attributeValueRepository.saveAll(attributeValuesToSave);
        return "attribute values created successfully";
    }
}
