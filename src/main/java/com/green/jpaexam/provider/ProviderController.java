package com.green.jpaexam.provider;

import com.green.jpaexam.provider.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/provider")
public class ProviderController {
    private final ProviderService service;

    @PostMapping
    public ResponseEntity<ProviderInsVo> postProvider(@RequestBody ProviderReqInsDto dto) {
        ProviderInsVo vo = service.save(dto);

        return ResponseEntity.ok(vo);
    }

    @PutMapping //수정할 컬럼이 적기 때문에 PutMapping. 많다면 PatchMapping
    public ResponseEntity<ProviderUpdVo> putProvider(@RequestBody ProviderReqUpdDto dto) {
        ProviderUpdVo vo = service.update(dto);
        return ResponseEntity.ok(vo);
    }

    @GetMapping
    public ResponseEntity<List<ProviderVo>> getProvider() {
        List<ProviderVo> vo = service.select();
        return ResponseEntity.ok(vo);
    }
    //id name만 나오도록


    @DeleteMapping
    public ResponseEntity<Integer> delProvider(@RequestParam Long providerId) {
        service.delete(providerId);
        return ResponseEntity.ok(1);
    }
}
