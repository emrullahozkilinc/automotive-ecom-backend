package com.fmss.automotiveecombackend.data.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class NotFoundResponsePayload {
    private UUID id;
    private String message;
}
