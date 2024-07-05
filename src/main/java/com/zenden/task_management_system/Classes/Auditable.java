package com.zenden.task_management_system.Classes;

import java.sql.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
public class Auditable {
    
    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;
}
