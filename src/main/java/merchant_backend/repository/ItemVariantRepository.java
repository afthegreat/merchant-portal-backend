package merchant_backend.repository;

import merchant_backend.entities.ItemVariant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemVariantRepository extends JpaRepository<ItemVariant, Long> {

}
