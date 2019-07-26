/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.salesorder.business.product.business;

import com.example.salesorder.business.product.dto.ProductManufacturerDTO;

/**
 *
 * @author joao
 */
public class ProductManufacturerDTOBuilder {

    private ProductManufacturerDTO productManufacturerDTO;

    public ProductManufacturerDTOBuilder() {
    }

    public ProductManufacturerDTOBuilder withId(Integer id) {
        productManufacturerDTO.setId(id);
        return this;
    }

    public ProductManufacturerDTO build() {
        return this.productManufacturerDTO;
    }

}
