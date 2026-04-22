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
@Table(name = "merchant_profiles")
public class MerchantProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    // name = the DB column name; we'll use merchant_id to be standard
    @JoinColumn(name = "merchant_id", nullable = false)
    private Users merchant; // <--- CHANGE THIS from merchantId to merchant

    @ManyToOne
    @JoinColumn(name = "business_type_id")
    private BusinessType businessType;
}