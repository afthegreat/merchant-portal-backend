package merchant_backend.repository;

import merchant_backend.entities.Item;
import merchant_backend.entities.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query("select i from Item i LEFT JOIN fetch i.category where i.user=:user")
    Page<Item> findAllByUser(Users user, Pageable pageable);

    List<Item> findAllByIdInAndUser(List<Long> ids, Users user);

    Page<Item> findAllByCategoryId(Pageable pageable,Long categoryId);
    @Query("SELECT i FROM Item i JOIN FETCH i.itemVariants v " +
            "JOIN FETCH v.attributeMappings m " +
            "JOIN FETCH m.attributeValue av " +
            "JOIN FETCH av.attribute " +
            "WHERE i.category.id = :categoryId")
    Page<Item> findAllByCategoryyId(Long categoryId, Pageable pageable);
}
