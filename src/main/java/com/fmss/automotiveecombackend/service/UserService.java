package com.fmss.automotiveecombackend.service;

import com.fmss.automotiveecombackend.data.dto.request.CreateUserPayload;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    public UUID createUser(UUID userId, CreateUserPayload payload) {
        return UUID.randomUUID();
    }
}
