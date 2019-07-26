/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.salesorder.resource.manufacturer;

import com.example.salesorder.business.manufacturer.dto.ManufacturerDTO;
import com.example.salesorder.business.manufacturer.business.ManufacturerService;
import com.example.salesorder.business.manufacturer.domain.Manufacturer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author joao
 */
@RestController
@RequestMapping("manufacturer")
public class ManufacturerResource {
    private final ManufacturerService service;

    public ManufacturerResource(ManufacturerService service) {
        this.service = service;
    }
    
    @GetMapping("get/{id}")
    public ResponseEntity get(@PathVariable("id") Integer id) {
        Manufacturer manufacturer = service.get(id);
        ManufacturerResponse response = ManufacturerMapperResource.mapToManufacturerResponse(manufacturer);
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("insert")
    public Integer insert(@RequestBody ManufacturerDTO manufacturerDTO) {
        return service.insert(manufacturerDTO);
    }
    
    @PostMapping("update/{id}")
    public ResponseEntity update(@PathVariable("id") Integer id, @RequestBody ManufacturerDTO manufacturerDTO) {
        Manufacturer manufacturer = service.update(id, manufacturerDTO);
        ManufacturerResponse response = ManufacturerMapperResource.mapToManufacturerResponse(manufacturer);
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("delete/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    
}
