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
    private Long itemId;
    private Long suggestedPrice;
    private String status;
    private String writer;
    private String password;
    public NegotiationDto fromEntity(NegotiationEntity negotiationEntity) {
        NegotiationDto negotiationDto = new NegotiationDto();
        negotiationDto.setId(negotiationEntity.getId());
        negotiationDto.setItemId(negotiationEntity.getItemId());
        negotiationDto.setSuggestedPrice(negotiationEntity.getSuggestedPrice());
        negotiationDto.setStatus(negotiationEntity.getStatus());
        negotiationDto.setWriter(negotiationEntity.getWriter());
        negotiationDto.setPassword(negotiationDto.getPassword());
        return negotiationDto;
    }
}
