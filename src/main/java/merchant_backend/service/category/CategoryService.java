package merchant_backend.service.category;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import merchant_backend.dto.category.AllCategoriesResponse;
import merchant_backend.dto.category.RegisterCategory;
import merchant_backend.entities.BusinessType;
import merchant_backend.entities.Category;
import merchant_backend.repository.BusinessTypeRepository;
import merchant_backend.repository.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final BusinessTypeRepository businessTypeRepository;

    @Transactional
    public String registerNewCategory(List<RegisterCategory> requests){
        List<Long> allBusinessTypeIds= requests.stream().map(
                RegisterCategory::getBusinessTypeId
        ).distinct().toList();

        Map<Long, BusinessType> businessTypeMap= businessTypeRepository.findAllById(allBusinessTypeIds).stream()
                .collect(Collectors.toMap(BusinessType::getId, b->b));

        List<Long> allParentIds= requests.stream().map(RegisterCategory::getParentId).distinct().toList();

        Map<Long, Category> categoryMap= categoryRepository.findAllById(allParentIds).stream().collect(Collectors.toMap(Category::getId, p->p));

        List<Category> categoriesToSave=requests.stream().map(req->{
            BusinessType type= businessTypeMap.get(req.getBusinessTypeId());
            if (type==null){
                throw new RuntimeException("Invalid business type ID:"+req.getBusinessTypeId());
            }

            Category category= new Category();
            category.setBusinessType(type);
            if (req.getParentId()!=null){
                Category parentId=categoryMap.get(req.getParentId());
                if (parentId==null){
                    throw new RuntimeException("invalid parent id:"+req.getParentId());
                }
                category.setParent(parentId);
            }
            category.setName(req.getCategoryName());
            return category;
        }).toList();
        categoryRepository.saveAll(categoriesToSave);
        return "Categories Registered";
    }

    @Transactional
    public Page<AllCategoriesResponse> getAllCategories(int page, int size) {
        // 1. Create a request for a specific page and size
        Pageable pageable = PageRequest.of(page, size);

        // 2. Fetch the page of entities
        Page<Category> categoryPage = categoryRepository.findAllWithBusinessTypes(pageable);

        // 3. Map the page of entities to a page of DTOs
        return categoryPage.map(category -> new AllCategoriesResponse(
                category.getId(),
                category.getName(),
                category.getBusinessType().getName()
        ));
    }}
