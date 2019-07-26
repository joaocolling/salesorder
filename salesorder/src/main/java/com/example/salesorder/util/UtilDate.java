/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.salesorder.util;

import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 *
 * @author joao
 */
public class UtilDate {
 
    public static LocalDateTime getTimeUTC(){
        return LocalDateTime.now(ZoneId.of("UTC"));
    }
}
