package co.medina.test.pricecatalogapi.usecase;

import co.medina.test.pricecatalogapi.adapter.controller.dto.PriceDTO;

import java.time.LocalDateTime;

public interface PriceService {

    PriceDTO findByBrandIdAndProductIdAndDate(Integer brandId, Integer productId, LocalDateTime requestDate);
}
