package org.example.bridalbucket.service;

import org.example.bridalbucket.dtos.request.ProductRequestDTO;
import org.example.bridalbucket.dtos.response.ProductResponseDTO;
import org.example.bridalbucket.exception.product.ProductNotFoundException;
import org.example.bridalbucket.models.Category;
import org.example.bridalbucket.models.Product;
import org.example.bridalbucket.models.Seller;
import org.example.bridalbucket.models.SellerProductListing;
import org.example.bridalbucket.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductResponseDTO addProduct(ProductRequestDTO productRequestDTO) {
        return null;
    }

    public Optional<List<ProductResponseDTO>> getAllProducts(){

        return Optional.empty();
    }
    public Optional<Product> getProductById(String id){
        try {
            return Optional.of(createDummyProduct());
        } catch (Exception e) {
            throw new ProductNotFoundException("Product not found with this id: " + id);
        }

    }

    public Product createDummyProduct(){
        Category category = new Category().id((long)12434341).name("Clothing");

        Product product = new Product();
        product.setId(1l);
        product.setTitle("Red Marriage Saree");
        product.setCategory(category);
        product.setDescription("Saree for marriage");
        product.setImageUrl("/test");


        Seller seller1 = Seller.builder().name("Rajesh").email("test@gmail.com").phoneNumber("1234567890").id(1l).build();
        Seller seller2 = Seller.builder().name("Kuldeep ").email("test232@gmail.com").phoneNumber("656567890").id(2l).build();

        SellerProductListing sellerProductListing1 = new SellerProductListing();
        sellerProductListing1.setId(1l);
        sellerProductListing1.setProduct(product);
        sellerProductListing1.setSeller(seller1);
        sellerProductListing1.setPrice(1000);
        sellerProductListing1.setQuantity(10);


        SellerProductListing sellerProductListing2 = new SellerProductListing();
        sellerProductListing2.setId(2l);
        sellerProductListing2.setProduct(product);
        sellerProductListing2.setSeller(seller2);
        sellerProductListing2.setPrice(800);
        sellerProductListing2.setQuantity(2);


        product.setSellerProductListings(List.of(sellerProductListing1, sellerProductListing2));
        return product;
    }
}
