package com.green.jpaexam.entity;

import com.green.jpaexam.config.BaseEntity;
import com.green.jpaexam.product.model.ProductEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@Table(name = "t_product_detail")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ProductDetailEntity extends BaseEntity {

    @Id //pk
    private Long number;

    @MapsId
    @OneToOne
    @JoinColumn(name = "product_number", columnDefinition = "BIGINT UNSIGNED")
    private ProductEntity productEntity;

    @Column(nullable = true, length = 255)
    private String description;



//    @OneToOne
//    @JoinColumn(name = "product_number")
//    private ProductEntity productEntity;

}
