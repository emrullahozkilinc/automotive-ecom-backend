package com.fmss.automotiveecombackend.data.dto.request;

import com.fmss.automotiveecombackend.data.payload.CreatePaymentPayload;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderRequestPayload {
    private AddressRequestPayload addressRequestPayload;
    private CreatePaymentPayload createPaymentPayload;
}
