package com.green.jpaexam.product.model;

import com.green.jpaexam.config.BaseEntity;
import com.green.jpaexam.entity.ProductDetailEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_provider")
@SuperBuilder
@NoArgsConstructor
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ProviderEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_incremnet
    @Column(updatable = false, nullable = false, columnDefinition = "BIGINT UNSIGNED")
    private Long id;

    private String name;


    // (mappedBy = "") 는 FK가 안들어가는 부분에 넣음
    @OneToMany(mappedBy = "providerEntity", cascade = CascadeType.PERSIST) //(mappedBy = "providerEntity") 입력하면 db에 t_provider_product_entity_list 테이블이 안생김
//    @JoinColumn(name = "provider_id")
    @ToString.Exclude
    private List<ProductEntity> productEntityList = new ArrayList<>();

//    @OneToOne
//    @JoinColumn(name = "productDetailEntity")
//    private ProductDetailEntity productDetailEntity;
    /*
    @OneToMany
    @ToString.Exclude
    private List<ProductEntity> productEntityList;
     */
}
