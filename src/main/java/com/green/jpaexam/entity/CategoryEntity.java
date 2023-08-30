package com.green.jpaexam.entity;


import com.green.jpaexam.product.model.ProductEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@Table(name = "t_category")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
//@EqualsAndHashCode(callSuper = true)
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //오라클일 경우 .SQUENCE
    @Column(updatable = false, nullable = false, columnDefinition = "BIGINT UNSIGNED")
    private Long id;

    @Column(unique = true) //하나의 컬럼에만 유니크 주는 경우
    private String code;

    private String name;

    @OneToMany(mappedBy = "cateogryEntity")
    private List<ProductEntity> productEntityList;

}
