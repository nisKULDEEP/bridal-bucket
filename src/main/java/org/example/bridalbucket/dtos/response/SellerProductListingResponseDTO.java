package org.example.bridalbucket.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.bridalbucket.models.Seller;


@Getter
@Setter
@Builder(toBuilder = true)
public class SellerProductListingResponseDTO {

    private Long id;

    private Seller seller;

    private double price;

    private double originalPrice;

    private int quantity;

    @JsonProperty("isAvailable")
    private boolean isAvailable;

}


