package com.fmss.automotiveecombackend.data.repository;

import com.fmss.automotiveecombackend.data.dbmodel.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByKeycloakId(UUID keycloakUserId);
}