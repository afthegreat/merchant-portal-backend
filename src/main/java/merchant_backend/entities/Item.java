package merchant_backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_seq")
    @SequenceGenerator(name="item_seq", sequenceName ="item_sequence", allocationSize = 50)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id") // The owner of the item
    private Users user;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @Column(unique = true)
    private String name;
    private String unitOfMeasurement;
    private String description;
    private String imageUrl;
    // Inside Item.java
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private Set<ItemVariant> itemVariants= new LinkedHashSet<>();
}


