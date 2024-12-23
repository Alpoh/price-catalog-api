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
    void findByBrandIdAndProductId() {
        Optional<Price> priceMock = Optional.of(new Price(1, LocalDateTime.now(), LocalDateTime.now().plusDays(1), 1, 35455, 1, 40.00, "EUR"));
        LocalDateTime testDateTime = LocalDateTime.of(2020, 6, 14, 10, 0);
        Mockito.when(priceRepository.findFirstByBrandIdAndProductIdAndDate(Mockito.anyInt(), Mockito.anyInt(), Mockito.any(LocalDateTime.class))).thenReturn(priceMock);

        PriceDTO pricesResponse = priceService.findByBrandIdAndProductIdAndDate(1, 35455, testDateTime);
        Assertions.assertNotNull(pricesResponse);
    }
}