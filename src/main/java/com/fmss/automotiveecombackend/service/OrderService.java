package com.fmss.automotiveecombackend.service;

import com.fmss.automotiveecombackend.data.dbmodel.*;
import com.fmss.automotiveecombackend.data.dto.request.AddressRequestPayload;
import com.fmss.automotiveecombackend.data.payload.CreatePaymentPayload;
import com.fmss.automotiveecombackend.data.repository.AddressRepository;
import com.fmss.automotiveecombackend.data.repository.OrderRepository;
import com.fmss.automotiveecombackend.mapper.AddressMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;
    private final BasketService basketService;
    private final CreditCardService creditCardService;
    private final UserService userService;

    @Transactional
    public void order(Basket basket, AddressRequestPayload addressPayload, CreatePaymentPayload paymentPayload) {

        Address addressEntity = addressRepository.save(addressMapper.toEntity(addressPayload));
        Order order = getOrder(basket, addressEntity);

        creditCardService.createPayment(basket.getUser(), paymentPayload, basketService.calculatePrice(basket));
        orderRepository.save(order);
    }

    private Order getOrder(Basket basket, Address addressEntity) {
        return Order.builder()
                .address(addressEntity)
                .user(basket.getUser())
                .products(basket.getProducts())
                .amount(basketService.calculatePrice(basket))
                .build();
    }

    //TODO: Order sonrasında user'ın basketindeki itemleri düşürmek gerekiyor.
    public void createOrderForUser(UUID userKeycloakId, AddressRequestPayload addressPayload, CreatePaymentPayload paymentPayload) {
        User user = userService.getUserByKeycloakId(userKeycloakId);
        order(user.getBasket(), addressPayload, paymentPayload);
    }
}
