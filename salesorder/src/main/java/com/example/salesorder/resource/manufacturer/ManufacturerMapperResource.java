/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.salesorder.resource.manufacturer;

import com.example.salesorder.business.manufacturer.domain.Manufacturer;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joao
 */
public class ManufacturerMapperResource {
    public static ManufacturerResponse mapToManufacturerResponse(Manufacturer origin) {
        if(origin == null){
            return null;
        }
        
        ManufacturerResponse destiny = new ManufacturerResponse();
        destiny.setId(origin.getId());
        destiny.setName(origin.getName());
        
        return destiny;
    }
    
     public static List<ManufacturerResponse> mapToManufacturerResponse(List<Manufacturer> origin) {
        if(origin == null){
            return null;
        }
        
        List<ManufacturerResponse> destiny = new ArrayList<>();
        origin.forEach((originItem) -> {
            destiny.add(mapToManufacturerResponse(originItem));
        });
        
        return destiny;
    }
}
