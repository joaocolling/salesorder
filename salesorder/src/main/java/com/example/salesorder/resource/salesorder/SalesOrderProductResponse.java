/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.salesorder.resource.salesorder;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author joao
 */
@Getter
@Setter
public class SalesOrderProductResponse {
    private Integer id;
    private String name;
    private String description;
    private String barcode;
    private BigDecimal unitPrice;
}
