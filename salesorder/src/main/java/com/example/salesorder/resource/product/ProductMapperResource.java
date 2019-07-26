/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.salesorder.resource.product;

import com.example.salesorder.business.product.domain.Product;
import com.example.salesorder.resource.manufacturer.ManufacturerMapperResource;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Page;

/**
 *
 * @author joao
 */
public class ProductMapperResource {

    
    public static ProductResponse mapToProductResponse(Product origin) {
        if (origin == null) {
            return null;
        }

        ProductResponse destiny = new ProductResponse();
        destiny.setId(origin.getId());
        destiny.setName(origin.getName());
        destiny.setDescription(origin.getDescription());
        destiny.setBarcode(origin.getBarcode());
        destiny.setManufacturer(ManufacturerMapperResource.mapToManufacturerResponse(origin.getManufacturer()));
        destiny.setUnitPrice(origin.getUnitPrice());

        return destiny;
    }

    public static Page<ProductResponse> mapToProductResponse(final Page<Product> origin) {
        if (origin == null) {
            return null;
        }

        Page<ProductResponse> destiny = origin.map((t) -> {
            return mapToProductResponse(t);
        });
        
        return destiny;
    }
    
    public static List<ProductResponse> mapToProductResponse(List<Product> origin) {
        if (origin == null) {
            return null;
        }

        List<ProductResponse> destiny = new ArrayList<>();
        origin.forEach((originItem) -> {
            destiny.add(mapToProductResponse(originItem));
        });

        return destiny;
    }
}
