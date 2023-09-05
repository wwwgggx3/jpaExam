package com.green.jpaexam.product;

import com.green.jpaexam.product.model.ProductEntity;
import com.green.jpaexam.product.model.ProductRes;
import com.green.jpaexam.product.model.ProductSelAllParam;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
//    @Query("select p from ProductEntity p join p.productDetailEntity") //select p는 select *와 같은 의미. 둘이 정확하게 fk로 연결되어 잇으면 on은 and문이 된다. 연결안되어 있으면 on써야함
//    @Query("select new com.green.jpaexam.product.model.ProductRes(p.number, p.name, p.price, p.stock, d.description, c.name, e.name, p.createdAt) "+
//            "from ProductEntity p join p.productDetailEntity d join p.cateogryEntity c join p.providerEntity e " +
//            "where p.name = :productName and p.price >= :price") //select p는 select *와 같은 의미. 둘이 정확하게 fk로 연결되어 잇으면 on은 and문이 된다. 연결안되어 있으면 on써야함
//    @Query("select new com.green.jpaexam.product.model.ProductRes(p.number, p.name, p.price, p.stock, d.description, c.name, p.name, p.createdAt) "+
//            "from ProductEntity p join p.productDetailEntity d join CategoryEntity c on c.id = p.cateogryEntity.id") // 직접적으로 join하는 것(전혀 fk가 안걸려있는 것들끼리 join할 때)

@Query(" select new com.green.jpaexam.product.model.ProductRes" +
        " (p.number, p.name, p.price, p.stock, d.description, c.name, pv.name, p.createdAt)" +
        // "from ProductEntity p join p.productDetailEntity d join CategoryEntity c on p.cateogryEntity.id = c.id")
        " from ProductEntity p join p.productDetailEntity d join p.cateogryEntity c join p.providerEntity pv" +
        " where p.name = :#{#param.productName} and p.price >= :#{#param.price}")
    List<ProductRes> selProductAll(Pageable pageable, ProductSelAllParam param); //바로 위 Query용
//    List<ProductRes> selProductAll(Pageable pageable, String productName, int price);




// @Query()의 select 부분을 projection이라고함
//    @Query("select d from ProductEntityDetail d join CategoryEntity c on d.pruductNumber = c.id")
    /*
    select * from t_product_detail d
    left join t_category c
    on d.product_number = c.id;
     */
}
/* @Query("select p from ProductEntity p join p.productDetailEntity d on d.number = 1")
select p.*
from t_product p
inner join t_product_detail d
ON p.number = d.product_number
AND d.number = 1;

연관 없는 애끼리 join걸면 위에서 on이 아래로 바뀜?
select p.*
from t_product p
inner join t_product_detail d

on d.number = 1;
 */