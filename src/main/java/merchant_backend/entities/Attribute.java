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
    @Table(name = "attributes")
    public class Attribute {
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "attr_seq")
        @SequenceGenerator(name = "attr_seq", sequenceName = "attribute_sequence", allocationSize = 50)
        private Long id;
        @OneToMany(mappedBy = "attribute", cascade = CascadeType.ALL)
        private Set<AttributeValue> attributeValues= new LinkedHashSet<>();
        @Column(unique = true)
        private String name;
    }

