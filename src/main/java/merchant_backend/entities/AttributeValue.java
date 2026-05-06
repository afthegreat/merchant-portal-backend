package merchant_backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "attribute_values")
public class AttributeValue {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "attval_seq")
    @SequenceGenerator(name = "attval_seq", sequenceName = "attribute_value_sequence", allocationSize = 50)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "attribute_id")
    private Attribute attribute;
    @OneToMany(mappedBy = "attributeValue", cascade = CascadeType.ALL)
    private Set<VariantAttributeMap> variantAttributeMaps= new LinkedHashSet<>();
    @Column(name = "attribute_value")
    private String value;
}