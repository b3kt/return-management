package com.example.returnkey.returnmanagement.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Table(name="pending_returns", uniqueConstraints = { @UniqueConstraint(columnNames = { "orderId", "sku" }) })
public class PendingReturn extends BaseEntity  implements Serializable {

    @Column
    @Setter @Getter
    private String orderId;
    
    @Column
    @Setter @Getter
    private String emailAddress;
    
    @Column
    @Setter @Getter
    private String sku;
    
    @Column
    @Setter @Getter
    private Long quantity;
    
    @Column
    @Setter @Getter
    private BigDecimal price;
    
    @Column
    @Setter @Getter
    private String itemName;

    @Column
    @Setter @Getter
    private String token;

    @Column
    @Setter @Getter
    private String status;

}

