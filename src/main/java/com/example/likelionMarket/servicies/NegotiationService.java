package com.example.likelionMarket.servicies;

import com.example.likelionMarket.dtos.NegotiationDto;
import com.example.likelionMarket.entities.NegotiationEntity;
import com.example.likelionMarket.repositories.NegotiationRepository;
import com.example.likelionMarket.repositories.SalesItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class NegotiationService {
    private final NegotiationRepository negotiationRepository;
    private final SalesItemRepository salesItemRepository;

    public void createNegotiation(Long itemId, NegotiationDto negotiationDto) {
        if (!salesItemRepository.existsById(itemId)) {
            throw new RuntimeException("제안을 등록할 아이템이 없음");
        }
        NegotiationEntity negotiationEntity = new NegotiationEntity();
        negotiationEntity.setId(negotiationDto.getId());
        negotiationEntity.setWriter(negotiationDto.getWriter());
        negotiationEntity.setItemId(itemId);
        negotiationEntity.setPassword(negotiationDto.getPassword());
        negotiationEntity.setSuggestedPrice(negotiationDto.getSuggestedPrice());
        negotiationEntity.setStatus("제안");
        negotiationRepository.save(negotiationEntity);
    }
}
