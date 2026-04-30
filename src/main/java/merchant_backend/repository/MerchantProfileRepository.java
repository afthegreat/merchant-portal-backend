package merchant_backend.repository;

import merchant_backend.entities.BusinessType;
import merchant_backend.entities.MerchantProfile;
import merchant_backend.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MerchantProfileRepository extends JpaRepository<MerchantProfile, Long> {

    List<MerchantProfile>findByMerchantId(Long id);
    // Logic: existsBy + [Field Name 'Merchant'] + And + [Field Name 'BusinessType']
    boolean existsByMerchantAndBusinessType(Users merchant, BusinessType businessType);
}