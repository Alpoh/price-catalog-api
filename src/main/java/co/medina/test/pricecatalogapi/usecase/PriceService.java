package co.medina.test.pricecatalogapi.usecase;

import co.medina.test.pricecatalogapi.adapter.controller.dto.PriceDTO;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceService {

    Optional<PriceDTO> findByBrandIdAndProductIdAndDate(Integer brandId, Integer productId, LocalDateTime requestDate);
}
