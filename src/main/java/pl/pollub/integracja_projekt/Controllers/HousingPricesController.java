package pl.pollub.integracja_projekt.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.pollub.integracja_projekt.Models.HousingPrices;
import pl.pollub.integracja_projekt.Repositories.HousingPricesRepository;
import pl.pollub.integracja_projekt.Services.HousingPricesService;

import java.util.List;

@RestController
@RequestMapping("/api/housingPrices")
@AllArgsConstructor
public class HousingPricesController {

    private final HousingPricesRepository repository;
    private final HousingPricesService service;

    @GetMapping("/")
    List<HousingPrices> getHousingPrices(@RequestParam(value = "year", required = false) Integer year, @RequestParam(value = "name", required = false) String name,
                                         @RequestParam(value = "transaction", required = false) String transaction, @RequestParam(value = "surface", required = false) String surface){
        if(year == null && surface == null && transaction == null){
            return service.getHousingPricesByName(name);
        }

        if(transaction == null && name == null){
            return service.getHousingPricesByYearSurface(year, surface);
        }

        if(year == null && surface == null){
            return service.getHousingPricesByNameTransaction(name, transaction);
        }

        if(surface == null){
            return service.getHousingPricesByYearNameTransaction(year, name, transaction);
        }

        if(year == null){
            return  service.getHousingPricesByNameTransactionSurface(name, transaction, surface);
        }

        return service.getHousingPrices(year, name, transaction, surface);
    }
}
