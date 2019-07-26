/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.salesorder.resource.salesorder;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author joao
 */
@Getter
@Setter
public class PaymentResponse {
    private String mode;
    private BigDecimal amountPrice;
    private Integer installments;
    private BigDecimal installmentPrice;
}
