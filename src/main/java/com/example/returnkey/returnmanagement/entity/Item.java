package com.example.returnkey.returnmanagement.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="items")
public class Item extends BaseEntity  implements Serializable {

    @Column
    @Setter @Getter
    private String itemName;

    @Column
    @Setter @Getter
    private String sku;

    @Column
    @Setter @Getter
    private BigDecimal price;

}