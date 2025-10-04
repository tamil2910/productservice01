package com.example.productservice01.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product extends BaseModel {
  private String title;
  private String description;
  private double price;
  private Category category;
}
