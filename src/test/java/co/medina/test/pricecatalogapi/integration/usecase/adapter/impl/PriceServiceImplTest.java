package co.medina.test.pricecatalogapi.integration.usecase.adapter.impl;

import co.medina.test.pricecatalogapi.adapter.controller.dto.PriceDTO;
import co.medina.test.pricecatalogapi.adapter.persistence.PriceRepository;
import co.medina.test.pricecatalogapi.domain.entities.Price;
import co.medina.test.pricecatalogapi.usecase.impl.PriceServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.Optional;

@DataJpaTest
class PriceServiceImplTest {

    private PriceServiceImpl priceService;

    @Autowired
    private PriceRepository priceRepository;

    @BeforeEach
    void setUp() {
        priceService = new PriceServiceImpl(priceRepository);
    }

    @Test
    void testFindByBrandIdAndProductIdAndDate_case1() {
        priceRepository.save(new Price(1, LocalDateTime.of(2020, 6, 14, 0, 0),
                LocalDateTime.of(2020, 12, 31, 23, 59), 1, 35455, 0, 35.50, "EUR"));

        LocalDateTime requestDate = LocalDateTime.of(2020, 6, 14, 10, 0);
        Optional<PriceDTO> result = priceService.findByBrandIdAndProductIdAndDate(1, 35455, requestDate);

        Assertions.assertThat(result).isPresent();
        Assertions.assertThat(result.get().price()).isEqualTo(35.50);
        Assertions.assertThat(result.get().brandId()).isEqualTo(1);
    }

    @Test
    void testFindByBrandIdAndProductIdAndDate_case2() {
        priceRepository.save(new Price(1, LocalDateTime.of(2020, 6, 14, 15, 0),
                LocalDateTime.of(2020, 6, 14, 18, 30), 1, 35455, 0,
                25.45, "EUR"));

        LocalDateTime requestDate = LocalDateTime.of(2020, 6, 14, 16, 0);
        Optional<PriceDTO> result = priceService.findByBrandIdAndProductIdAndDate(1, 35455, requestDate);

        Assertions.assertThat(result).isPresent();
        Assertions.assertThat(result.get().price()).isEqualTo(25.45);
        Assertions.assertThat(result.get().brandId()).isEqualTo(1);
    }

    @Test
    void testFindByBrandIdAndProductIdAndDate_case3() {
        priceRepository.save(new Price(1, LocalDateTime.of(2020, 6, 14, 0, 0),
                LocalDateTime.of(2020, 12, 31, 23, 59), 1, 35455, 0,
                35.50, "EUR"));

        LocalDateTime requestDate = LocalDateTime.of(2020, 6, 14, 21, 0);
        Optional<PriceDTO> result = priceService.findByBrandIdAndProductIdAndDate(1, 35455, requestDate);

        Assertions.assertThat(result).isPresent();
        Assertions.assertThat(result.get().price()).isEqualTo(35.50);
        Assertions.assertThat(result.get().brandId()).isEqualTo(1);
    }

    @Test
    void testFindByBrandIdAndProductIdAndDate_case4() {
        priceRepository.save(new Price(1, LocalDateTime.of(2020, 6, 15, 0, 0),
                LocalDateTime.of(2020, 6, 15, 11, 0),1, 35455, 1,
                30.50, "EUR"));

        LocalDateTime requestDate = LocalDateTime.of(2020, 6, 15, 10, 0);
        Optional<PriceDTO> result = priceService.findByBrandIdAndProductIdAndDate(1, 35455, requestDate);

        Assertions.assertThat(result).isPresent();
        Assertions.assertThat(result.get().price()).isEqualTo(30.50);
        Assertions.assertThat(result.get().brandId()).isEqualTo(1);
    }

    @Test
    void testFindByBrandIdAndProductIdAndDate_case5() {
        priceRepository.save(new Price(1, LocalDateTime.of(2020, 6, 16, 20, 0),
                LocalDateTime.of(2020, 6, 16, 22, 0),1, 35455, 1,
                38.95, "EUR"));

        LocalDateTime requestDate = LocalDateTime.of(2020, 6, 16, 21, 0);
        Optional<PriceDTO> result = priceService.findByBrandIdAndProductIdAndDate(1, 35455, requestDate);

        Assertions.assertThat(result).isPresent();
        Assertions.assertThat(result.get().price()).isEqualTo(38.95);
        Assertions.assertThat(result.get().brandId()).isEqualTo(1);
    }
}
