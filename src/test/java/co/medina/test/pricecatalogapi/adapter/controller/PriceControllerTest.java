package co.medina.test.pricecatalogapi.adapter.controller;

import co.medina.test.pricecatalogapi.adapter.controller.dto.PriceDTO;
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

@ExtendWith(MockitoExtension.class)
class PriceControllerTest {

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
        PriceDTO priceMock = new PriceDTO(35455,1, 1, LocalDateTime.now(), LocalDateTime.now().plusDays(1), 40.00);
        Mockito.when(priceService.findByBrandIdAndProductIdAndDate(Mockito.anyInt(), Mockito.anyInt(), Mockito.any(LocalDateTime.class))).thenReturn(priceMock);

        priceController.findByBrandIdAndProductIdAndDate(1, 35455, LocalDateTime.now());

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/api/price")
                        .param("brandId", "1")
                        .param("productId", "35455")
                        .param("requestDate", "2020-06-14T10:00:00")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));

    }
}