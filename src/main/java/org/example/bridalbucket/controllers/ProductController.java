package org.example.bridalbucket.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.example.bridalbucket.dtos.request.ProductRequestDTO;
import org.example.bridalbucket.dtos.response.ProductResponseDTO;
import org.example.bridalbucket.exception.product.ProductNotFoundException;
import org.example.bridalbucket.models.Product;
import org.example.bridalbucket.service.ProductService;
import org.example.bridalbucket.util.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/")
    public ResponseWrapper addProduct(@RequestBody @Valid ProductRequestDTO productRequestDTO){
        return ResponseWrapper.successResponseWithPayload(HttpStatus.OK.name(), productService.addProduct(productRequestDTO)) ;
    }

    @Operation(summary = "Get a product by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the product",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductResponseDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseWrapper.class))),
            @ApiResponse(responseCode = "404", description = "Product not found",
                    content =  @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseWrapper.class)))})
    @GetMapping("/{id}")
    public ResponseWrapper getProductById(@PathVariable String id){
        Optional<Product> productOptional = productService.getProductById(id);
        if (productOptional.isEmpty()) {
             throw new ProductNotFoundException("Product not found with id: " + id);
            }
        return ResponseWrapper.successResponseWithPayload(HttpStatus.OK.name(), ProductResponseDTO.from(productOptional.get()) );
    }

    @GetMapping("/")
    public ResponseWrapper getAllProducts(){
        return ResponseWrapper.successResponseWithPayload(HttpStatus.OK.name(),productService.getAllProducts());
    }




}
