package com.example.productservice01.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.productservice01.models.Product;
import com.example.productservice01.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

  ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<Product> getProductById(@PathVariable("id") Long productId) {
    ResponseEntity<Product> response = new ResponseEntity<>(this.productService.getSingleProduct(productId), HttpStatus.FORBIDDEN);
    return response;
  }

  @GetMapping("")
  public List<Product> getAllProducts() {
    return this.productService.getAllProducts();
  }

  @PostMapping("")
  public Product createProduct(@RequestBody Product product) {
    return this.productService.createProduct(product);
  }

  @PutMapping("/{id}")
  public Product updateProduct(@PathVariable("id") Long productId, @RequestBody Product product) {
    return this.productService.updateProduct(productId, product);
  }

}
