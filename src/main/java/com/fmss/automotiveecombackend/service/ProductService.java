package com.fmss.automotiveecombackend.service;

import com.fmss.automotiveecombackend.data.dbmodel.Product;
import com.fmss.automotiveecombackend.data.dto.request.AddProductPayload;
import com.fmss.automotiveecombackend.data.dto.request.EditProductPayload;
import com.fmss.automotiveecombackend.data.dto.response.ProductResponse;
import com.fmss.automotiveecombackend.data.repository.ProductRepository;
import com.fmss.automotiveecombackend.exception.exception_classes.OutOfStockException;
import com.fmss.automotiveecombackend.exception.exception_classes.ProductNotFoundException;
import com.fmss.automotiveecombackend.mapper.ProductMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductResponse getProduct(String id) {
        return productRepository.findById(UUID.fromString(id))
                .map(productMapper::toProductResponse)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
    }

    public List<ProductResponse> filterProducts(List<UUID> productIds) {
        return productRepository.findByIdIn(productIds.stream().toList()).stream().map(productMapper::toProductResponse).collect(Collectors.toList());
    }

    public List<ProductResponse> getProducts() {
        return productRepository.findAll().stream().map(productMapper::toProductResponse).collect(Collectors.toList());
    }

    @Transactional
    public UUID addProduct(AddProductPayload payload) {
        return productRepository.save(productMapper.fromAddProductPayloadToProduct(payload)).getId();
    }

    @Transactional
    public UUID editProduct(String id, EditProductPayload payload) {
        UUID uid = UUID.fromString(id);
        Product product = productRepository.findById(uid)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
        productMapper.fromEditProductPayloadToProduct(product, payload);
        productRepository.save(product);
        return uid;
    }

    @Transactional
    public UUID deleteProduct(String id) {
        UUID uid = UUID.fromString(id);
        productRepository.deleteById(uid);
        return uid;
    }

    public void reduceProductFromStock(List<Product> products) {
        products.forEach(this::checkStock);
        List<Product> reducedProducts = products.stream().peek(product -> product.setQuantity(product.getQuantity() - 1)).toList();
        productRepository.saveAll(reducedProducts);
    }

    private void checkStock(Product product) {
        if (product.getQuantity() <= 0)
            throw new OutOfStockException("Product out of stock with id: " + product.getId());
    }

    public void increaseStock(List<Product> products){
        List<Product> reducedProducts = products.stream().peek(product -> product.setQuantity(product.getQuantity() + 1)).toList();
        productRepository.saveAll(reducedProducts);
    }
}
