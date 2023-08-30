package com.green.jpaexam.config;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// JpaAuditingConfiguration클래스도 만들어야함
@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@SuperBuilder
@NoArgsConstructor
public class BaseEntity {

    @CreatedDate // spring제공 애노테이션. @creationstamp? 써도됨
//    @CreationTimestamp //하이버네이트 제공 애노테이션
    @Column(updatable = false) //create시간 수정이 불가능하다는 의미
    private LocalDateTime createdAt;

    @LastModifiedDate // spring제공 애노테이션
//    @UpdateTimestamp //하이버네이트 제공 애노테이션
    private LocalDateTime updatedAt;

    public String getCreatedAt() {
        return this.createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm"));
    }

    public String getUpdatedAt() {
        return this.updatedAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm"));
    }
}
