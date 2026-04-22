package merchant_backend.repository;

import merchant_backend.entities.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface AttributeRepository extends JpaRepository<Attribute, Long> {
}
