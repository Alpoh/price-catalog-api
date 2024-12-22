package co.medina.test.pricecatalogapi.usecase.impl;

import co.medina.test.pricecatalogapi.adapter.persistence.PriceRepository;
import co.medina.test.pricecatalogapi.domain.Price;
import co.medina.test.pricecatalogapi.usecase.PriceService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class PriceServiceImpl implements PriceService {
    private final PriceRepository priceRepository;

    @Override
    public List<Price> findByBrandIdAndProductId(Integer brandId, Integer productId) {
        log.debug("PriceServiceImpl.findByBrandIdAndProductId: brandId::{}, productId::{}", brandId, productId);
        return priceRepository.findByBrandIdAndProductId(brandId, productId);
    }
}
