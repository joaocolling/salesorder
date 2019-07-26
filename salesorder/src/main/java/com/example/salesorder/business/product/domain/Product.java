/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.salesorder.business.product.domain;

import com.example.salesorder.business.manufacturer.domain.Manufacturer;
import com.example.salesorder.util.UtilDate;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author joao
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "product")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String barcode;

    @Column
    private BigDecimal unitPrice;

    @Column
    private LocalDateTime created;

    @Column
    private LocalDateTime updated;

    @ManyToOne
    @JoinColumn(name = "id_manufacturer")
    private Manufacturer manufacturer;

    @PrePersist
    private void prePersist() {
        this.created = UtilDate.getTimeUTC();
        this.updated = this.created;
    }

    @PreUpdate
    private void preUpdate() {
        this.updated = UtilDate.getTimeUTC();
    }
}
