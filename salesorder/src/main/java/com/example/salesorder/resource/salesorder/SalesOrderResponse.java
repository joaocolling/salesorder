/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.salesorder.resource.salesorder;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author joao
 */
@Getter
@Setter
public class SalesOrderResponse {
    private Integer id;
    private String status;
    private List<SalesOrderProductResponse> products;
    private ConsumerResponse consumer;
    private PaymentResponse payment;
    private DeliveryResponse delivery;
    
}
