package com.example.returnkey.returnmanagement.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="pending_returns")
public class PendingReturn extends BaseEntity  implements Serializable {

    @Column
    @Setter @Getter
    private String filename;

    @Column
    @Setter @Getter
    private String uuid;

    @Column(name="md5_hash")
    @Setter @Getter
    private String md5hash;

    @Column
    @Setter @Getter
    private BigDecimal size;

    @Column(name="total_rows")
    @Setter @Getter
    private BigDecimal totalrows;

    @Column(name="token")
    @Setter @Getter
    private String token;

    @OneToOne(fetch = FetchType.LAZY)
    @Setter @Getter
    private StringMap status;

}

