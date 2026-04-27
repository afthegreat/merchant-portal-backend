package merchant_backend.repository;

import merchant_backend.entities.Attribute;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttributeRepository extends JpaRepository<Attribute, Long> {
    @Override
    Page<Attribute> findAll(Pageable pageable);
}
