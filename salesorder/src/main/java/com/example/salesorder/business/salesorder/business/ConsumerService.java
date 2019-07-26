/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.salesorder.business.salesorder.business;

import com.example.salesorder.business.salesorder.domain.subdomain.Consumer;
import com.example.salesorder.business.salesorder.domain.subdomain.ConsumerRepository;
import com.example.salesorder.business.salesorder.dto.ConsumerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author joao
 */
@Service
public class ConsumerService {

    private ConsumerRepository repository;

    @Autowired
    public ConsumerService(ConsumerRepository repository) {
        this.repository = repository;
    }

    public Consumer insertConsumerOfSales(ConsumerDTO consumerDTO) {
        Consumer consumer = new Consumer();
        consumer.setName(consumerDTO.getName());
        consumer.setPhone(consumerDTO.getPhone());
        consumer.setEmail(consumerDTO.getEmail());

        return repository.save(consumer);
    }

}
