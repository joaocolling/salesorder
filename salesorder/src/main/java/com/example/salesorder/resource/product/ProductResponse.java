/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.salesorder.resource.product;

import com.example.salesorder.resource.manufacturer.ManufacturerResponse;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author joao
 */
@Getter
@Setter
public class ProductResponse {
    private Integer id;
    private String name;
    private String description;
    private String barcode;
    private ManufacturerResponse manufacturer;
    private BigDecimal unitPrice;
}
