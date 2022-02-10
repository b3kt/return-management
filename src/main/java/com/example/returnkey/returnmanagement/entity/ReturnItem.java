package com.example.returnkey.returnmanagement.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="return_items")
public class ReturnItem extends BaseEntity  implements Serializable {

    @Expose
    @Column
    @Setter @Getter
    private String itemName;

    @Expose
    @Column
    @Setter @Getter
    private String sku;

    @Expose
    @Column
    @Setter @Getter
    private BigDecimal price;

    @Expose
    @Column
    @Setter @Getter
    private Long quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "return_id")
    @Setter @Getter
    private Return returns;   

    @Expose
    @Column
    @Setter @Getter
    private String status;

}