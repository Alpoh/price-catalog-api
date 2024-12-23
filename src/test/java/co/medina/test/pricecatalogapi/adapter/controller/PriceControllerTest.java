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
    void test1_findPrice_at_10am_on_14th() throws Exception {
        LocalDateTime requestDate = LocalDateTime.of(2020, 6, 14, 10, 0);
        PriceDTO mockPriceDTO = new PriceDTO(35455, 1, 1, LocalDateTime.of(2020, 6, 14, 10, 0), LocalDateTime.of(2020, 6, 14, 20, 0), 40.00);

        Mockito.when(priceService.findByBrandIdAndProductIdAndDate(1, 35455, requestDate)).thenReturn(mockPriceDTO);

        priceController.findByBrandIdAndProductIdAndDate(1, 35455, requestDate);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/api/price")
                        .param("brandId", "1")
                        .param("productId", "35455")
                        .param("requestDate", requestDate.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void test2_findPrice_at_16pm_on_14th() throws Exception {
        LocalDateTime requestDate = LocalDateTime.of(2020, 6, 14, 16, 0);
        PriceDTO mockPriceDTO = new PriceDTO(35455, 1, 1, LocalDateTime.of(2020, 6, 14, 10, 0), LocalDateTime.of(2020, 6, 14, 20, 0), 35.50);

        Mockito.when(priceService.findByBrandIdAndProductIdAndDate(1, 35455, requestDate)).thenReturn(mockPriceDTO);

        priceController.findByBrandIdAndProductIdAndDate(1, 35455, requestDate);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/api/price")
                        .param("brandId", "1")
                        .param("productId", "35455")
                        .param("requestDate", requestDate.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void test3_findPrice_at_21pm_on_14th() throws Exception {
        LocalDateTime requestDate = LocalDateTime.of(2020, 6, 14, 21, 0);
        PriceDTO mockPriceDTO = new PriceDTO(35455, 1, 1, LocalDateTime.of(2020, 6, 14, 21, 0), LocalDateTime.of(2020, 6, 15, 18, 0), 30.00);

        Mockito.when(priceService.findByBrandIdAndProductIdAndDate(1, 35455, requestDate)).thenReturn(mockPriceDTO);

        priceController.findByBrandIdAndProductIdAndDate(1, 35455, requestDate);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/api/price")
                        .param("brandId", "1")
                        .param("productId", "35455")
                        .param("requestDate", requestDate.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void test4_findPrice_at_10am_on_15th() throws Exception {
        LocalDateTime requestDate = LocalDateTime.of(2020, 6, 15, 10, 0);
        PriceDTO mockPriceDTO = new PriceDTO(35455, 1, 1, LocalDateTime.of(2020, 6, 15, 8, 0), LocalDateTime.of(2020, 6, 15, 18, 0), 25.00);

        Mockito.when(priceService.findByBrandIdAndProductIdAndDate(1, 35455, requestDate)).thenReturn(mockPriceDTO);

        priceController.findByBrandIdAndProductIdAndDate(1, 35455, requestDate);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/api/price")
                        .param("brandId", "1")
                        .param("productId", "35455")
                        .param("requestDate", requestDate.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void test5_findPrice_at_21pm_on_16th() throws Exception {
        LocalDateTime requestDate = LocalDateTime.of(2020, 6, 16, 21, 0);
        PriceDTO mockPriceDTO = new PriceDTO(35455, 1, 1, LocalDateTime.of(2020, 6, 16, 19, 0), LocalDateTime.of(2020, 6, 16, 22, 0), 20.00);

        Mockito.when(priceService.findByBrandIdAndProductIdAndDate(1, 35455, requestDate)).thenReturn(mockPriceDTO);

        priceController.findByBrandIdAndProductIdAndDate(1, 35455, requestDate);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/api/price")
                        .param("brandId", "1")
                        .param("productId", "35455")
                        .param("requestDate", requestDate.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

}