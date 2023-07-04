package com.example.likelionMarket.servicies;

import com.example.likelionMarket.dtos.NegotiationDto;
import com.example.likelionMarket.entities.NegotiationEntity;
import com.example.likelionMarket.repositories.NegotiationRepository;
import com.example.likelionMarket.repositories.SalesItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class NegotiationService {
    private final NegotiationRepository negotiationRepository;
    private final SalesItemRepository salesItemRepository;

    // 제안 만들기
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


    // TODO
    public Page<NegotiationDto> readNegotiation(
            String writer,
            String password,
            Integer page,
            Long itemId) {
        if(!salesItemRepository.existsById(itemId))
            throw new RuntimeException("이런 아이템이 없어서 보여 주기 불가능");

        if(negotiationRepository.findAllByWriter(writer).isEmpty())
            throw new RuntimeException("이런 작성자가 제시한 제안 없음");

        if(!salesItemRepository.findById(itemId).get().getPassword().equals(password))
            throw new RuntimeException("비밀번호가 달라서 제안을 보여 줄 수 없음");

        Pageable pageable = PageRequest.of(
                page,
                25,
                Sort.by("id"));
        Page<NegotiationEntity> negotiationEntityPage =
                negotiationRepository.findAll(pageable);

        Page<NegotiationDto> negotiationDtoPage =
                negotiationEntityPage.map(NegotiationDto::fromEntity);
        return negotiationDtoPage;
    }
}
