package merchant_backend.utility;

import lombok.RequiredArgsConstructor;
import merchant_backend.entities.BusinessType;
import merchant_backend.repository.BusinessTypeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BusinessTypeDataInitializer implements CommandLineRunner {
    private final BusinessTypeRepository businessTypeRepository;

    @Override
    public void run(String... args) throws Exception {
        if(businessTypeRepository.count() ==0){
            BusinessType retail = new BusinessType();
            retail.setName("Retail");
            retail.setDescription("Physical products like electronics or clothes");

            BusinessType service = new BusinessType();
            service.setName("Service");
            service.setDescription("Professional or manual services");

            BusinessType rental = new BusinessType();
            rental.setName("Rental");
            rental.setDescription("Leasing items like vehicles or equipment");
            businessTypeRepository.saveAll(List.of(retail, service, rental));
            System.out.println("Business types initialized");
        }
        else {
            System.out.println("there are defined businesses");
        }
    }
}
