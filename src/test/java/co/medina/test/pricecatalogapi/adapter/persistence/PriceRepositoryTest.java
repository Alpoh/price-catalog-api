package co.medina.test.pricecatalogapi.adapter.persistence;

import co.medina.test.pricecatalogapi.domain.entities.Price;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.Optional;

@DataJpaTest
class PriceRepositoryTest {

    @Autowired
    private PriceRepository priceRepository;

    @Test
    void loadContext(){
        Assertions.assertNotNull(priceRepository);
    }

    @Test
    void shouldSaveAndRetrievePrice() {
        LocalDateTime testDateTime = LocalDateTime.of(2020, 6, 14, 10, 0);
        Price price = new Price(1, LocalDateTime.now(), LocalDateTime.now().plusDays(1), 1, 35455, 1, 40.00, "EUR");
        priceRepository.save(price);
        Optional<Price> priceResponse = priceRepository.findFirstByBrandIdAndProductIdAndDate(1, 35455, testDateTime);
        Assertions.assertNotNull(priceResponse);
    }
}