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
    private List<CommentDto> comments;
    private List<NegotiationDto> negotiations;

    public static SalesItemDto fromEntity(SalesItemEntity salesItemEntity) {
        SalesItemDto salesItemDto = new SalesItemDto();
        salesItemDto.setId(salesItemEntity.getId());
        salesItemDto.setTitle(salesItemEntity.getTitle());
        salesItemDto.setDescription(salesItemEntity.getDescription());
        salesItemDto.setImageUrl(salesItemEntity.getImageUrl());
        salesItemDto.setMinPriceWanted(salesItemEntity.getMinPriceWanted());
        salesItemDto.setStatus(salesItemEntity.getStatus());
        salesItemDto.setWriter(salesItemEntity.getWriter());
        salesItemDto.setPassword(salesItemEntity.getPassword());
        for (CommentEntity comment: salesItemEntity.getComments()) {
            salesItemDto.getComments().add(CommentDto.fromEntity(comment));
        }
        for (NegotiationEntity negotiation: salesItemEntity.getNegotiations()) {
            salesItemDto.getNegotiations().add(NegotiationDto.fromEntity(negotiation));
        }
        return salesItemDto;
    }

    public static SalesItemEntity toEntity(SalesItemDto salesItemDto) {
        SalesItemEntity salesItemEntity = new SalesItemEntity();
        salesItemEntity.setId(salesItemDto.getId());
        salesItemEntity.setTitle(salesItemDto.getTitle());
        salesItemEntity.setDescription(salesItemDto.getDescription());
        salesItemEntity.setImageUrl(salesItemDto.getImageUrl());
        salesItemEntity.setMinPriceWanted(salesItemDto.getMinPriceWanted());
        salesItemEntity.setStatus(salesItemDto.getStatus());
        salesItemEntity.setWriter(salesItemDto.getWriter());
        salesItemEntity.setPassword(salesItemDto.getPassword());
        for (CommentDto comment: salesItemDto.getComments()) {
            salesItemEntity.getComments().add(CommentDto.toEntity(comment));
        }
        for (NegotiationDto negotiation: salesItemDto.getNegotiations()) {
            salesItemEntity.getNegotiations().add(NegotiationDto.toEntity(negotiation));
        }
        return salesItemEntity;
    }
}
