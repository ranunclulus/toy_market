package com.example.likelionMarket.dtos;

import com.example.likelionMarket.entities.CommentEntity;
import com.example.likelionMarket.entities.NegotiationEntity;
import lombok.Data;
import com.example.likelionMarket.entities.SalesItemEntity;

import java.util.List;

@Data
public class SalesItemDto {

    private Long id;
    private String title;
    private String description;
    private String imageUrl;
    private Integer minPriceWanted;
    private String status;
    private String writer;
    private String password;


    public static SalesItemDto fromEntity(SalesItemEntity salesItemEntity) {
        SalesItemDto salesItemDto = new SalesItemDto();
        salesItemDto.setId(salesItemEntity.getId());
        salesItemDto.setTitle(salesItemEntity.getTitle());
        salesItemDto.setDescription(salesItemEntity.getDescription());
        salesItemDto.setImageUrl(salesItemEntity.getImageUrl());
        salesItemDto.setMinPriceWanted(salesItemEntity.getMinPriceWanted());
        salesItemDto.setStatus(salesItemEntity.getStatus());
        salesItemDto.setWriter(salesItemEntity.getUser().getUsername());
        salesItemDto.setPassword(salesItemEntity.getPassword());
        return salesItemDto;
    }
}
