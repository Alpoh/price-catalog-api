package co.medina.test.pricecatalogapi.adapter.controller;

import co.medina.test.pricecatalogapi.domain.Price;
import co.medina.test.pricecatalogapi.usecase.PriceService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class PriceControllerTest {

    private final List<Price> priceMock = List.of(new Price(1, LocalDateTime.now(), LocalDateTime.now().plusDays(1), 1, 35455, 1, 40.00, "EUR"));
    @InjectMocks
    private PriceController priceController;
    @Mock
    private PriceService priceService;
    private MockMvc mockMvc;
    private AutoCloseable mocks;

    @BeforeEach
    void setUp() {
        mocks = MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(priceController).build();
        priceController = new PriceController(priceService);
    }

    @AfterEach
    void tearDown() throws Exception {
        mocks.close();
    }

    @Test
    void findByBrandIdAndProductId() throws Exception {
        Mockito.when(priceService.findByBrandIdAndProductId(Mockito.anyInt(), Mockito.anyInt())).thenReturn(priceMock);

        priceController.findByBrandIdAndProductId(1, 35455);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/api/price/1/35455")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }
}