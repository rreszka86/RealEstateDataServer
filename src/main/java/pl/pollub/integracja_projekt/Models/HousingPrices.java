package pl.pollub.integracja_projekt.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "housingPrices")
public class HousingPrices {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String transaction;
    private String surface;
    private Integer year;
    private Integer price;
}
