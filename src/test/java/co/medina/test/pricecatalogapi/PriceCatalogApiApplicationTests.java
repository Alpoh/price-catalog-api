package co.medina.test.pricecatalogapi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PriceCatalogApiApplicationTests {

    private PriceCatalogApiApplication application;

    @BeforeEach
    void setUp() {
        application = new PriceCatalogApiApplication();
    }

    @Test
    void contextLoads() {
        Assertions.assertNotNull(application);
    }

}
