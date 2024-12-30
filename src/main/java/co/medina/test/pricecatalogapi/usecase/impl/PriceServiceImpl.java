package co.medina.test.pricecatalogapi.usecase.impl;

import co.medina.test.pricecatalogapi.adapter.controller.dto.PriceDTO;
import co.medina.test.pricecatalogapi.adapter.persistence.PriceRepository;
import co.medina.test.pricecatalogapi.domain.mappers.PriceMapper;
import co.medina.test.pricecatalogapi.usecase.PriceService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class PriceServiceImpl implements PriceService {
    private final PriceRepository priceRepository;

    @Override
    public Optional<PriceDTO> findByBrandIdAndProductIdAndDate(Integer brandId, Integer productId, LocalDateTime requestDate) {
        log.debug("Service fetching price for brand ID:: {}, product ID:: {} and request Date:: {}", brandId, productId, requestDate);

        return priceRepository.findFirstByBrandIdAndProductIdAndDate(brandId, productId, requestDate)
                .map(PriceMapper.INSTANCE::toDto);
    }
}
