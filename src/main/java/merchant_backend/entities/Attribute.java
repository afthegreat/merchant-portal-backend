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
    @Table(name = "attributes")
    public class Attribute {
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "attr_seq")
        @SequenceGenerator(name = "attr_seq", sequenceName = "attribute_sequence", allocationSize = 50)
        private Long id;
        @Column(unique = true)
        private String name;
    }

