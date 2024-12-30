package co.medina.test.pricecatalogapi.adapter.controller;

import co.medina.test.pricecatalogapi.adapter.controller.dto.PriceDTO;
import co.medina.test.pricecatalogapi.adapter.exception.PriceNotFoundException;
import co.medina.test.pricecatalogapi.usecase.PriceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api")
@Slf4j
@Tag(name = "Price Controller", description = "Controller for managing prices")
@Validated
public class PriceController {
    private final PriceService priceService;

    @Operation(summary = "Get prices by brand, product ID and request date",
            description = "Retrieve a price for a specific brand, product and date.")
    @GetMapping("/price")
    public ResponseEntity<PriceDTO> findByBrandIdAndProductIdAndDate(
            @Parameter(description = "ID of the brand") @RequestParam("brandId") @NotNull Integer brandId,
            @Parameter(description = "ID of the product") @RequestParam("productId") @NotNull Integer productId,
            @Parameter(description = "Request date")
            @RequestParam("requestDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @NotNull LocalDateTime requestDate) {

        log.debug("Controller fetching price for brand ID:: {}, product ID:: {} and request Date:: {}", brandId, productId, requestDate);

        return priceService.findByBrandIdAndProductIdAndDate(brandId, productId, requestDate)
                .map(price -> ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(price))
                .orElseThrow(() -> new PriceNotFoundException("Price not found for brand ID " + brandId + " and product ID " + productId));
    }
}
