package pl.pollub.integracja_projekt.Services;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pollub.integracja_projekt.Models.InterestRates;
import pl.pollub.integracja_projekt.Repositories.InterestRatesRepository;
import pl.pollub.integracja_projekt.Utils.XmlReader.InterestRatesDTO;
import pl.pollub.integracja_projekt.Utils.XmlReader.XmlReader;

import java.util.List;

@Service
public class InterestRatesService {

    private final InterestRatesRepository repository;

    @Autowired
    public InterestRatesService(InterestRatesRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    public void loadInterestRates() {
        XmlReader readXml = new XmlReader("src/main/resources/Data/stopy_procentowe_archiwum.xml");
        List<InterestRatesDTO> list;
        list = readXml.parseXML();

        list.forEach(interestRatesDTO -> {
            InterestRates interestRates = new InterestRates();
            interestRatesDTO.getEntryList().forEach(entry -> {
                interestRates.setDate(interestRatesDTO.getEffectiveFrom());
                switch (entry.getId()){
                    case "ref":
                        interestRates.setRefRate(Double.parseDouble(entry.getInterestPercentage()));
                        break;
                    case "lom":
                        interestRates.setPawnRate(Double.parseDouble(entry.getInterestPercentage()));
                        break;
                    case "dep":
                        interestRates.setDepRate(Double.parseDouble(entry.getInterestPercentage()));
                        break;
                    case "red":
                        interestRates.setRedRate(Double.parseDouble(entry.getInterestPercentage()));
                        break;
                    case "dys":
                        interestRates.setDisRate(Double.parseDouble(entry.getInterestPercentage()));
                        break;
                    default:
                        break;
                }
            });
            repository.save(interestRates);
        });

    }

    public List<InterestRates> getInterestRatesByDateRange(String fromDate, String toDate){
        return repository.findByDateBetween(fromDate, toDate);
    }

    public List<InterestRates> getInterestRatesFromDate(String fromDate){
        return repository.findByDateAfter(fromDate);
    }
}
