package com.example.likelionMarket.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "sales_item")
public class SalesItemEntity {

    // PK
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 필수 포함 요소: 제목, 설명, 최소 가격
    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    private String imageUrl;

    @Column(nullable = false)
    private Integer minPriceWanted;

    private String status;

    @ManyToOne
    @JoinColumn(name = "writer")
    private UserEntity user;

    private String password;

    @OneToMany(mappedBy = "item")
    private List<CommentEntity> comments;

    @OneToMany(mappedBy = "item")
    private List<NegotiationEntity> negotiations;
}
