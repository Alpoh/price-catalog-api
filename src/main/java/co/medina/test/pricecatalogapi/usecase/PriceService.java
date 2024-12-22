package co.medina.test.pricecatalogapi.usecase;

import co.medina.test.pricecatalogapi.domain.Price;

import java.util.List;

public interface PriceService {
    List<Price> findByBrandIdAndProductId(Integer brandId, Integer productId);
}
