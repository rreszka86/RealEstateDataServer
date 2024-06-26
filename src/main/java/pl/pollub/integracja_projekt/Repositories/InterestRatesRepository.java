package pl.pollub.integracja_projekt.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.pollub.integracja_projekt.Models.InterestRates;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface InterestRatesRepository extends JpaRepository<InterestRates, Integer> {
    List<InterestRates> findByDateBetween(String fromDate, String toDate);
    List<InterestRates> findByDateAfter(String fromDate);
}
