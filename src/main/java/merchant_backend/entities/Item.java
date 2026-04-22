package merchant_backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
}


