package merchant_backend.repository;

import merchant_backend.entities.Item;
import merchant_backend.entities.ItemVariant;
import merchant_backend.entities.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemVariantRepository extends JpaRepository<ItemVariant, Long> {

    @EntityGraph(attributePaths = "item")
    Page<ItemVariant> findAll(Pageable pageable);

    @EntityGraph(attributePaths = "item")
    Page<ItemVariant> findAllByItem_User(Users user, Pageable pageable);

}
