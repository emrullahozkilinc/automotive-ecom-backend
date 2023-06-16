package com.fmss.automotiveecombackend.data.repository;

import com.fmss.automotiveecombackend.data.dbmodel.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface CreditCardRepository extends JpaRepository<CreditCard, UUID> {

    @Query("select c from CreditCard c " +
            "join User u on c.user.id = u.id " +
            "where c.cardNumber = :cardNumber")
    Optional<CreditCard> findByCardNumber(String cardNumber);
}