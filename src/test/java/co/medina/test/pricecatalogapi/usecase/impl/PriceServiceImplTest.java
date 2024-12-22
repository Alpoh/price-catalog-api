package co.medina.test.pricecatalogapi.usecase.impl;

import co.medina.test.pricecatalogapi.adapter.persistence.PriceRepository;
import co.medina.test.pricecatalogapi.domain.Price;
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
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class PriceServiceImplTest {
    @InjectMocks
    private PriceServiceImpl priceService;

    @Mock
    private PriceRepository priceRepository;

    private AutoCloseable mocks;
    private List<Price> priceMock = new ArrayList<>();

    @BeforeEach
    void setUp() {
        mocks = MockitoAnnotations.openMocks(this);
        priceMock = List.of(new Price(1, LocalDateTime.now(), LocalDateTime.now().plusDays(1), 1, 35455, 1, 40.00, "EUR"));
        priceService = new PriceServiceImpl(priceRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        mocks.close();
    }

    @Test
    void findByBrandIdAndProductId() {
        Mockito.when(priceRepository.findByBrandIdAndProductId(Mockito.anyInt(), Mockito.anyInt())).thenReturn(priceMock);

        List<Price> pricesResponse = priceService.findByBrandIdAndProductId(1, 35455);
        Assertions.assertNotNull(pricesResponse);
        Assertions.assertEquals(priceMock.size(), pricesResponse.size());
        Assertions.assertEquals(priceMock.get(0), pricesResponse.get(0));
    }
}