package co.medina.test.pricecatalogapi.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table( name = "PRICE")
@Data
@RequiredArgsConstructor
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private final Integer brandId;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;
    private final Integer priceList;
    private final Integer productId;
    private final Integer priority;
    private final Double price;
    private final String curr;

    public Price() {
        this.startDate = LocalDateTime.now();
        this.endDate = LocalDateTime.now();
        this.priceList = 100;
        this.productId = 1;
        this.priority = 1;
        this.price = 0.0;
        this.curr = "";
        this.brandId = 1;
    }
}
