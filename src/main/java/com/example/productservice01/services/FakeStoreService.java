package com.example.productservice01.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import com.example.productservice01.dtos.FakeStoreDto;
import com.example.productservice01.exception.ProductNotFoundException;
import com.example.productservice01.models.Category;
import com.example.productservice01.models.Product;

@Service
public class FakeStoreService implements ProductService{
  private final RestTemplate restTemplate;
  private final String url = "https://fakestoreapi.com/products";


  public FakeStoreService(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }


  @Override
  public Product getSingleProduct(Long id) {
    String urlById = "https://fakestoreapi.com/products" + "/" + id;
    FakeStoreDto fakeStoreDto = this.restTemplate.getForObject(urlById, FakeStoreDto.class);

    if (fakeStoreDto == null) {
      throw new ProductNotFoundException("Product by id " + id + " not found");
    }

    return this.convertFakeStoreDtoToProduct(fakeStoreDto);
  }

  @Override
  public List<Product> getAllProducts() {
    FakeStoreDto[] fakeStoreDtos = this.restTemplate.getForObject(url,FakeStoreDto[].class);

    List<Product> products = new ArrayList<>();

    for (FakeStoreDto fakeStoreDto : fakeStoreDtos) {
      products.add(this.convertFakeStoreDtoToProduct(fakeStoreDto));
    }
    
    return products;
  }

  private Product convertFakeStoreDtoToProduct(FakeStoreDto fakeStoreDto) {
    // convert fakeStoreDto to Product

    Product product = new Product();
    product.setId(fakeStoreDto.getId());
    product.setTitle(fakeStoreDto.getTitle());
    product.setDescription(fakeStoreDto.getDescription());
    product.setPrice(fakeStoreDto.getPrice());

    Category category = new Category();
    category.setName(fakeStoreDto.getCategory());
    product.setCategory(category);

    return product;
  }


  @Override
  public Product createProduct(Product product) {
    FakeStoreDto fakeStoreDto = new FakeStoreDto();
    fakeStoreDto.setId(product.getId());
    fakeStoreDto.setTitle(product.getTitle());
    fakeStoreDto.setDescription(product.getDescription());
    fakeStoreDto.setPrice(product.getPrice());
    fakeStoreDto.setCategory(product.getCategory().getName());

    this.restTemplate.postForObject(url, fakeStoreDto, FakeStoreDto.class);

    return null;
    
  }


  @Override
  public Product deleteProduct(Long id) {
    return null;
  }


  @Override
  public Product updateProduct(Long id, Product product) {
    RequestCallback requestCallback = restTemplate.httpEntityCallback(product, FakeStoreDto.class);
    HttpMessageConverterExtractor<FakeStoreDto> responseExtractor = new HttpMessageConverterExtractor(FakeStoreDto.class, restTemplate.getMessageConverters());
    FakeStoreDto response = restTemplate.execute(url + "/" + id, HttpMethod.PATCH, requestCallback, responseExtractor);
    return this.convertFakeStoreDtoToProduct(response);
  }


}
