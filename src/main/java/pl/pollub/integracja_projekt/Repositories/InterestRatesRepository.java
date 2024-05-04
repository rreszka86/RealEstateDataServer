package pl.pollub.integracja_projekt.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pollub.integracja_projekt.Models.InterestRates;

@Repository
public interface InterestRatesRepository extends JpaRepository<InterestRates, Integer> {
}
