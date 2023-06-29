package com.example.likelionMarket.dtos;

import lombok.Data;
import com.example.likelionMarket.entities.salesItemEntity;

@Data
public class salesItemDto {

    private String title;
    private String description;
    private String image_url;
    private Integer min_price_wanted;
    private String status;
    private String writer;

    public salesItemDto toEntity(salesItemEntity salesItemEntity) {
        salesItemDto salesItemDto = new salesItemDto();
        salesItemDto.setTitle(salesItemEntity.getTitle());
        salesItemDto.setDescription(salesItemEntity.getDescription());
        salesItemDto.setImage_url(salesItemEntity.getImage_url());
        salesItemDto.setMin_price_wanted(salesItemEntity.getMin_price_wanted());
        salesItemDto.setStatus(salesItemEntity.getStatus());
        salesItemDto.setWriter(salesItemEntity.getWriter());
        return salesItemDto;
    }
}
