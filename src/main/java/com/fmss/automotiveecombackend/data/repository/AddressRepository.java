package com.fmss.automotiveecombackend.data.repository;

import com.fmss.automotiveecombackend.data.dbmodel.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, UUID> {
}