/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.salesorder.business.salesorder.business;

import com.example.salesorder.business.salesorder.domain.subdomain.Delivery;
import com.example.salesorder.business.salesorder.domain.subdomain.DeliveryRepository;
import com.example.salesorder.business.salesorder.dto.DeliveryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author joao
 */
@Service
public class DeliveryService {

    private final DeliveryRepository repository;

    @Autowired
    public DeliveryService(DeliveryRepository repository) {
        this.repository = repository;
    }
    
    public Delivery insertDeliveryOfSales(DeliveryDTO deliveryDTO){
        Delivery delivery = new Delivery();
        delivery.setMode(deliveryDTO.getMode());
        
        return repository.save(delivery);
    }

}
