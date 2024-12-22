package co.medina.test.pricecatalogapi.adapter.persistence;

import co.medina.test.pricecatalogapi.domain.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<Price, Integer> {
    List<Price> findByBrandIdAndProductId(Integer brandId, Integer productId);
}
