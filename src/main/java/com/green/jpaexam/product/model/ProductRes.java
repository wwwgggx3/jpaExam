package com.green.jpaexam.product.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ProductRes {
    private Long number;
    private String name;
    private int price;
    private int stock;
    private String decription;
    private String categoryNm;
    private String providerNm;
    private String createdAt;
}
