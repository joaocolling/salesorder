/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.salesorder.business.salesorder.business;

import com.example.salesorder.business.product.business.ProductService;
import com.example.salesorder.business.product.domain.Product;
import com.example.salesorder.business.salesorder.domain.SalesOrder;
import com.example.salesorder.business.salesorder.domain.SalesOrderRepository;
import com.example.salesorder.business.salesorder.domain.subdomain.Consumer;
import com.example.salesorder.business.salesorder.domain.subdomain.Delivery;
import com.example.salesorder.business.salesorder.domain.subdomain.Payment;
import com.example.salesorder.business.salesorder.domain.subdomain.SalesOrderProduct;
import com.example.salesorder.business.salesorder.dto.SalesOrderDTO;
import com.example.salesorder.business.salesorder.dto.SalesOrderProductDTO;
import com.example.salesorder.configuration.exception.CustomException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author joao
 */
@Service
public class SalesOrderService {

    private final SalesOrderRepository repository;
    private final ProductService productService;
    private final PaymentService paymentService;
    private final ConsumerService consumerService;
    private final DeliveryService DeliveryService;

    @Autowired
    public SalesOrderService(SalesOrderRepository repository,
            ProductService productService,
            PaymentService paymentService,
            ConsumerService consumerService,
            DeliveryService DeliveryService) {
        this.repository = repository;
        this.productService = productService;
        this.paymentService = paymentService;
        this.consumerService = consumerService;
        this.DeliveryService = DeliveryService;
    }

    public SalesOrder get(Integer id) {
        Optional<SalesOrder> opt = repository.findById(id);
        return opt.isPresent() ? opt.get() : null;
    }
    
    public SalesOrder getAndValidate(Integer id) {
        SalesOrder salesOrder = get(id);
        if(salesOrder == null){
            throw new CustomException("Pedido de Venda não encontrado.");
        }
        return salesOrder;
    }
    
    public Integer insert(SalesOrderDTO salesOrderDTO) {

        SalesOrder salesOrder = new SalesOrder();
        salesOrder.setStatus(SalesOrder.Const.STATUS_PENDING);
        
        BigDecimal amountSalesPrice = BigDecimal.ZERO;
        List<SalesOrderProduct> salesOrderProducts = new ArrayList<>();
        for (SalesOrderProductDTO salesOrderProductDTO : salesOrderDTO.getProducts()) {
            SalesOrderProduct salesOrderProduct = countProduct(salesOrderProductDTO, salesOrder);
            salesOrderProduct.setSalesOrder(salesOrder);
            
            salesOrderProducts.add(salesOrderProduct);
            amountSalesPrice = amountSalesPrice.add(salesOrderProduct.getAmountPrice());
        }
        salesOrder.setSalesOrderProducts(salesOrderProducts);

        Consumer consumer = consumerService.insertConsumerOfSales(salesOrderDTO.getConsumer());
        salesOrder.setConsumer(consumer);

        Payment payment = paymentService.insertPaymentOfSales(salesOrderDTO.getPayment(), amountSalesPrice);
        salesOrder.setPayment(payment);

        Delivery delivery = DeliveryService.insertDeliveryOfSales(salesOrderDTO.getDelivery());
        salesOrder.setDelivery(delivery);

        return repository.save(salesOrder).getId();
    }

    public void confirm(Integer id){
        SalesOrder salesOrder = getAndValidate(id);
        salesOrder.setStatus(SalesOrder.Const.STATUS_CONFIRMED);
        repository.save(salesOrder);
    }
    
    public void cancel(Integer id){
        SalesOrder salesOrder = getAndValidate(id);
        salesOrder.setStatus(SalesOrder.Const.STATUS_CANCELED);
        repository.save(salesOrder);
    }
    
    private SalesOrderProduct countProduct(SalesOrderProductDTO salesOrderProductDTO, SalesOrder salesOrder) {
        Product product = productService.getAndValidate(salesOrderProductDTO.getId());
        
        BigDecimal units = salesOrderProductDTO.getUnits();
        BigDecimal unitPrice = product.getUnitPrice();
        BigDecimal amountPrice = units.multiply(unitPrice);
        
        SalesOrderProduct salesOrderProduct = new SalesOrderProduct();
        salesOrderProduct.setProductName(product.getName());
        salesOrderProduct.setUnits(units);
        salesOrderProduct.setUnitPrice(unitPrice);
        salesOrderProduct.setAmountPrice(amountPrice);
        salesOrderProduct.setSalesOrder(salesOrder);
        salesOrderProduct.setProduct(product);
        return salesOrderProduct;
    }

}
