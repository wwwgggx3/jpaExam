package com.green.jpaexam.product;

import com.green.jpaexam.product.model.*;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;

    @PostMapping
    public ResponseEntity<ProductRes> postProduct(@RequestBody ProductDto dto) {
        ProductRes res = service.saveProduct(dto);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/postProduct2")
    public ResponseEntity<ProductRes> postProduct2(@RequestBody ProductDto dto) {
        ProductRes res = service.saveProduct2(dto);
        return ResponseEntity.ok(res);
    }

    @GetMapping
    public ResponseEntity<Page<ProductRes>> getProductAll(@PageableDefault(sort="number", direction = Sort.Direction.DESC, size = 20) Pageable page) {
        return ResponseEntity.ok(service.getProductAll(page)); //ok는 200코드
    }

// 깃보고 코드 수정
    @GetMapping("/jpql")
    public ResponseEntity<List<ProductRes>> getProductAllJpql(
            @PageableDefault(sort="number", direction = Sort.Direction.DESC, size = 20) Pageable pageable, @RequestParam ProductSelAllParam param) {
        return ResponseEntity.ok(service.getProductAllJpql(pageable, param));
    }
    //@ParameterObject 넣으면 @PageableDefault의 디폴트값이 스웨거에 디폴트값으로 들어감
    @GetMapping("/qdsl")
    public ResponseEntity<List<ProductResQdsl>> getProductAllQdsl(
            @ParameterObject @PageableDefault(sort="number", direction = Sort.Direction.DESC, page = 0, size = 20) Pageable pageable) {
        return ResponseEntity.ok(service.getProductAllQdsl(pageable));
    }



    @GetMapping("/{number}")
    public ResponseEntity<ProductRes> getProduct(Long number) {
        return ResponseEntity.ok(service.getProduct(number));
    }

    @PutMapping
    public ResponseEntity<ProductRes> updProduct(@RequestBody ProductUpdDto dto) {
        return ResponseEntity.ok(service.updProduct(dto));
    }

    @DeleteMapping
    public ResponseEntity<Integer> delProduct(@RequestParam Long number) {
        service.delProduct(number);
        return ResponseEntity.ok(1);
    }
}
