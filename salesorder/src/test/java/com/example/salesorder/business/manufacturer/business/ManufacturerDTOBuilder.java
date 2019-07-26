/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.salesorder.business.manufacturer.business;

import com.example.salesorder.business.manufacturer.dto.ManufacturerDTO;

/**
 *
 * @author joao
 */

public class ManufacturerDTOBuilder {

    private ManufacturerDTO manufacturerDTO;
    
    public ManufacturerDTOBuilder() {   
    }
    
    public ManufacturerDTOBuilder withName(String name){
        manufacturerDTO.setName(name);
        return this;
    }
    
    public ManufacturerDTO build(){
        return this.manufacturerDTO;
    }
}
