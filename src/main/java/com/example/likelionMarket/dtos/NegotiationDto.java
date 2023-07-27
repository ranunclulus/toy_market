package com.example.likelionMarket.dtos;

import com.example.likelionMarket.entities.NegotiationEntity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class NegotiationDto {
    private Long id;
    private SalesItemDto item;
    private Long suggestedPrice;
    private String status;
    private String writer;
    private String password;
    public static NegotiationDto fromEntity(NegotiationEntity negotiationEntity) {
        NegotiationDto negotiationDto = new NegotiationDto();
        negotiationDto.setId(negotiationEntity.getId());
        negotiationDto.setItem(SalesItemDto.fromEntity(negotiationEntity.getItem()));
        negotiationDto.setSuggestedPrice(negotiationEntity.getSuggestedPrice());
        negotiationDto.setStatus(negotiationEntity.getStatus());
        negotiationDto.setWriter(negotiationEntity.getWriter());
        negotiationDto.setPassword(negotiationDto.getPassword());
        return negotiationDto;
    }

    public static NegotiationEntity toEntity(NegotiationDto negotiationDto) {
        NegotiationEntity negotiationEntity = new NegotiationEntity();
        negotiationEntity.setId(negotiationDto.getId());
        negotiationEntity.setItem(SalesItemDto.toEntity(negotiationDto.getItem()));
        negotiationEntity.setSuggestedPrice(negotiationDto.getSuggestedPrice());
        negotiationEntity.setStatus(negotiationDto.getStatus());
        negotiationEntity.setWriter(negotiationDto.getWriter());
        negotiationEntity.setPassword(negotiationDto.getPassword());
        return negotiationEntity;
    }
}
