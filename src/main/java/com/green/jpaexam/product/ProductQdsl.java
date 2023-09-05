package com.green.jpaexam.product;

import com.green.jpaexam.entity.QCategoryEntity;
//import com.green.jpaexam.entity.QProductDetailEntity;
import com.green.jpaexam.entity.QProductDetailEntity;
import com.green.jpaexam.product.model.*;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.QuerydslUtils;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;



@Slf4j
@Component
@RequiredArgsConstructor
public class ProductQdsl {
    private final JPAQueryFactory jpaQueryFactory;
    private final QProductEntity p = QProductEntity.productEntity; //import해서 사용해도됨
    private final QCategoryEntity c = QCategoryEntity.categoryEntity;
    private final QProviderEntity pv = QProviderEntity.providerEntity;
    private final QProductDetailEntity pd = QProductDetailEntity.productDetailEntity;


    public List<ProductResQdsl> selProductAll(Pageable pageable) {

//        QProductDetailEntity pd = productDetailEntity;


//        List<ProductEntity> list = jpaQueryFactory.selectFrom(p).fetch();

        JPQLQuery<ProductResQdsl> query = jpaQueryFactory
                .select(Projections.bean(ProductResQdsl.class,
                        p.number, p.name, p.price, p.stock, pd.description, c.name.as("categoryNm")
                , pv.name.as("providerNm"), p.createdAt
                ))
                .from(p)
                .join(p.productDetailEntity, pd)
                .join(p.cateogryEntity, c)
                .join(p.providerEntity, pv)
//                .orderBy(p.number.desc())
                .orderBy(getAllOrderSpecifiers(pageable))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());





        return query.fetch();
    }

    private OrderSpecifier[] getAllOrderSpecifiers(Pageable pageable) {
//        List<OrderSpecifier> orders = new ArrayList();
        List<OrderSpecifier> orders = new LinkedList<>();
        if(!pageable.getSort().isEmpty()) {
            for(Sort.Order order : pageable.getSort()) {
               Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;
               //order의 property값이 스웨거 입력칸 sort의 number
               switch (order.getProperty().toLowerCase()) {
                   case "number": orders.add(new OrderSpecifier(direction, p.number)); break;
                   case "product_name": orders.add(new OrderSpecifier(direction, p.name)); break;
                   case "price": orders.add(new OrderSpecifier(direction, p.price)); break;
               }
            }
        }
        return orders.stream().toArray(OrderSpecifier[]::new);
    }

}
