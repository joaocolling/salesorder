/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.salesorder.business.product.business;

import com.example.salesorder.business.product.dto.ProductDTO;
import com.example.salesorder.business.product.dto.ProductManufacturerDTO;
import java.math.BigDecimal;

/**
 *
 * @author joao
 */
public class ProductDTOBuilder {

    private ProductDTO productDTO;
    
    public ProductDTOBuilder() {   
    }
    
    public ProductDTOBuilder withName(String name){
        productDTO.setName(name);
        return this;
    }
    
    public ProductDTOBuilder withDescription(String description){
        productDTO.setDescription(description);
        return this;
    }
    
    public ProductDTOBuilder withBarcode(String barcode){
        productDTO.setBarcode(barcode);
        return this;
    }
    
    public ProductDTOBuilder withManufacturer(ProductManufacturerDTO manufacturer){
        productDTO.setManufacturer(manufacturer);
        return this;
    }
    
    public ProductDTOBuilder withUnitPrice(BigDecimal unitPrice){
        productDTO.setUnitPrice(unitPrice);
        return this;
    }
    public ProductDTO build(){
        return this.productDTO;
    }
    
}
