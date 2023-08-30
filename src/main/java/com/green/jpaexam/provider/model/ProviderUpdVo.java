package com.green.jpaexam.provider.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ProviderUpdVo {
    private Long id;
    private String name;
    private String updatedAt;
}
