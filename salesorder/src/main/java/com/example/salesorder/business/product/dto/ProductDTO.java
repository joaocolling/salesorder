/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.salesorder.business.product.dto;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author joao
 */
@NoArgsConstructor
@Getter
@Setter
public class ProductDTO {

    private String name;
    private String description;
    private String barcode;
    private ProductManufacturerDTO manufacturer;
    private BigDecimal unitPrice;
}
