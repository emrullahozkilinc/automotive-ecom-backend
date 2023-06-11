package com.fmss.automotiveecombackend.configuration;

import lombok.NonNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class JwtAuthorizationConfig implements Converter<Jwt, AbstractAuthenticationToken> {

    private final JwtGrantedAuthoritiesConverter jwtConverter = new JwtGrantedAuthoritiesConverter();

    @Override
    public AbstractAuthenticationToken convert(@NonNull Jwt source) {

        Collection<GrantedAuthority> authorities = Stream.concat(
                jwtConverter.convert(source).stream(),
                extractRoles(source).stream()
        ).collect(Collectors.toSet());

        return new JwtAuthenticationToken(source, authorities, principalClaimName(source));
    }

    private String principalClaimName(Jwt jwt) {
        String principalAttribute = "preffered_username";
        return jwt.getClaim(principalAttribute);
    }

    private Collection<? extends GrantedAuthority> extractRoles(Jwt jwt){
        Map<String, Object> resourceAccess = jwt.getClaim("resource_access");

        if (Objects.isNull(resourceAccess))
            return Set.of();

        Map<String, Object> resource = (Map<String, Object>) resourceAccess.get("automotive-ecom");

        if (Objects.isNull(resource)){
            return Set.of();
        }

        Collection<String> resourceRoles = (Collection<String>) resource.get("roles");

        return resourceRoles.stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role)).collect(Collectors.toSet());
    }
}
