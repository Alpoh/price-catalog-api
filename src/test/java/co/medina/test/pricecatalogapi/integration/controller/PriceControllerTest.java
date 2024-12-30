package co.medina.test.pricecatalogapi.integration.controller;

import co.medina.test.pricecatalogapi.adapter.persistence.PriceRepository;
import co.medina.test.pricecatalogapi.domain.entities.Price;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PriceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PriceRepository priceRepository;

    @BeforeEach
    void setUp() {
        priceRepository.deleteAll();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        priceRepository.save(new Price(1, LocalDateTime.parse("2020-06-14 00:00:00", formatter),
                LocalDateTime.parse("2020-12-31 23:59:59", formatter), 1, 35455, 0, 35.50, "EUR"));
        priceRepository.save(new Price(1, LocalDateTime.parse("2020-06-14 15:00:00", formatter),
                LocalDateTime.parse("2020-06-14 18:30:00", formatter), 2, 35455, 1, 25.45, "EUR"));
        priceRepository.save(new Price(1, LocalDateTime.parse("2020-06-15 00:00:00", formatter),
                LocalDateTime.parse("2020-06-15 11:00:00", formatter), 3, 35455, 1, 30.50, "EUR"));
        priceRepository.save(new Price(1, LocalDateTime.parse("2020-06-15 16:00:00", formatter),
                LocalDateTime.parse("2020-12-31 23:59:59", formatter), 4, 35455, 1, 38.95, "EUR"));
    }

    @Test
    void test1_findPrice_at_10am_on_14th() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/api/price")
                        .param("brandId", "1")
                        .param("productId", "35455")
                        .param("requestDate", "2020-06-14T10:00:00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(35.50))
                .andExpect(jsonPath("$.curr").value("EUR"));
    }

    @Test
    void test2_findPrice_at_16pm_on_14th() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/api/price")
                        .param("brandId", "1")
                        .param("productId", "35455")
                        .param("requestDate", "2020-06-14T16:00:00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(25.45))
                .andExpect(jsonPath("$.curr").value("EUR"));
    }

    @Test
    void test3_findPrice_at_21pm_on_14th() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/api/price")
                        .param("brandId", "1")
                        .param("productId", "35455")
                        .param("requestDate", "2020-06-14T21:00:00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(35.50))
                .andExpect(jsonPath("$.curr").value("EUR"));
    }

    @Test
    void test4_findPrice_at_10am_on_15th() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/api/price")
                        .param("brandId", "1")
                        .param("productId", "35455")
                        .param("requestDate", "2020-06-15T10:00:00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(30.50))
                .andExpect(jsonPath("$.curr").value("EUR"));
    }

    @Test
    void test5_findPrice_at_21pm_on_16th() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/api/price")
                        .param("brandId", "1")
                        .param("productId", "35455")
                        .param("requestDate", "2020-06-16T21:00:00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(38.95))
                .andExpect(jsonPath("$.curr").value("EUR"));
    }
}
