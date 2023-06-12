package com.fmss.automotiveecombackend.service;

import com.fmss.automotiveecombackend.data.dbmodel.Address;
import com.fmss.automotiveecombackend.data.dbmodel.Basket;
import com.fmss.automotiveecombackend.data.dbmodel.Order;
import com.fmss.automotiveecombackend.data.dbmodel.Product;
import com.fmss.automotiveecombackend.data.dto.request.AddressRequestPayload;
import com.fmss.automotiveecombackend.data.repository.AddressRepository;
import com.fmss.automotiveecombackend.data.repository.OrderRepository;
import com.fmss.automotiveecombackend.mapper.AddressMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;
    private final BasketService basketService;

    public void order(Basket basket, AddressRequestPayload addressPayload) {

        Address addressEntity = addressRepository.save(addressMapper.toEntity(addressPayload));

        BigDecimal amount = basketService.calculatePrice(basket);

        Order order = Order.builder()
                .address(addressEntity)
                .user(basket.getUser())
                .products(basket.getProducts())
                .amount(amount)
                .build();

        orderRepository.save(order);
    }
}
