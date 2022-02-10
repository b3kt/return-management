package com.example.returnkey.returnmanagement.listener;

import java.util.Date;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.springframework.stereotype.Component;

import com.example.returnkey.returnmanagement.entity.BaseEntity;

@Component
public class EntityListener{

    @PreUpdate
    @PrePersist
    public void onBeforeSave(final BaseEntity entity){
        final Long existingVersion = entity.getVersion();
        entity.setVersion(existingVersion == null ? 0L : existingVersion + 1L);

        entity.setCreatedBy("system");
        entity.setCreatedDate(new Date());

        entity.setLastModifiedBy("system");
        entity.setLastModifiedDate(new Date());

    }
    

    
}