package com.fmss.automotiveecombackend.service;

import com.fmss.automotiveecombackend.data.dbmodel.CreditCard;
import com.fmss.automotiveecombackend.data.dbmodel.User;
import com.fmss.automotiveecombackend.data.payload.CreatePaymentPayload;
import com.fmss.automotiveecombackend.data.repository.CreditCardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreditCardService {

    private final CreditCardRepository creditCardRepository;

    public void createPayment(User user, CreatePaymentPayload paymentPayload, BigDecimal price) {

        if (!paymentPayload.isShouldSaveDb())
            return;

        if (creditCardRepository.findByCardNumber(paymentPayload.getCardNumber()).isPresent())
            return;

        log.info("Payment created. Amount: {}", price);

        CreditCard creditCard = getCreditCard(user, paymentPayload);
        creditCardRepository.save(creditCard);
    }

    private CreditCard getCreditCard(User user, CreatePaymentPayload paymentPayload) {
        return CreditCard.builder()
                .cardDate(paymentPayload.getCardDate())
                .cardNumber(paymentPayload.getCardNumber())
                .user(user)
                .cvc(paymentPayload.getCvv())
                .build();
    }
}
