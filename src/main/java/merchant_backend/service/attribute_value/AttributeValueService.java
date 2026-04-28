package merchant_backend.service.attribute_value;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import merchant_backend.dto.attribute_value.NewAttributeValue;
import merchant_backend.repository.AttributeValueRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttributeValueService {
    private final AttributeValueRepository attributeValueRepository;

    @Transactional
    public String registerNewAttributeValue(List<NewAttributeValue> requests){

    }
}
