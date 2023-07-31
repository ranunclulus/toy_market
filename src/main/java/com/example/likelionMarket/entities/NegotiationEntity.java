package com.example.likelionMarket.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "negotiation")
public class NegotiationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "sales_item")
    private SalesItemEntity item;

    @ManyToOne
    @JoinColumn(name = "writer")
    private UserEntity user;

    @Column(nullable = false)
    private Long suggestedPrice;

    private String status;



    private String password;
}
