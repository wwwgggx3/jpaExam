package com.green.jpaexam.product.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder //@AllArgsConstructor가 있으니 필요 없음
//@AllArgsConstructor
public class ProductRes {
    private Long number;
    private String name;
    private int price;
    private int stock;
    private String decription;
    private String categoryNm;
    private String providerNm;
    private LocalDateTime createdAt;

    public ProductRes(Long number, String name, int price, int stock, String decription, String categoryNm, String providerNm, LocalDateTime createdAt) {
        this.number = number;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.decription = decription;
        this.categoryNm = categoryNm;
        this.providerNm = providerNm;
        this.createdAt = createdAt;
    }
}
