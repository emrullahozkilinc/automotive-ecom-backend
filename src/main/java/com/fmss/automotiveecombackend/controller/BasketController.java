package com.fmss.automotiveecombackend.controller;

import com.fmss.automotiveecombackend.data.dto.request.AddItemToBasketPayload;
import com.fmss.automotiveecombackend.data.dto.request.RemoveItemsFromBasket;
import com.fmss.automotiveecombackend.service.BasketService;
import com.fmss.automotiveecombackend.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.fmss.automotiveecombackend.constants.UserRoles.*;

@RestController
@RequestMapping("/basket")
@RequiredArgsConstructor
public class BasketController {

    private final BasketService basketService;

    @PreAuthorize(IS_ADMIN_OR_USER)
    @PostMapping("/add-product")
    public void addItemsToBasket(JwtAuthenticationToken jwt, @RequestBody AddItemToBasketPayload payload) {
        basketService.addProductToBasket(UserUtil.fromJwtToUserId(jwt), payload);
    }

    @PreAuthorize(IS_ADMIN_OR_USER)
    @PostMapping("/remove-product")
    public void removeItemsFromBasket(JwtAuthenticationToken jwt, @RequestBody RemoveItemsFromBasket payload) {
        basketService.removeProductFromBasket(UserUtil.fromJwtToUserId(jwt), payload);
    }
}
