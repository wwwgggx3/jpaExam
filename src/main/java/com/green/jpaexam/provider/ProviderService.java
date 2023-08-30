package com.green.jpaexam.provider;

import com.green.jpaexam.product.model.ProviderEntity;
import com.green.jpaexam.provider.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional //은행 이체. 여러 가지 업무를 하나로 만든다.
/* @Transactional
하나 또는 둘 이상의 작업을 하나의 작업 단위로 묶어서 커밋되어 작업이 적용되거나 롤백되어 작업을 취소하고 이전 상태로 되돌리는 것
 */
public class ProviderService {
    private final ProviderRepository rep;

    public ProviderInsVo save(ProviderReqInsDto dto) {
        ProviderEntity entity = ProviderEntity.builder()
                .name(dto.getName())
                .build();
        rep.save(entity);

        return ProviderInsVo.builder()
                .id(entity.getId())
                .name(entity.getName())
                .createdAt(entity.getCreatedAt().toString())
                .build();
    }

    public ProviderUpdVo update(ProviderReqUpdDto dto) {
        Optional<ProviderEntity> optEntity = rep.findById(dto.getId());

        ProviderEntity entity = optEntity.get();
        entity.setName(dto.getName());

        rep.save(entity); //영속성인 애를 넣으면 수정. 아닌 애는 save

        return  ProviderUpdVo.builder()
                .id(entity.getId())
                .name(entity.getName())
                .updatedAt(entity.getUpdatedAt().toString())
                .build();

        /*
        //select (영속성인 애를 가져와야 함)
//        Optional<ProviderEntity> entity = rep.findById(dto.getId());
        ProviderEntity entity = rep.findById(dto.getId()).get();

        //이름 수정
//        entity.get().setName(dto.getName());
        entity.setName(dto.getName());

        rep.save(entity); //영속인 애를 넣으면 수정. 비영속인 애를 넣으면 save


        return ProviderUpdVo.builder()
                .id(entity.getId())
                .name(entity.getName())
                .updatedAt(entity.getUpdatedAt().toString())
                .build();
*/
    }

    public List<ProviderVo> select() {
        List<ProviderEntity> list = rep.findAll(Sort.by(Sort.Direction.ASC, "name"));

        return list.stream().map(entity -> ProviderVo.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build()).toList();
    }

    public void delete(Long providerId) {
        //providerId가 없다면 throw하도록 해야함

        rep.deleteById(providerId);

        /*
        //영속성
        Optional<ProviderEntity> optEntity = rep.findById(providerId);
        ProviderEntity entity = optEntity.get();

        rep.delete(entity);
        */

    }
}
