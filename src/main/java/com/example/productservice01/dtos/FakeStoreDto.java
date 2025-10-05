package com.example.productservice01.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreDto {
  private Long id;
  private String title;
  private String description;
  private Double price;
  private String category;
  private String image;
}
