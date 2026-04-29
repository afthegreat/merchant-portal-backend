package merchant_backend.repository;

import merchant_backend.entities.AttributeValue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttributeValueRepository extends JpaRepository<AttributeValue, Long> {

    @Override
    @EntityGraph(attributePaths = "attribute")
    Page<AttributeValue>findAll(Pageable pageable);
}
