package pl.pollub.integracja_projekt.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pollub.integracja_projekt.Models.InterestRates;
import pl.pollub.integracja_projekt.Repositories.InterestRatesRepository;
import pl.pollub.integracja_projekt.Services.InterestRatesService;

import java.util.List;

@RestController
@RequestMapping("/api/interestRates")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class InterestRatesController {

    public final InterestRatesRepository repository;
    public final InterestRatesService service;

    // localhost:8080/?fromDate=2020-03-12

    @GetMapping("/")
    List<InterestRates> getAllRates(@RequestParam(value = "fromDate", required = false) String fromDate, @RequestParam(value = "toDate", required = false) String toDate){
        if(fromDate != null && toDate != null){
            return service.getInterestRatesByDateRange(fromDate, toDate);
        }
        if(fromDate != null){
            return service.getInterestRatesFromDate(fromDate);
        }
        return repository.findAll();
    }

}
