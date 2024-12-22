package co.medina.test.pricecatalogapi.adapter.persistence;

import co.medina.test.pricecatalogapi.domain.Price;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

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
        // Guardar un precio
        Price price = new Price(1, LocalDateTime.now(), LocalDateTime.now().plusDays(1), 1, 35455, 1, 40.00, "EUR");
        priceRepository.save(price);

        // Recuperar el precio
        List<Price> prices = priceRepository.findByBrandIdAndProductId(1, 35455);
        Assertions.assertFalse(prices.isEmpty());
        Assertions.assertEquals(5, prices.size());
        Assertions.assertEquals(35.50, prices.get(0).getPrice());
    }
}