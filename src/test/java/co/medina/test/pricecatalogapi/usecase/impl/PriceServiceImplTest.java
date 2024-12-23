package co.medina.test.pricecatalogapi.usecase.impl;

import co.medina.test.pricecatalogapi.adapter.controller.dto.PriceDTO;
import co.medina.test.pricecatalogapi.adapter.persistence.PriceRepository;
import co.medina.test.pricecatalogapi.domain.entities.Price;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class PriceServiceImplTest {
    @InjectMocks
    private PriceServiceImpl priceService;

    @Mock
    private PriceRepository priceRepository;

    private AutoCloseable mocks;

    @BeforeEach
    void setUp() {
        mocks = MockitoAnnotations.openMocks(this);
        priceService = new PriceServiceImpl(priceRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        mocks.close();
    }

    @Test
    void test1_findPrice_at_10am_on_14th() {
        LocalDateTime requestDate = LocalDateTime.of(2020, 6, 14, 10, 0);
        LocalDateTime startDate = LocalDateTime.of(2020, 6, 14, 8, 0); // Inicio de validez
        LocalDateTime endDate = LocalDateTime.of(2020, 6, 14, 20, 0); // Fin de validez
        Optional<Price> priceMock = Optional.of(new Price(1, startDate, endDate, 1, 35455, 1, 35.50, "EUR"));
        Mockito.when(priceRepository.findFirstByBrandIdAndProductIdAndDate(Mockito.anyInt(), Mockito.anyInt(), Mockito.any(LocalDateTime.class))).thenReturn(priceMock);

        PriceDTO pricesResponse = priceService.findByBrandIdAndProductIdAndDate(1, 35455, requestDate);
        Assertions.assertNotNull(pricesResponse);
    }

    @Test
    void test2_findPrice_at_16pm_on_14th() {
        LocalDateTime requestDate = LocalDateTime.of(2020, 6, 14, 16, 0);
        LocalDateTime startDate = LocalDateTime.of(2020, 6, 14, 15, 0);
        LocalDateTime endDate = LocalDateTime.of(2020, 6, 14, 20, 0);
        Optional<Price> priceMock = Optional.of(new Price(1, startDate, endDate, 1, 35455, 1, 25.45, "EUR"));
        Mockito.when(priceRepository.findFirstByBrandIdAndProductIdAndDate(Mockito.anyInt(), Mockito.anyInt(), Mockito.any(LocalDateTime.class))).thenReturn(priceMock);

        PriceDTO pricesResponse = priceService.findByBrandIdAndProductIdAndDate(1, 35455, requestDate);
        Assertions.assertNotNull(pricesResponse);
    }

    @Test
    void test3_findPrice_at_21pm_on_14th() {
        LocalDateTime requestDate = LocalDateTime.of(2020, 6, 14, 21, 0);
        LocalDateTime startDate = LocalDateTime.of(2020, 6, 14, 15, 0);
        LocalDateTime endDate = LocalDateTime.of(2020, 6, 14, 20, 0);
        Optional<Price> priceMock = Optional.of(new Price(1, startDate, endDate, 1, 35455, 1, 35.50, "EUR"));
        Mockito.when(priceRepository.findFirstByBrandIdAndProductIdAndDate(Mockito.anyInt(), Mockito.anyInt(), Mockito.any(LocalDateTime.class))).thenReturn(priceMock);

        PriceDTO pricesResponse = priceService.findByBrandIdAndProductIdAndDate(1, 35455, requestDate);
        Assertions.assertNotNull(pricesResponse);
    }

    @Test
    void test4_findPrice_at_10am_on_15th() {
        LocalDateTime requestDate = LocalDateTime.of(2020, 6, 15, 10, 0);
        LocalDateTime startDate = LocalDateTime.of(2020, 6, 15, 9, 0);
        LocalDateTime endDate = LocalDateTime.of(2020, 6, 15, 20, 0);
        Optional<Price> priceMock = Optional.of(new Price(1, startDate, endDate, 1, 35455, 1, 30.50, "EUR"));
        Mockito.when(priceRepository.findFirstByBrandIdAndProductIdAndDate(Mockito.anyInt(), Mockito.anyInt(), Mockito.any(LocalDateTime.class))).thenReturn(priceMock);

        PriceDTO pricesResponse = priceService.findByBrandIdAndProductIdAndDate(1, 35455, requestDate);
        Assertions.assertNotNull(pricesResponse);
    }

    @Test
    void test5_findPrice_at_21pm_on_16th() {
        LocalDateTime requestDate = LocalDateTime.of(2020, 6, 16, 21, 0);
        LocalDateTime startDate = LocalDateTime.of(2020, 6, 16, 20, 0);
        LocalDateTime endDate = LocalDateTime.of(2020, 6, 16, 21, 0);
        Optional<Price> priceMock = Optional.of(new Price(1, startDate, endDate, 1, 35455, 1, 38.95, "EUR"));
        Mockito.when(priceRepository.findFirstByBrandIdAndProductIdAndDate(Mockito.anyInt(), Mockito.anyInt(), Mockito.any(LocalDateTime.class))).thenReturn(priceMock);

        PriceDTO pricesResponse = priceService.findByBrandIdAndProductIdAndDate(1, 35455, requestDate);
        Assertions.assertNotNull(pricesResponse);
    }

}
