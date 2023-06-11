package com.fmss.automotiveecombackend.util;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.UUID;

public class UserUtil {

    public static UUID fromJwtToUserId(JwtAuthenticationToken principal){
        return UUID.fromString((String) ((Jwt)principal.getPrincipal()).getClaims().get("sub"));
    }
}
