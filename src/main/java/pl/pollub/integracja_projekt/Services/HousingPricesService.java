package pl.pollub.integracja_projekt.Services;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.pollub.integracja_projekt.Models.HousingPrices;
import pl.pollub.integracja_projekt.Repositories.HousingPricesRepository;
import pl.pollub.integracja_projekt.Utils.ExcelReader.ExcelReader;

import java.util.List;

@Service
@Transactional
public class HousingPricesService {

    private final HousingPricesRepository repository;

    @Autowired
    public HousingPricesService(HousingPricesRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    public void loadHousingPrices(){
        try {
            ExcelReader reader = new ExcelReader("Data/ceny_mieszkan.xlsx");
            List<List<String>> list;
            list = reader.getArr();

            list.stream().skip(1).forEach(row -> {
                try {
                    HousingPrices housingPrices = new HousingPrices();
                    housingPrices.setName(row.get(1));
                    housingPrices.setTransaction(row.get(2));
                    housingPrices.setSurface(row.get(3));
                    housingPrices.setYear(Integer.parseInt(row.get(4)));
                    housingPrices.setPrice((int)Double.parseDouble((row.get(5).equals("-")) ? "0" : row.get(5)));

                    repository.save(housingPrices);
                } catch (NumberFormatException e) {
                    System.err.println("Error parsing row: " + row + ". Error: " + e.getMessage());
                }
            });
        } catch (RuntimeException e){
            System.err.println("Error loading housing prices: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<HousingPrices> getHousingPricesByName(String name){
        return repository.findByName(name);
    }

    public List<HousingPrices> getHousingPricesByTransaction(String transaction){
        return repository.findByTransaction(transaction);
    }

    public List<HousingPrices> getHousingPricesBySurface(String surface){
        return repository.findBySurface(surface);
    }

    public List<HousingPrices> getHousingPricesByYear(Integer year){
        return repository.findByYear(year);
    }

    public List<HousingPrices> getHousingPricesByYearNameTransaction(Integer year, String name, String transaction){
        return repository.findByYearAndNameAndTransaction(year, name, transaction);
    }

    public List<HousingPrices> getHousingPricesByNameTransactionSurface(String name, String transaction, String surface){
        return repository.findByNameAndTransactionAndSurface(name, transaction, surface);
    }

    public List<HousingPrices> getHousingPrices(Integer year, String name, String transaction, String surface){
        return repository.findByYearAndNameAndTransactionAndSurface(year, name, transaction, surface);
    }

    public List<HousingPrices> getHousingPricesByYearSurface(Integer year, String surface){
        return repository.findByYearAndSurface(year, surface);
    }

    public List<HousingPrices> getHousingPricesByNameTransaction(String name, String transaction){
        return repository.findByNameAndTransaction(name, transaction);
    }
}
