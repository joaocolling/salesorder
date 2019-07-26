/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.salesorder.business.product.business;

import com.example.salesorder.business.manufacturer.business.ManufacturerService;
import com.example.salesorder.business.manufacturer.domain.Manufacturer;
import com.example.salesorder.business.product.domain.Product;
import com.example.salesorder.business.product.domain.ProductRepository;
import com.example.salesorder.business.product.dto.ProductDTO;
import com.example.salesorder.configuration.exception.CustomException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author joao
 */
@Service
public class ProductService {
    
    private final ProductRepository repository;
    private final ManufacturerService manufacturerService;

    @Autowired
    public ProductService(ProductRepository repository,
            ManufacturerService manufacturerService) {
        this.repository = repository;
        this.manufacturerService = manufacturerService;
    }
    
    public Product get(Integer idProduct){
        Optional<Product> opt = repository.findById(idProduct);
        return opt.isPresent() ? opt.get(): null;
    }
    
    @Transactional(readOnly = true)
    public Product getAndValidate(Integer idProduct){
        Product product = get(idProduct);
        if(product == null){
            throw new CustomException("Produto não encontrado.");
        }
        return product;
    }
    
    @Transactional(readOnly = true)
    public List<Product> findProductsOrderByName(){
       return repository.findAll(new Sort(Sort.Direction.ASC, "name"));
    }
    
    //@TODO Null em @RequestScoped (Resolver) 
//     @Transactional(readOnly = true)
//    public Page<Product> findProductsPagedAndOrdered() {
//        CustomPageRequest customPageRequest = getCustomPageRequest();
//        if (customPageRequest.getPageNumber() != null && customPageRequest.getPageSize() != null) {
//            Pageable pageable;
//            if (customPageRequest.getOrderField() != null && customPageRequest.getDirection() != null) {
//                pageable = PageRequest.of(customPageRequest.getPageNumber(), customPageRequest.getPageSize(),
//                        Sort.by(Sort.Direction.DESC, customPageRequest.getOrderField()));
//            } else {
//                pageable = PageRequest.of(customPageRequest.getPageNumber(), customPageRequest.getPageSize());
//            }
//            return repository.findAll(pageable);
//        }
//
//        return repository.findAll(Pageable.unpaged());
//    }
    
    public Integer insert(ProductDTO productDTO){
        Product product = new Product();
        
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setBarcode(productDTO.getBarcode());
        product.setUnitPrice(productDTO.getUnitPrice());
        
        Manufacturer manufacturer = manufacturerService.getAndValidated(productDTO.getManufacturer().getId());
        product.setManufacturer(manufacturer);
        
        return repository.save(product).getId();
    }

    public Product update(Integer id, ProductDTO productDTO) {
        Product product = getAndValidate(id);
        
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setBarcode(productDTO.getBarcode());
        product.setUnitPrice(productDTO.getUnitPrice());
        
        Manufacturer manufacturer = manufacturerService.getAndValidated(productDTO.getManufacturer().getId());
        product.setManufacturer(manufacturer);
        
        return repository.save(product);
    }

    public void delete(Integer id) {
        Product product = getAndValidate(id);
        repository.deleteById(id);
    }
}