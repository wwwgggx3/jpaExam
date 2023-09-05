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
                .orderBy(p.number.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());





        return query.fetch();
    }

    private List<OrderSpecifier> getAllOrderSpecifiers(Pageable pageable) {
        List<OrderSpecifier> orders = new ArrayList();
        if(!pageable.getSort().isEmpty()) {
            for(Sort.Order order : pageable.getSort()) {
               Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;
//               OrderSpecifier orderId = QuerydslUtil
            }
        }

        return orders;
    }

}
