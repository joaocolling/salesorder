/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.salesorder.business.salesorder.business;

import com.example.salesorder.business.salesorder.domain.subdomain.Payment;
import com.example.salesorder.business.salesorder.domain.subdomain.PaymentRepository;
import com.example.salesorder.business.salesorder.dto.PaymentDTO;
import java.math.BigDecimal;
import java.math.RoundingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author joao
 */
@Service
public class PaymentService {

    private final PaymentRepository repository;

    @Autowired
    public PaymentService(PaymentRepository repository) {
        this.repository = repository;
    }

    public Payment insertPaymentOfSales(PaymentDTO paymentDTO, BigDecimal amountSalesPrice) {
        Integer installments = paymentDTO.getInstallments();
        BigDecimal installmentPrice = amountSalesPrice.divide(BigDecimal.valueOf(installments), RoundingMode.HALF_EVEN);

        Payment payment = new Payment();
        payment.setMode(paymentDTO.getMode());
        payment.setInstallments(paymentDTO.getInstallments());
        payment.setInstallmentPrice(installmentPrice);
        payment.setAmountPrice(amountSalesPrice);

        return repository.save(payment);
    }

}
