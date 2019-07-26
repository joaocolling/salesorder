/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.salesorder.business.salesorder.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author joao
 */
@Getter
@Setter
public class SalesOrderDTO {

    private List<SalesOrderProductDTO> products;
    private ConsumerDTO consumer;
    private PaymentDTO payment;
    private DeliveryDTO delivery;
}
