package com.fmss.automotiveecombackend.service;

import com.fmss.automotiveecombackend.data.dbmodel.User;
import com.fmss.automotiveecombackend.data.dto.request.CreateUserPayload;
import com.fmss.automotiveecombackend.data.repository.UserRepository;
import com.fmss.automotiveecombackend.exception.exception_classes.ClientBadRequestException;
import com.fmss.automotiveecombackend.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.fmss.automotiveecombackend.constants.ExceptionMessageConstants.USER_ALREADY_EXIST;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    //TODO: Userlar redisten gelecek

    public UUID createUser(UUID userKeycloakId, CreateUserPayload payload) {
        if (userRepository.findByKeycloakId(userKeycloakId).isPresent()) {
            throw new ClientBadRequestException(USER_ALREADY_EXIST);
        }

        payload.setKeycloakId(userKeycloakId);

        User user = userMapper.toEntity(payload);
        userRepository.save(user);

        return user.getId();
    }

    public User getUserByKeycloakId(UUID userKeycloakId) {
        return userRepository.findByKeycloakId(userKeycloakId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
    }

    public void clearUserBasket(User user) {
        user.setBasket(null);
        userRepository.save(user);
    }

}
