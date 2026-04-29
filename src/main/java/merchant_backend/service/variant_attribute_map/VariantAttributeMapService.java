package merchant_backend.service.variant_attribute_map;

import lombok.RequiredArgsConstructor;
import merchant_backend.dto.variant_attribute_map.NewVariantAttributeDTO;
import merchant_backend.entities.AttributeValue;
import merchant_backend.entities.ItemVariant;
import merchant_backend.entities.VariantAttributeMap;
import merchant_backend.repository.AttributeValueRepository;
import merchant_backend.repository.ItemVariantRepository;
import merchant_backend.repository.VarinatAttributeMapRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VariantAttributeMapService {

    private final VarinatAttributeMapRepository varinatAttributeMapRepository;
    private final ItemVariantRepository itemVariantRepository;
    private final AttributeValueRepository attributeValueRepository;

    public String registerNewMapping(List<NewVariantAttributeDTO> requests){
        List<Long> allVariantIds= requests.stream().map(NewVariantAttributeDTO::variantId).distinct().toList();
        List<Long> allValueIds= requests.stream().map(NewVariantAttributeDTO::attributeValueId).distinct().toList();

        Map<Long, ItemVariant> variantMap= itemVariantRepository.findAllById(allVariantIds).stream()
                .collect(Collectors.toMap(ItemVariant::getId, iv->iv));
        Map<Long, AttributeValue> valueMap= attributeValueRepository.findAllById(allValueIds)
                .stream().collect(Collectors.toMap(AttributeValue::getId, av->av));

        List<VariantAttributeMap> variantAttributeMapsToSave= requests.stream().map(request->{
            VariantAttributeMap variantAttributeMap= new VariantAttributeMap();
            ItemVariant variant= variantMap.get(request.variantId());
            if(variant==null){
                throw new RuntimeException("invalid id for item variant id:"+ request.variantId());
            }
            AttributeValue value= valueMap.get(request.attributeValueId());
            if (value==null){
                throw new RuntimeException("invalid attribute value id: "+request.attributeValueId());
            }
            Long newAttributeCategoryId = value.getAttribute().getId();

            // 2. Check if the variant already has a mapping for this category
            // Note: This assumes your ItemVariant entity has a List<VariantAttributeMap> called 'attributeMappings'
            boolean alreadyHasThisAttribute = variant.getAttributeMappings().stream()
                    .anyMatch(existingMapping ->
                            existingMapping.getAttributeValue().getAttribute().getId().equals(newAttributeCategoryId)
                    );

            if (alreadyHasThisAttribute) {
                throw new RuntimeException("Conflict: Variant ID " + request.variantId() +
                        " already has a value assigned for the attribute category: " +
                        value.getAttribute().getName());
            }
            variantAttributeMap.setAttributeValue(value);
            variantAttributeMap.setItemVariant(variant);
            return variantAttributeMap;
        }).toList();
        varinatAttributeMapRepository.saveAll(variantAttributeMapsToSave);
        return "variant attribute value maps created successfully";
    }
}
