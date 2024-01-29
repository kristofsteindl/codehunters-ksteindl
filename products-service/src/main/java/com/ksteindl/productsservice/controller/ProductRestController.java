package com.ksteindl.productsservice.controller;

import com.ksteindl.productsservice.controller.dto.Product;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8081") // <-- FE calls allowed from here
@RequestMapping("/products")
public class ProductRestController {

    @PostMapping
    public ResponseEntity<Product> createProduct(Product newProduct) throws URISyntaxException {
//        return ResponseEntity.status(201).body(newProduct);
        return ResponseEntity.created(new URI("/products/1234")).body(newProduct);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, Product newProduct) {
        return newProduct;
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable(name = "id") Long renamedId) {
    }

    @GetMapping
    @Operation(summary = "It retrieves all products as a list.")
    @ApiResponse(
            content = @Content(array = @ArraySchema(
                    schema = @Schema(implementation = Product.class))),
            responseCode = "200",
            description = "It retrieves all products."
    )
    public ResponseEntity<List<Product>> getProducts() {
        return ResponseEntity.ok(List.of());
    }

    // Content-Type -> Headerben kell megjelölni, hogy a body-ban milyen típúsú adat van 
    // application/xml, application/json, .../jpg, plain/text
    // Global Exception Handling 

    // Entity Manager factory, transaction
    // JPAs alap


}
