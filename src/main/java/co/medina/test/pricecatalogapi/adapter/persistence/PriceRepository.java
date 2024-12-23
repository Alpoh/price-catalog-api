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
    @Query(value = "SELECT p FROM Price p " +
            "WHERE p.brandId = :brandId " +
            "AND p.productId = :productId " +
            "AND p.startDate <= :requestDate " +
            "AND p.endDate >= :requestDate " +
            "ORDER BY p.priority DESC")
    Optional<Price> findFirstByBrandIdAndProductIdAndDate(
            @Param("brandId") Integer brandId,
            @Param("productId") Integer productId,
            @Param("requestDate") LocalDateTime requestDate);
}
