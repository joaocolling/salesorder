/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.salesorder.business.salesorder.domain.subdomain;

import com.example.salesorder.business.product.domain.Product;
import com.example.salesorder.business.salesorder.domain.SalesOrder;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author joao
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "sales_order_product")
public class SalesOrderProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "product_name")
    private String productName;

    @Column
    private BigDecimal units;

    @Column(name = "unit_price")
    private BigDecimal unitPrice;

    @Column(name = "amount_price")
    private BigDecimal amountPrice;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_sales_order")
    private SalesOrder salesOrder;

    @ManyToOne
    @JoinColumn(name = "id_product")
    private Product product;    
}
