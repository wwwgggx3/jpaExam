package com.green.jpaexam.product.model;

import com.green.jpaexam.config.BaseEntity;
import com.green.jpaexam.entity.CategoryEntity;
import com.green.jpaexam.entity.ProductDetailEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Table(name = "t_product")
//@EqualsAndHashCode //데이터가 같으면 동등성, 객체가 같으면 동일성(주소값 같음) //이퀄스메서드랑 해시코드메서드를 오버라이딩해야함
@Entity //ProductEntity를 entity로 사용할거라는 의미?
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ProductEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //오라클일 경우 .SQUENCE
    @Column(updatable = false, nullable = false, columnDefinition = "BIGINT UNSIGNED")
    private Long number;

    @Column(nullable = false) //not null이라는 의미
    private String name;

    @Column(nullable = false)
    private Integer price; //int(프러머티브 타입)안쓰고 레퍼런스타입 쓰기

    @Column(nullable = false)
    private Integer stock;

    @ManyToOne
    @JoinColumn(name = "provider_id")
    @ToString.Exclude // ProviderEntity는 문자열로 찍을 때 제외시킨다는 의미. 지금은 제외 안해도 ok. 양방향일 땐 필수임
    private ProviderEntity providerEntity; //jpa는 관계 설정을 객체로 하기 때문에 객체주소값을 담을 수 있도록 객체를 적는 것(Long providerId 안적고)


    //ProductEntity에는 FK 안생기고 ProductDetailEntity와 관계 설정
    @OneToOne(mappedBy = "productEntity", cascade = { CascadeType.PERSIST } ) //mappedBy 는 fk 안들어가고 싶은 곳에 들어가면 됨 //@OneToOne(mappedBy = "productEntity")
    @ToString.Exclude
    private ProductDetailEntity productDetailEntity;
    /*
     @OneToOne(mappedBy = "productEntity", cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, orphanRemoval = true)
    CascadeType.PERSIST : fk로 참조한 row부터 먼저 삭제하고 나서 원본을 삭제해야함
    CascadeType.REMOVE : 부모가 삭제되면 자식fk가 null로 됨
     */

    @ManyToOne
    @JoinColumn(name= "category_id") //@JoinColumn(name= "category_id")
    @ToString.Exclude
    private CategoryEntity cateogryEntity;

    public void setProductDetailEntity(ProductDetailEntity productDetailEntity) {
        if(this.productDetailEntity != null) {
            this.productDetailEntity.setProductEntity(null);
        }
        this.productDetailEntity = productDetailEntity;
        this.productDetailEntity.setProductEntity(this);
    }



    //    //사용자가 등록, 수정하는 테이블이면 등록일시, 수정일시 들어간다고 보면 됨
//    @CreationTimestamp
////    @Column(name = "created_at") // 안해도될듯
//    private LocalDateTime createdAt;
//
//    @UpdateTimestamp
//    private LocalDateTime updatedAt;
}
