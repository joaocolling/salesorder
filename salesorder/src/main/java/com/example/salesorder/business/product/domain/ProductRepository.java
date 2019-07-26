/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.salesorder.business.product.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author joao
 */
public interface ProductRepository extends JpaRepository<Product, Integer>{
}
