/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.salesorder.business.salesorder.domain.subdomain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author joao
 */
public interface PaymentRepository extends JpaRepository<Payment, Integer>{
    
}
