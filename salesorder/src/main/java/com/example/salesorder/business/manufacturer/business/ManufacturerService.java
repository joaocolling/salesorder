/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.salesorder.business.manufacturer.business;
//
//import com.example.salesorder.business.manufacturer.domain.Manufacturer;

import com.example.salesorder.business.manufacturer.domain.Manufacturer;
import com.example.salesorder.business.manufacturer.domain.ManufacturerRepository;
import com.example.salesorder.business.manufacturer.dto.ManufacturerDTO;
import com.example.salesorder.configuration.exception.CustomException;
import com.example.salesorder.configuration.rest.CustomPageRequest;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

//import com.example.salesorder.business.manufacturer.dto.ManufacturerDTO;
//import com.example.salesorder.business.manufacturer.domain.ManufacturerRepository;
//import com.example.salesorder.configuration.exception.CustomException;
//import com.example.salesorder.tools.rest.CustomPageRequest;
//import java.util.Optional;
//import org.springframework.stereotype.Service;
//
/**
 *
 * @author joao
 */
@Service
public class ManufacturerService {

    private final ManufacturerRepository repository;

    @Autowired
    public ManufacturerService(ManufacturerRepository repository) {
        this.repository = repository;
    }

    public Manufacturer get(Integer idManufacturer) {
        Optional<Manufacturer> opt = repository.findById(idManufacturer);
        return opt.isPresent() ? opt.get() : null;
    }

    public Manufacturer getAndValidated(Integer id) {
        Manufacturer manufacturer = get(id);
        if (manufacturer == null) {
            throw new CustomException("Fabricante não encontrado.");
        }
        return manufacturer;
    }

    public Page listPagedAndSorted(CustomPageRequest customPageRequest) {
        return repository.findAll(PageRequest.of(
                customPageRequest.getPageNumber(),
                customPageRequest.getPageSize(),
                Sort.by(customPageRequest.getDirection(), customPageRequest.getOrderField())));

    }

    public Integer insert(ManufacturerDTO manufacturerDTO) {
        if (manufacturerDTO.getName() == null || manufacturerDTO.getName().isEmpty()) {
            throw new CustomException("Nome do Fabricante não pode ser vazio.");
        }

        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setName(manufacturerDTO.getName());
        return repository.save(manufacturer).getId();
    }

    public Manufacturer update(Integer idManufacturer, ManufacturerDTO manufacturerDTO) {
        if (manufacturerDTO.getName() == null || manufacturerDTO.getName().isEmpty()) {
            throw new CustomException("Nome do Fabricante não pode ser vazio.");
        }
        
        Manufacturer manufacturer = getAndValidated(idManufacturer);
        manufacturer.setName(manufacturerDTO.getName());
        return repository.save(manufacturer);
    }

    public void delete(Integer idManufacturer) {
        Manufacturer manufacturer = getAndValidated(idManufacturer);
        repository.delete(manufacturer);
    }

}
