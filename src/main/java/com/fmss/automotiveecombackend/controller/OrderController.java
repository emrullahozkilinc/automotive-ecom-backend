package com.fmss.automotiveecombackend.controller;

import com.fmss.automotiveecombackend.constants.UserRoles;
import com.fmss.automotiveecombackend.data.dto.request.AddressRequestPayload;
import com.fmss.automotiveecombackend.data.dto.request.CreateOrderRequestPayload;
import com.fmss.automotiveecombackend.service.OrderService;
import com.fmss.automotiveecombackend.service.UserService;
import com.fmss.automotiveecombackend.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PreAuthorize(UserRoles.IS_USER)
    @PostMapping
    public ResponseEntity<String> createOrder(JwtAuthenticationToken jwt, @RequestBody CreateOrderRequestPayload payload) {
        orderService.createOrderForUser(
                UserUtil.fromJwtToUserId(jwt),
                payload.getAddressRequestPayload(),
                payload.getCreatePaymentPayload());
        return ResponseEntity.ok("Order successful");
    }
}
