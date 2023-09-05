package com.green.jpaexam.product.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductResQdsl {
    private Long number;
    private String name;
    private int price;
    private int stock;
    private String decription;
    private String categoryNm;
    private String providerNm;
    private LocalDateTime createdAt;
}
