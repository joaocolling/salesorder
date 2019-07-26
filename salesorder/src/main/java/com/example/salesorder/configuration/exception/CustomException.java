/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.salesorder.configuration.exception;

/**
 *
 * @author joao
 */
public class CustomException extends RuntimeException{

    public CustomException(String mensagem) {
        super(mensagem);
    }
    
}
