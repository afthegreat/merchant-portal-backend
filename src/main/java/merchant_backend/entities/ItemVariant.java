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
@Table(name = "item_variants")
public class ItemVariant {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "itevar_seq")
    @SequenceGenerator(name = "itevar_seq", sequenceName = "itemVariantSequence", allocationSize = 50)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    private Double unitPrice;
}
