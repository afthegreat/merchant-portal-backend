package merchant_backend.repository;


import merchant_backend.dto.category.LoggedInUserCategoryResponseDto;
import merchant_backend.entities.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT c FROM Category c LEFT JOIN FETCH c.businessType")
    Page<Category> findAllWithBusinessTypes(Pageable pageable);

    List<Category>findByBusinessType_IdIn(List<Long> id);

    List<Category> findByBusinessType_Id(Long id);
}
