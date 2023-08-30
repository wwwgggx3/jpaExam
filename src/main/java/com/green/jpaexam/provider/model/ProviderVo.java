package com.green.jpaexam.provider.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ProviderVo {
    private Long id;
    private String name;
}
