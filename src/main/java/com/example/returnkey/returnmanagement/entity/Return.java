package com.example.returnkey.returnmanagement.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name= "returns")
public class Return extends BaseEntity implements Serializable {

    @Expose
    @Column
    @Setter @Getter
    private String orderId;
    
    @Expose
    @Column
    @Setter @Getter
    private String emailAddress;
    
    @Expose
    @Column
    @Setter @Getter
    private BigDecimal refundAmount;

    @Expose
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "returns")
    @Setter @Getter
    private List<ReturnItem> returnItemList;

    @Expose
    @Column
    @Setter @Getter
    private String status;

}