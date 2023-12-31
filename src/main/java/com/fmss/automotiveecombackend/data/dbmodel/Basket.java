package com.fmss.automotiveecombackend.data.dbmodel;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Basket {

    //TODO: sepete her üründen sadece birer tane eklenebiliyor. birden fazla ürün eklenebilir hale geitilecek.
    //TODO: bu sırada product içerisindeki quantity de kontrol edilecek ve ona göre düşülecek


    @Id
    @GeneratedValue
    private UUID id;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Product> products;

    @OneToOne
    @JsonBackReference
    private User user;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
