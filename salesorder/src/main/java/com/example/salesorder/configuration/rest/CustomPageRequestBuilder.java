/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.salesorder.configuration.rest;

//import org.springframework.data.domain.Sort;

import org.springframework.data.domain.Sort;


/**
 *
 * @author joao
 */
public class CustomPageRequestBuilder {    
    private final CustomPageRequest customPageRequest;

    public CustomPageRequestBuilder() {
        this.customPageRequest = new CustomPageRequest();
    }
    
    public CustomPageRequestBuilder withPageNumber(Integer pageNumber){
        customPageRequest.setPageNumber(pageNumber);
        return this;
    }
    
    public CustomPageRequestBuilder withPageSize(Integer pageSize){
        customPageRequest.setPageSize(pageSize);
        return this;
    }
    
    public CustomPageRequestBuilder withOrderField(String orderField){
        customPageRequest.setOrderField(orderField);
        return this;
    }
    
    public CustomPageRequestBuilder withOrder(Sort.Direction direction){
        customPageRequest.setDirection(direction);
        return this;
    }
           
    public CustomPageRequest build(){
        return customPageRequest;
    }
    
}
