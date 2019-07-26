/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.salesorder.configuration.rest;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;

/**
 *
 * @author joao
 */
@Getter
@Setter
public class CustomPageRequest {
    private Integer pageNumber;
    private Integer pageSize;
    private String orderField;
    private Sort.Direction direction;
}
