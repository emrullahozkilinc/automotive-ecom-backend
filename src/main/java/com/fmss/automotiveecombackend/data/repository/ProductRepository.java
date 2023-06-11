package com.fmss.automotiveecombackend.data.repository;

import com.fmss.automotiveecombackend.data.dbmodel.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

//Sorguda join varsa query yazılacak.
public interface ProductRepository extends JpaRepository<Product, UUID> {

    List<Product> findByIdIn(List<UUID> productIds);
}