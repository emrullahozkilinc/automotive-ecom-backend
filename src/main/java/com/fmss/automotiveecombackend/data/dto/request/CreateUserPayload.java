package com.fmss.automotiveecombackend.data.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.UUID;

@Data
public class CreateUserPayload {
    private String name;
    private String email;
    private String phone;
    @JsonIgnore
    private UUID keycloakId;
}
