package pl.pollub.integracja_projekt.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.pollub.integracja_projekt.Models.HousingPrices;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface HousingPricesRepository extends JpaRepository<HousingPrices, Integer> {
    List<HousingPrices> findByName(String name);
    List<HousingPrices> findByTransaction(String transaction);
    List<HousingPrices> findBySurface(String surface);
    List<HousingPrices> findByYear(Integer year);
    List<HousingPrices> findByYearAndNameAndTransaction(Integer year, String name, String transaction);
    List<HousingPrices> findByNameAndTransactionAndSurface(String name, String transaction, String surface);
    List<HousingPrices> findByYearAndNameAndTransactionAndSurface(Integer year, String name, String transaction, String surface);

}
