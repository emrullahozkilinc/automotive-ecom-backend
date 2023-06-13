package com.fmss.automotiveecombackend.service;

import com.fmss.automotiveecombackend.data.dbmodel.Basket;
import com.fmss.automotiveecombackend.data.dbmodel.Product;
import com.fmss.automotiveecombackend.data.dto.request.AddItemToBasketPayload;
import com.fmss.automotiveecombackend.data.dto.request.RemoveItemsFromBasket;
import com.fmss.automotiveecombackend.data.repository.BasketRepository;
import com.fmss.automotiveecombackend.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BasketService {

    private final BasketRepository basketRepository;
    private final ProductService productService;
    private final ProductMapper productMapper;
    private final UserService userService;

    public void addProductToBasket(UUID userKeycloakId, AddItemToBasketPayload payload) {
        Basket basket = userService.getUserByKeycloakId(userKeycloakId).getBasket();

        List<Product> products = productService.filterProducts(payload.getProductIds()).stream().map(productMapper::toProduct).toList();
        productService.reduceProductFromStock(products);

        if (basket.getProducts() == null) {
            basket.setProducts(products);
        } else {
            basket.getProducts().addAll(products);
        }

        basketRepository.save(basket);
    }

    //TODO: Userin stokundan ürün düşmese bile stoktan düşüyor
    public void removeProductFromBasket(UUID userKeycloakId, RemoveItemsFromBasket payload) {
        Basket basket = userService.getUserByKeycloakId(userKeycloakId).getBasket();

        List<Product> products = productService.filterProducts(payload.getProductIds()).stream().map(productMapper::toProduct).toList();
        productService.increaseStock(products);
        basket.getProducts().removeAll(products);
        basketRepository.save(basket);
    }

    public BigDecimal calculatePrice(Basket basket) {
        return basket.getProducts().stream().map(Product::getPrice).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
    }
}
