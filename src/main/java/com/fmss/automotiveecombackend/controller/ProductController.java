package com.fmss.automotiveecombackend.controller;

import com.fmss.automotiveecombackend.data.dto.request.AddProductPayload;
import com.fmss.automotiveecombackend.data.dto.request.EditProductPayload;
import com.fmss.automotiveecombackend.data.dto.response.ProductResponse;
import com.fmss.automotiveecombackend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.fmss.automotiveecombackend.constants.UserRoles.*;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    @PreAuthorize(IS_ADMIN_OR_USER)
    public ResponseEntity<List<ProductResponse>> getProduct() {
        return ResponseEntity.ok(productService.getProducts());
    }

    @GetMapping("/{id}")
    @PreAuthorize(IS_ADMIN_OR_USER)
    public ResponseEntity<ProductResponse> getProduct(@PathVariable("id") String id) {
        return ResponseEntity.ok(productService.getProduct(id));
    }

    @PostMapping("/admin")
    @PreAuthorize(IS_ADMIN)
    public ResponseEntity<UUID> addProduct(@RequestBody AddProductPayload payload) {
        return ResponseEntity.ok(productService.addProduct(payload));
    }

    @PreAuthorize(IS_ADMIN)
    @PutMapping("/admin/{id}")
    public ResponseEntity<UUID> editProduct(@PathVariable("id") String id ,@RequestBody EditProductPayload payload) {
        return ResponseEntity.ok(productService.editProduct(id, payload));
    }

    @PreAuthorize(IS_ADMIN)
    @DeleteMapping("/admin/{id}")
    public ResponseEntity<UUID> deleteProduct(@PathVariable("id") String id) {
        return ResponseEntity.ok(productService.deleteProduct(id));
    }

}
