package pl.pollub.integracja_projekt.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pollub.integracja_projekt.Models.InterestRates;

import java.util.List;

@Repository
public interface InterestRatesRepository extends JpaRepository<InterestRates, Integer> {
    List<InterestRates> findByDateBetween(String fromDate, String toDate);
    List<InterestRates> findByDateAfter(String fromDate);
}
