package com.green.jpaexam.product;

import com.green.jpaexam.product.model.ProductEntity;
import com.green.jpaexam.product.model.ProductRes;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
//@RequiredArgsConstructor
public class ProductDaoImpl implements ProductDao{

    private final ProductRepository rep;

    public ProductDaoImpl(ProductRepository rep) {
        this.rep = rep;
    }

    @Override
    public ProductRes saveProduct(ProductEntity p) {
//        return rep.save(p);

        ProductEntity result = rep.save(p);
        return ProductRes.builder()
                .number(result.getNumber())
                .name(result.getName())
                .price(result.getPrice())
                .stock(result.getStock())
                .build();
    }
    @Override
    public Page<ProductRes> getProductAll(Pageable page) {
        Page<ProductEntity> totalList = rep.findAll(page);
        Long totalSize = totalList.getTotalElements();
        List<ProductRes> contents = totalList.getContent().stream().map(item ->
                ProductRes.builder()
                        .number(item.getNumber())
                        .name(item.getName())
                        .price(item.getPrice())
                        .stock(item.getStock())
                        .build()
        ).toList();
        return new PageImpl<>(contents, page, totalSize);

//        return rep.findAll(page).stream().map(item ->
//                ProductRes.builder()
//                        .number(item.getNumber())
//                        .name(item.getName())
//                        .price(item.getPrice())
//                        .stock(item.getStock())
//                        .build()
//        ).toList();
    }

    /*
    @Override
    public List<ProductRes> getProductAll() {
        List<ProductEntity> list = rep.findAll(Sort.by(Sort.Direction.DESC, "number")); //정렬 적용

        List<ProductRes> result = list.stream().map(item -> ProductRes.builder()
                .number(item.getNumber())
                .name(item.getName())
                .price(item.getPrice())
                .stock(item.getStock())
                .build()
        ).toList();

        return result;
    }
*/
    @Override
    public ProductRes getProduct(Long number) {
        Optional<ProductEntity> opt = rep.findById(number);
        if(!opt.isPresent()) {
            return null;
        }
        ProductEntity entity = opt.get();
        return ProductRes.builder()
                .number(entity.getNumber())
                .name(entity.getName())
                .price(entity.getPrice())
                .stock(entity.getStock())
                .build();
    }

    @Override
    public ProductRes updProduct(ProductEntity p) {
        Optional<ProductEntity> opt = rep.findById(p.getNumber());
        if(!opt.isPresent()) {
            return null;
        }
        ProductEntity entity = opt.get();

        entity.setName(p.getName());
        entity.setPrice(p.getPrice());
        entity.setStock(p.getStock());

        ProductEntity result = rep.save(entity);
        return ProductRes.builder()
                .number(result.getNumber())
                .name(result.getName())
                .price(result.getPrice())
                .stock(result.getStock())
                .build();
    }

    @Override
    public void delProduct(Long number) {
        rep.deleteById(number);
    }
}
