package com.example.returnkey.returnmanagement.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name= "complete_returns")
public class Return extends BaseEntity implements Serializable {

    @Column
    @Setter @Getter
    private Order order;

    @OneToOne(fetch = FetchType.LAZY)
    @Setter @Getter
    private StringMap status;

}