package com.fmss.automotiveecombackend.controller;

import com.fmss.automotiveecombackend.data.dto.request.CreateUserPayload;
import com.fmss.automotiveecombackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static com.fmss.automotiveecombackend.constants.UserRoles.IS_USER;
import static com.fmss.automotiveecombackend.util.UserUtil.fromJwtToUserId;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PreAuthorize(IS_USER)
    @PostMapping("/create")
    public ResponseEntity<UUID> createUser(JwtAuthenticationToken jwt, @RequestBody CreateUserPayload payload) {
        return ResponseEntity.ok(userService.createUser(fromJwtToUserId(jwt), payload));
    }
}
