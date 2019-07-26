/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.salesorder.business.salesorder.domain;

import com.example.salesorder.business.salesorder.domain.subdomain.Consumer;
import com.example.salesorder.business.salesorder.domain.subdomain.Delivery;
import com.example.salesorder.business.salesorder.domain.subdomain.Payment;
import com.example.salesorder.business.salesorder.domain.subdomain.SalesOrderProduct;
import com.example.salesorder.util.UtilDate;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "sales_order")
public class SalesOrder implements Serializable {

    public static class Const {

        public static String STATUS_PENDING = "Pending Confirmation";
        public static String STATUS_CANCELED = "Canceled";
        public static String STATUS_CONFIRMED = "Confirmed";
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String status;

    @Column
    private LocalDateTime created;

    @Column
    private LocalDateTime updated;

    @ManyToOne
    @JoinColumn(name = "id_consumer")
    private Consumer consumer;

    @ManyToOne
    @JoinColumn(name = "id_payment")
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "id_delivery")
    private Delivery delivery;

    @OneToMany(mappedBy = "salesOrder", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SalesOrderProduct> salesOrderProducts;

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
