/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.salesorder.resource.salesorder;

import com.example.salesorder.business.salesorder.business.SalesOrderService;
import com.example.salesorder.business.salesorder.domain.SalesOrder;
import com.example.salesorder.business.salesorder.dto.SalesOrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("salesorder")
public class SalesOrderResource {

    private final SalesOrderService service;

    @Autowired
    public SalesOrderResource(SalesOrderService service) {
        this.service = service;
    }

    @PostMapping("insert")
    public Integer insert(@RequestBody SalesOrderDTO salesOrderDTO) {
        return service.insert(salesOrderDTO);
    }

    @GetMapping("get/{id}")
    public ResponseEntity get(@PathVariable("id") Integer id) {
        SalesOrder salesOrder = service.getAndValidate(id);
        SalesOrderResponse salesOrderResponse = SalesOrderMapperResource.mapToSalesOrderResponse(salesOrder);
        return ResponseEntity.ok(salesOrderResponse);
    }

    @GetMapping("confirm/{id}")
    public ResponseEntity confirm(@PathVariable("id") Integer id) {
        service.confirm(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("cancel/{id}")
    public ResponseEntity cancel(@PathVariable("id") Integer id) {
        service.cancel(id);
        return ResponseEntity.noContent().build();
    }

}
