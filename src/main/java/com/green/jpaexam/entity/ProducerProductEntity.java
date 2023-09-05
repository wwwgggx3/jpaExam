package com.green.jpaexam.entity;

import com.green.jpaexam.config.BaseEntity;
import com.green.jpaexam.product.model.ProductEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@Table(name = "t_producer")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ProducerProductEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false, columnDefinition = "BIGINT UNSIGNED")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "producer_id")
    private ProducerEntity producerEntity;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity productEntity;
}
