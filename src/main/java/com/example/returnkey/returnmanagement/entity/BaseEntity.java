package com.example.returnkey.returnmanagement.entity;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
public class BaseEntity {

    @Id
	@GenericGenerator(name="idgen" , strategy="increment")
	@GeneratedValue(generator="idgen")
	@Setter @Getter
    private Long id;

    @CreatedBy
    @Setter @Getter
    private String createdBy;

    @CreatedDate
    @Setter @Getter
    private Date createdDate;

    @LastModifiedBy
    @Setter @Getter
    private String lastModifiedBy;

    @LastModifiedDate
    @Setter @Getter
    private Date lastModifiedDate;

    @Version
    @Setter @Getter
    private Long version;

}