package co.medina.test.pricecatalogapi.adapter.persistence;

import co.medina.test.pricecatalogapi.domain.entities.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface PriceRepository extends JpaRepository<Price, Integer> {
    @Query(value = "SELECT p.* FROM Price p " +
            "WHERE p.brand_id = :brandId " +
            "AND p.product_id = :productId " +
            "AND p.start_date <= :requestDate " +
            "AND p.end_date >= :requestDate " +
            "ORDER BY p.priority DESC " +
            "LIMIT 1", nativeQuery = true)
    Optional<Price> findFirstByBrandIdAndProductIdAndDate(
            @Param("brandId") Integer brandId,
            @Param("productId") Integer productId,
            @Param("requestDate") LocalDateTime requestDate);
}
