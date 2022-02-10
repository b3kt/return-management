package com.example.returnkey.returnmanagement.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="string_map")
public class StringMap extends BaseEntity implements Serializable {
    
    @Column
    @Setter @Getter
    private String name;

    @Column
    @Setter @Getter
    private String code;

    @Column
    @Setter @Getter
    private String description;

}