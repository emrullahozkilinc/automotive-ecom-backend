package com.fmss.automotiveecombackend.data.repository;

import com.fmss.automotiveecombackend.data.dbmodel.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
}