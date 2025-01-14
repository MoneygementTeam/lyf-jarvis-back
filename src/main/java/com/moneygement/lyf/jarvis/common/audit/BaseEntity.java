package com.moneygement.lyf.jarvis.common.audit;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @CreatedDate
    @Column(name = "CREATED_DATE", updatable = false, nullable = false)
    protected LocalDateTime createdDate;

    @LastModifiedDate
    @Column(name = "UPDATED_DATE")
    protected LocalDateTime updatedDate;
}
