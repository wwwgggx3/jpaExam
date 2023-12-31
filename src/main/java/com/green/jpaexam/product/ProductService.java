package com.green.jpaexam.product;

import com.green.jpaexam.entity.CategoryEntity;
import com.green.jpaexam.entity.ProductDetailEntity;
import com.green.jpaexam.product.model.*;
import com.green.jpaexam.provider.ProviderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductDao dao;
    private final CategoryRepository categoryRep;
    private final ProviderRepository providerRep;
    private final ProductRepository productRep;
    private final ProductDetailRepository productDetailRep;
    private final ProductQdsl productQdsl;

    public ProductRes saveProduct(ProductDto dto) {

        ProductEntity productEntity = ProductEntity.builder()
                .name(dto.getName())
                .price(dto.getPrice())
                .stock(dto.getStock())
                .cateogryEntity(categoryRep.getReferenceById(dto.getCategoryId()))
                .providerEntity(providerRep.getReferenceById(dto.getProviderId()))
                .build();

        ProductEntity result = productRep.save(productEntity);
        //productRep.save(entity); //윗 줄 대신 이거해도 됨

        ProductDetailEntity productDetailEntity = ProductDetailEntity.builder()
                .productEntity(productEntity)
                .description(dto.getDescription())
                .build();
        productDetailRep.save(productDetailEntity);

        return ProductRes.builder()
                .number(productEntity.getNumber())
                .name(productEntity.getName())
                .price(productEntity.getPrice())
                .stock(productEntity.getStock())
                .decription(productDetailEntity.getDescription())
                .categoryNm(productEntity.getCateogryEntity().getName())
                .providerNm(productEntity.getProviderEntity().getName())
//                .createdAt(productEntity.getCreatedAt())
                .build();

        /*
        ProductEntity result = dao.saveProduct(entity);
        return ProductRes.builder()
                .number(result.getNumber())
                .name(result.getName())
                .price(result.getPrice())
                .stock(result.getStock())
                .build();
         */
    }

    public ProductRes saveProduct2(ProductDto dto) {
        CategoryEntity categoryEntity = categoryRep.findById(dto.getCategoryId()).get();
        ProviderEntity providerEntity = providerRep.findById(dto.getProviderId()).get();

        ProductDetailEntity productDetailEntity = ProductDetailEntity.builder()
                .description(dto.getDescription())
                .build();

        ProductEntity productEntity = ProductEntity.builder()
                .name(dto.getName())
                .price(dto.getPrice())
                .stock(dto.getStock())
                .cateogryEntity(categoryEntity)
                .providerEntity(providerEntity)
                .build();

        productEntity.setProductDetailEntity(productDetailEntity);
//        productDetailEntity.setProductEntity(productEntity); //productEntity도 productDetailEntity 연결하는데 이것도 연결하니 굳이 할 필요 없어서 주석처리. productEntity setter보기

        productRep.save(productEntity);
        return null;
        /*
        ProductEntity result = dao.saveProduct(entity);
        return ProductRes.builder()
                .number(result.getNumber())
                .name(result.getName())
                .price(result.getPrice())
                .stock(result.getStock())
                .build();
         */
    }

    public Page<ProductRes> getProductAll(Pageable page) {
        return dao.getProductAll(page);
        /*
        List<ProductEntity> list = dao.getProductAll();

        List<ProductRes> result = list.stream().map(item -> ProductRes.builder()
                        .number(item.getNumber())
                        .name(item.getName())
                        .price(item.getPrice())
                        .stock(item.getStock())
                        .build()
                ).toList();
        return result;
        */
    }

    public List<ProductRes> getProductAllJpql(Pageable pageable, ProductSelAllParam param) {
//        List<ProductRes> list = productRep.selProductAll(pageable, "등록테스트777", 100_000);
        List<ProductRes> list = productRep.selProductAll(pageable, param);
        log.info("list : {}", list); //에러터지면 이거 주석
        return list;
    }

    public List<ProductResQdsl> getProductAllQdsl(Pageable pageable, String search) {
        return productQdsl.selProductAll(pageable, search);
    }

    public ProductRes getProduct(Long number) {
        return dao.getProduct(number);
    }

    public ProductRes updProduct(ProductUpdDto dto) {
        ProductEntity entity = ProductEntity.builder()
                .number(dto.getNumber())
                .name(dto.getName())
                .price(dto.getPrice())
                .stock(dto.getStock())
                .build();
        return dao.updProduct(entity);
    }

    public void delProduct(Long number) {
        dao.delProduct(number);
    }
}
