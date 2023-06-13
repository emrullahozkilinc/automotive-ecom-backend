package com.fmss.automotiveecombackend.data.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RemoveItemsFromBasket {
    private List<UUID> productIds;
}
