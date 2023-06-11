package com.fmss.automotiveecombackend.data.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddProductPayload {
    private String name;
    private int quantity;
    private BigDecimal price;
}
