package pl.pollub.integracja_projekt.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pollub.integracja_projekt.Models.HousingPrices;

public interface HousingPricesRepository extends JpaRepository<HousingPrices, Integer> {
}
