package com.fmss.automotiveecombackend.data.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequestPayload {

    private String city;

    private String country;

    private String zipCode;

    private String streetNumber;

    private String doorNumber;
}
