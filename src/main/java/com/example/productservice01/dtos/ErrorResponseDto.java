package com.example.productservice01.dtos;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponseDto {
  private String message;
  private String solution;
  private int status_code;
  private LocalDateTime timestamp;
}
