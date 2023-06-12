package com.fmss.automotiveecombackend.service;

import com.fmss.automotiveecombackend.data.dbmodel.Basket;
import com.fmss.automotiveecombackend.data.dbmodel.Product;
import com.fmss.automotiveecombackend.data.dbmodel.User;
import com.fmss.automotiveecombackend.data.repository.BasketRepository;
import com.fmss.automotiveecombackend.exception.exception_classes.BasketNotFoundException;
import com.fmss.automotiveecombackend.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BasketService {

    private final BasketRepository basketRepository;
    private final ProductService productService;
    private final ProductMapper productMapper;

    public void createBasketForUser(User user) {
        basketRepository.save(Basket.builder().user(user).products(new ArrayList<>()).build());
    }

    public void addProductToBasket(String basketId, List<String> productIds) {
        Basket basket = basketRepository.findById(UUID.fromString(basketId))
                .orElseThrow(() -> new BasketNotFoundException("Basket not found with id: " + basketId));

        List<Product> products = productService.filterProducts(productIds).stream().map(productMapper::toProduct).toList();

        productService.reduceProductFromStock(products);

        basket.getProducts().addAll(products);
        basketRepository.save(basket);
    }

    public void removeProductFromBasket(String basketId, List<String> productIds) {
        Basket basket = basketRepository.findById(UUID.fromString(basketId))
                .orElseThrow(() -> new BasketNotFoundException("Basket not found with id: " + basketId));

        List<Product> products = productService.filterProducts(productIds).stream().map(productMapper::toProduct).toList();

        productService.increaseStock(products);

        basket.getProducts().removeAll(products);
        basketRepository.save(basket);
    }

    public BigDecimal calculatePrice(Basket basket) {
        return basket.getProducts().stream().map(Product::getPrice).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
    }
}
