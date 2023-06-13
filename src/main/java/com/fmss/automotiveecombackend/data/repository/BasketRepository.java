package com.fmss.automotiveecombackend.data.repository;

import com.fmss.automotiveecombackend.data.dbmodel.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BasketRepository extends JpaRepository<Basket, UUID> {

    Optional<Basket> findByUserId(UUID userId);

//    Optional<Basket> getBasketWithId();
}