package merchant_backend.repository;

import merchant_backend.entities.BusinessType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BusinessTypeRepository extends JpaRepository<BusinessType, Long> {
    boolean existsBusinessTypeByNameIn(List<String> businessNames);


}
