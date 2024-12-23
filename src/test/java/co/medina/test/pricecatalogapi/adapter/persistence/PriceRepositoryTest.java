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
    void test1_findPrice_at_10am_on_14th() {
        LocalDateTime requestDate = LocalDateTime.of(2020, 6, 14, 10, 0);
        Optional<Price> price = priceRepository.findFirstByBrandIdAndProductIdAndDate(1, 35455, requestDate);

        Assertions.assertTrue(price.isPresent());
        Assertions.assertEquals(1, price.get().getBrandId());
        Assertions.assertEquals(35455, price.get().getProductId());
        Assertions.assertEquals(35.50, price.get().getPrice().doubleValue());
    }

    @Test
    void test2_findPrice_at_16pm_on_14th() {
        LocalDateTime requestDate = LocalDateTime.of(2020, 6, 14, 16, 0);
        Optional<Price> price = priceRepository.findFirstByBrandIdAndProductIdAndDate(1, 35455, requestDate);

        Assertions.assertTrue(price.isPresent());
        Assertions.assertEquals(1, price.get().getBrandId());
        Assertions.assertEquals(35455, price.get().getProductId());
        Assertions.assertEquals(25.45, price.get().getPrice().doubleValue());
    }

    @Test
    void test3_findPrice_at_21pm_on_14th() {
        LocalDateTime requestDate = LocalDateTime.of(2020, 6, 14, 21, 0);
        Optional<Price> price = priceRepository.findFirstByBrandIdAndProductIdAndDate(1, 35455, requestDate);

        Assertions.assertTrue(price.isPresent());
        Assertions.assertEquals(1, price.get().getBrandId());
        Assertions.assertEquals(35455, price.get().getProductId());
        Assertions.assertEquals(35.50, price.get().getPrice().doubleValue());
    }

    @Test
    void test4_findPrice_at_10am_on_15th() {
        LocalDateTime requestDate = LocalDateTime.of(2020, 6, 15, 10, 0);
        Optional<Price> price = priceRepository.findFirstByBrandIdAndProductIdAndDate(1, 35455, requestDate);

        Assertions.assertTrue(price.isPresent());
        Assertions.assertEquals(1, price.get().getBrandId());
        Assertions.assertEquals(35455, price.get().getProductId());
        Assertions.assertEquals(30.50, price.get().getPrice().doubleValue());
    }

    @Test
    void test5_findPrice_at_21pm_on_16th() {
        LocalDateTime requestDate = LocalDateTime.of(2020, 6, 16, 21, 0);
        Optional<Price> price = priceRepository.findFirstByBrandIdAndProductIdAndDate(1, 35455, requestDate);

        Assertions.assertTrue(price.isPresent());
        Assertions.assertEquals(1, price.get().getBrandId());
        Assertions.assertEquals(35455, price.get().getProductId());
        Assertions.assertEquals(38.95, price.get().getPrice().doubleValue());
    }
}