package com.fmss.automotiveecombackend.data.dbmodel;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.uuid.UuidGenerator;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue
    private UUID id;

    @OneToMany
    private List<Product> products;

    private BigDecimal amount;

    @OneToOne
    private User user;

    @OneToOne(mappedBy = "order")
    @JsonManagedReference
    private Address address;
}
