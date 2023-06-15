package com.fmss.automotiveecombackend.data.payload;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreatePaymentPayload {

    private String cardNumber;
    private String cardDate;
    private String cvv;
    private boolean shouldSaveDb;
}
