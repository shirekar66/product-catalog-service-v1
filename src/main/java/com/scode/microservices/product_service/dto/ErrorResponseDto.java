package com.scode.microservices.product_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorResponseDto {
    private String code;
    private String message;
}
