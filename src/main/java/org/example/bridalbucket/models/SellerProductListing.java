package org.example.bridalbucket.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "product_seller_mapping")
public class SellerProductListing extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = false)
    private Seller seller;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private double originalPrice;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private boolean isAvailable;
}