package com.example.likelionMarket.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "sales_item")
public class salesItemEntity {

    // PK
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 필수 포함 요소: 제목, 설명, 최소 가격
    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    private String image_url;

    @Column(nullable = false)
    private Integer min_price_wanted;

    private String status;

    private String writer;

    private String password;
}
