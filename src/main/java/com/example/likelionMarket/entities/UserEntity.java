package com.example.likelionMarket.entities;

import com.example.likelionMarket.dtos.SalesItemDto;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;
    private String password;
    private LocalDateTime createdAt;
    private String email;
    private String phone;

    @OneToMany(mappedBy = "user")
    private List<SalesItemEntity> salesItem;

    @OneToMany(mappedBy = "user")
    private List<NegotiationEntity> negotiation;
}
