package org.example.bridalbucket.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.example.bridalbucket.models.Category;
import org.example.bridalbucket.models.Product;
import org.example.bridalbucket.models.SellerProductListing;
import org.example.bridalbucket.models.Seller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ProductResponseDTO {
    private String title;
    private String description;
    private double price;
    private String imageUrl;
    private Category category;
    private int quantity;

    @JsonProperty("isAvailable")
    private boolean isAvailable;
    private Seller seller;
    private List<SellerProductListingResponseDTO> otherSellerProductListings;

    public static ProductResponseDTO from (Product product){

        /**
         * This is a simple implementation of the form method. It is not the best implementation.
         * Currently, we are only returning the first listing of the product. This is not the best implementation.
         * We should calculate which seller is selling product at the lowest price and other ceriteria eg. rating, user reviews etc and return that seller's listing price and other details.
         * We can use cron job to calculate which seller is selling product at the lowest price and other creteria and store that information in the database.
         * So we not need to calculate this information every time we need to show the product details.
         */

        Optional<SellerProductListing> landingProductDetails = product.getSellerProductListings()
                .stream()
                .min(Comparator.comparingDouble(SellerProductListing::getPrice));



        if(landingProductDetails.isEmpty()){
            return null;
        }

        List<SellerProductListingResponseDTO> otherSellerListing = product.getSellerProductListings().stream()
                .map(item -> {
                    return SellerProductListingResponseDTO.builder()
                                    .seller(item.getSeller())
                                            .id(item.getId())
                                                    .isAvailable(item.isAvailable())
                                                            .originalPrice(item.getOriginalPrice())
                                                                    .price(item.getPrice())
                                                                            .quantity(item.getQuantity())
                                                                                    .build();
                })
                .toList();


        return ProductResponseDTO.builder()
                .title(product.getTitle())
                .description(product.getDescription())
                .price(landingProductDetails.get().getPrice())
                .imageUrl(product.getImageUrl())
                .category(product.getCategory())
                .quantity(landingProductDetails.get().getQuantity())
                .isAvailable(landingProductDetails.get().isAvailable())
                .seller(landingProductDetails.get().getSeller())
                .otherSellerProductListings(otherSellerListing)
                .build();
    }
}