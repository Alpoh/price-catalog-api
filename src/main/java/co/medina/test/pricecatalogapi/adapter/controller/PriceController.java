package co.medina.test.pricecatalogapi.adapter.controller;

import co.medina.test.pricecatalogapi.domain.Price;
import co.medina.test.pricecatalogapi.usecase.PriceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api")
@Slf4j
@Tag(name = "Price Controller", description = "Controller for managing prices")
public class PriceController {
    private final PriceService priceService;

    @Operation(summary = "Get prices by brand and product ID",
            description = "Retrieve a list of prices for a specific brand and product.")
    @GetMapping("/price/{brandId}/{productId}")
    public ResponseEntity<List<Price>> findByBrandIdAndProductId(
            @Parameter(description = "ID of the brand") @PathVariable int brandId,
            @Parameter(description = "ID of the product") @PathVariable int productId) {
        log.debug("Fetching prices for brand ID: {} and product ID: {}", brandId, productId);
        List<Price> prices = priceService.findByBrandIdAndProductId(brandId, productId);
        return ResponseEntity.ok(prices);
    }
}
