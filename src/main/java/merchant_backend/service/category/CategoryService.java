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

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final BusinessTypeRepository businessTypeRepository;

    @Transactional
    public String registerNewCategory(List<RegisterCategory> requests){
        List<Category> categoriesToSave= requests.stream()
                .map(req->{
                    Category category= new Category();
                    category.setName(req.getCategoryName());
                    BusinessType businessType= businessTypeRepository.findById(req.getBusinessTypeId())
                                    .orElseThrow(()->new RuntimeException("business type id is invalid"));
                    category.setBusinessType(businessType);
                    if(req.getParentId()!=null){
                        Category parentId= categoryRepository.findById(req.getParentId())
                                .orElseThrow(()-> new RuntimeException("Incorrect parent category"));
                        category.setParent(parentId);
                    }

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
