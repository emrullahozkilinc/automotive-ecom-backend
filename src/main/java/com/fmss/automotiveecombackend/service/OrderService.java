package com.fmss.automotiveecombackend.service;

import com.fmss.automotiveecombackend.data.dbmodel.*;
import com.fmss.automotiveecombackend.data.dto.request.AddressRequestPayload;
import com.fmss.automotiveecombackend.data.payload.CreatePaymentPayload;
import com.fmss.automotiveecombackend.data.repository.OrderRepository;
import com.fmss.automotiveecombackend.mapper.AddressMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final AddressMapper addressMapper;
    private final BasketService basketService;
    private final CreditCardService creditCardService;
    private final UserService userService;
    private final MailService mailService;

    public void createOrderForUser(UUID userKeycloakId, AddressRequestPayload addressPayload, CreatePaymentPayload paymentPayload) {
        User user = userService.getUserByKeycloakId(userKeycloakId);
        order(user.getBasket(), addressPayload, paymentPayload);
        userService.clearUserBasket(user);
    }

    private void sendMailToUser(User user, Order order) {
        mailService.sendHtmlEmail(user.getEmail(), "Order Successful", prepareMailBody(order));
    }

    //TODO: QUANTİTİY DÜZELİNCE DÜZELCEK
    private String prepareMailBody(Order order) {
        String listItems =
            order.getProducts().stream().map((product) ->
                "<li>" +
                "Product Name: " + product.getName() + " " +
                "Quantity: " + 1 + " " +
                "Price: " + order.getAmount() + " " +
                "</li>"
            ).reduce((f,s) -> f+s)
            .orElse("");

        return "<ul>" + listItems + "</ul>";
    }

    @Transactional
    public void order(Basket basket, AddressRequestPayload addressPayload, CreatePaymentPayload paymentPayload) {

        Address addressEntity = addressMapper.toEntity(addressPayload);
        Order order = getOrder(basket, addressEntity);

        creditCardService.createPayment(basket.getUser(), paymentPayload, basketService.calculatePrice(basket));
        orderRepository.save(order);
        sendMailToUser(basket.getUser(), order);
    }

    private Order getOrder(Basket basket, Address addressEntity) {
        return Order.builder()
                .address(addressEntity)
                .user(basket.getUser())
                .products(basket.getProducts())
                .amount(basketService.calculatePrice(basket))
                .build();
    }
}
