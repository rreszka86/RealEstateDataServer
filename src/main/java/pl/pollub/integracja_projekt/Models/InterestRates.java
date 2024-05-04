package pl.pollub.integracja_projekt.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "interestRates")
public class InterestRates {
    @Id
    @GeneratedValue
    private Integer id;
    @Temporal(TemporalType.DATE)
    private String date;
    private Double refRate;
    private Double pawnRate;
    private Double depRate;
    private Double redRate;
    private Double disRate;
}
