package com.example.productservice01.services;

import java.util.List;

import com.example.productservice01.models.Product;

public interface ProductService {
  Product getSingleProduct(Long id);

  List<Product> getAllProducts();

  Product createProduct(Product product);

  Product updateProduct(Long id, Product product);

  Product deleteProduct(Long id);
}
