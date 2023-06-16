package com.fmss.automotiveecombackend.data.repository;

import com.fmss.automotiveecombackend.data.dbmodel.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    @Query("select u from User u " +
            "join Basket b on u.basket.id = b.id " +
            "join u.creditCards c on c.id in " +
            "(select c1.id from CreditCard c1) " +
            "where u.keycloakId = :keycloakUserId")
    Optional<User> findByKeycloakId(UUID keycloakUserId);
}