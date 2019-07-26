/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.salesorder.resource.salesorder;

import com.example.salesorder.business.salesorder.domain.SalesOrder;
import com.example.salesorder.business.salesorder.domain.subdomain.Consumer;
import com.example.salesorder.business.salesorder.domain.subdomain.Delivery;
import com.example.salesorder.business.salesorder.domain.subdomain.Payment;
import com.example.salesorder.business.salesorder.domain.subdomain.SalesOrderProduct;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joao
 */
public class SalesOrderMapperResource {

    public static SalesOrderResponse mapToSalesOrderResponse(SalesOrder origin) {
        if (origin == null) {
            return null;
        }

        SalesOrderResponse destiny = new SalesOrderResponse();
        destiny.setId(origin.getId());
        destiny.setStatus(origin.getStatus());
        destiny.setProducts(mapToSalesOrderProductResponse(origin.getSalesOrderProducts()));
        destiny.setConsumer(mapToConsumerResponse(origin.getConsumer()));
        destiny.setPayment(mapToPaymentResponse(origin.getPayment()));
        destiny.setDelivery(mapToDeliveryResponse(origin.getDelivery()));

        return destiny;
    }

    public static List<SalesOrderResponse> mapToSalesOrderResponse(List<SalesOrder> origin) {
        if (origin == null) {
            return null;
        }

        List<SalesOrderResponse> destiny = new ArrayList<>();
        origin.forEach((originItem) -> {
            destiny.add(mapToSalesOrderResponse(originItem));
        });

        return destiny;
    }

    public static SalesOrderProductResponse mapToSalesOrderProductResponse(SalesOrderProduct origin) {
        if (origin == null) {
            return null;
        }

        SalesOrderProductResponse destiny = new SalesOrderProductResponse();
        destiny.setId(origin.getId());
        destiny.setName(origin.getProductName());
        destiny.setDescription(origin.getProduct().getDescription());
        destiny.setBarcode(origin.getProduct().getBarcode());
        destiny.setUnitPrice(origin.getUnitPrice()
                .setScale(2, RoundingMode.HALF_EVEN));

        return destiny;
    }

    public static List<SalesOrderProductResponse> mapToSalesOrderProductResponse(List<SalesOrderProduct> origin) {
        if (origin == null) {
            return null;
        }

        List<SalesOrderProductResponse> destiny = new ArrayList<>();
        origin.forEach((originItem) -> {
            destiny.add(mapToSalesOrderProductResponse(originItem));
        });

        return destiny;
    }

    public static PaymentResponse mapToPaymentResponse(Payment origin) {
        if (origin == null) {
            return null;
        }

        PaymentResponse destiny = new PaymentResponse();
        destiny.setMode(origin.getMode());
        destiny.setAmountPrice(origin.getAmountPrice());
        destiny.setInstallments(origin.getInstallments());
        destiny.setInstallmentPrice(origin.getInstallmentPrice()
                .setScale(2, RoundingMode.HALF_EVEN));

        return destiny;
    }

    public static List<PaymentResponse> mapToPaymentResponse(List<Payment> origin) {
        if (origin == null) {
            return null;
        }

        List<PaymentResponse> destiny = new ArrayList<>();
        origin.forEach((originItem) -> {
            destiny.add(mapToPaymentResponse(originItem));
        });

        return destiny;
    }

    public static ConsumerResponse mapToConsumerResponse(Consumer origin) {
        if (origin == null) {
            return null;
        }

        ConsumerResponse destiny = new ConsumerResponse();
        destiny.setId(origin.getId());
        destiny.setName(origin.getName());
        destiny.setPhone(origin.getPhone());
        destiny.setEmail(origin.getEmail());

        return destiny;
    }

    public static List<ConsumerResponse> mapToConsumerResponse(List<Consumer> origin) {
        if (origin == null) {
            return null;
        }

        List<ConsumerResponse> destiny = new ArrayList<>();
        origin.forEach((originItem) -> {
            destiny.add(mapToConsumerResponse(originItem));
        });

        return destiny;
    }

    public static DeliveryResponse mapToDeliveryResponse(Delivery origin) {
        if (origin == null) {
            return null;
        }

        DeliveryResponse destiny = new DeliveryResponse();
        destiny.setMode(origin.getMode());

        return destiny;
    }

    public static List<DeliveryResponse> mapToDeliveryResponse(List<Delivery> origin) {
        if (origin == null) {
            return null;
        }

        List<DeliveryResponse> destiny = new ArrayList<>();
        origin.forEach((originItem) -> {
            destiny.add(mapToDeliveryResponse(originItem));
        });

        return destiny;
    }
}
