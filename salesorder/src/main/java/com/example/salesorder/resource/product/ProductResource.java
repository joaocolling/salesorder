/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.salesorder.resource.product;

import com.example.salesorder.business.product.business.ProductService;
import com.example.salesorder.business.product.domain.Product;
import com.example.salesorder.business.product.dto.ProductDTO;
import java.util.List;
import javax.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
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
@RequestMapping("product")
public class ProductResource {
    private final ProductService service;

    @Autowired
    public ProductResource(ProductService service) {
        this.service = service;
    }
    
    @GetMapping("get/{id}")
    public ResponseEntity get(@PathVariable("id") Integer id) {
        Product product = service.getAndValidate(id);
        ProductResponse response = ProductMapperResource.mapToProductResponse(product);
        return ResponseEntity.ok(response);
    } 
    
    @GetMapping("findProducts")
    public ResponseEntity findProducts(@PathParam("filter") String filter){
        List<Product> products = service.findProductsOrderByName();
        List<ProductResponse> response = ProductMapperResource.mapToProductResponse(products);
        return ResponseEntity.ok(response);
    } 
    
    @PostMapping("insert")
    public ResponseEntity insert(@RequestBody ProductDTO productDTO) {
        Integer idProduct = service.insert(productDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(idProduct);
    }
    
    @PostMapping("update/{id}")
    public ResponseEntity update(@PathVariable("id") Integer id, @RequestBody ProductDTO productDTO) {
        Product product = service.update(id, productDTO);
        return ResponseEntity.ok(product);
    }
   
    @DeleteMapping("delete/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
