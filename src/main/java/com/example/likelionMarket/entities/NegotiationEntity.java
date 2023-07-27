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

    @Column(nullable = false)
    private Long suggestedPrice;

    private String status;

    @Column(nullable = false)
    private String writer;

    private String password;
}
