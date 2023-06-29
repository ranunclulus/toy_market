package com.example.likelionMarket.dtos;

import lombok.Data;
import com.example.likelionMarket.entities.SalesItemEntity;

@Data
public class SalesItemDto {

    private Long id;
    private String title;
    private String description;
    private String image_url;
    private Integer minPriceWanted;
    private String status;
    private String writer;
    private String password;

    public static SalesItemDto fromEntity(SalesItemEntity salesItemEntity) {
        SalesItemDto salesItemDto = new SalesItemDto();
        salesItemDto.setId(salesItemEntity.getId());
        salesItemDto.setTitle(salesItemEntity.getTitle());
        salesItemDto.setDescription(salesItemEntity.getDescription());
        salesItemDto.setImage_url(salesItemEntity.getImage_url());
        salesItemDto.setMinPriceWanted(salesItemEntity.getMinPriceWanted());
        salesItemDto.setStatus(salesItemEntity.getStatus());
        salesItemDto.setWriter(salesItemEntity.getWriter());
        salesItemDto.setPassword(salesItemEntity.getPassword());
        return salesItemDto;
    }
}
