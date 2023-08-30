package com.green.jpaexam.provider.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ProviderInsVo {
    private Long id;
    private String name;
    private String createdAt;
}
