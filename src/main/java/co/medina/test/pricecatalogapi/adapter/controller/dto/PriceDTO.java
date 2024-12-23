package co.medina.test.pricecatalogapi.adapter.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;


public record PriceDTO(@JsonProperty("product_id") Integer productId, @JsonProperty("brand_id") Integer brandId,
                       @JsonProperty("price_list") Integer priceList,
                       @JsonProperty("start_date") LocalDateTime startDate,
                       @JsonProperty("end_date") LocalDateTime endDate, @JsonProperty("price") Double price) {
}
