package com.example.returnkey.returnmanagement.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity(name="orders")
public class Order extends BaseEntity implements Serializable {

    @Column
    @Setter @Getter
    private String orderId;

    @Column
    @Setter @Getter
    private String emailAddress;

    @Column
    @Setter @Getter
    private BigDecimal quantity;

    @OneToMany
    @Setter @Getter
    private List<Item> itemList;

}