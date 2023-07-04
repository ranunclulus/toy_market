package com.example.likelionMarket.servicies;

import com.example.likelionMarket.dtos.NegotiationDto;
import com.example.likelionMarket.dtos.ResponseDto;
import com.example.likelionMarket.entities.NegotiationEntity;
import com.example.likelionMarket.repositories.NegotiationRepository;
import com.example.likelionMarket.repositories.SalesItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public List<NegotiationEntity> readNegotiation(
            String writer,
            String password,
            Integer page,
            Long itemId) {
        List<NegotiationEntity> list = negotiationRepository.findAllByItemId(itemId);
        return list;
    }

    // 제안 수정하기
    public String updateNegotiation(Long itemId, Long proposalId, NegotiationDto negotiationDto) {
        String message = null;
        if(!salesItemRepository.existsById(itemId)) {
            throw new RuntimeException("이런 아이템 없어서 제안 수정 불가능");
        }

        Optional<NegotiationEntity> negotiationEntityOptional =
                negotiationRepository.findById(proposalId);

        if (negotiationEntityOptional.isEmpty()) {
            throw new RuntimeException("이런 제안 없어서 제안 수정 불가능");
        }

        NegotiationEntity negotiationEntity = negotiationEntityOptional.get();

        if(!negotiationEntity.getWriter().equals(negotiationDto.getWriter())) {
            throw new RuntimeException("작성자가 달라서 제안 수정 불가");
        }

        if(!negotiationEntity.getPassword().equals(negotiationDto.getPassword())) {
            throw new RuntimeException("비밀번호가 달라서 제안 수정 불가능");
        }
        // 제안하는 가격을 수정할 때
        if(negotiationDto.getSuggestedPrice() != null)
            negotiationEntity.setSuggestedPrice(negotiationDto.getSuggestedPrice());

        // 제안의 상태를 수정할 때
        if (negotiationDto.getStatus() != null) {
            String changeStatus = negotiationDto.getStatus(); // 바꾸려는 상태
            if (changeStatus.equals("확정")) {
                if(negotiationEntity.getStatus().equals("수락"))
                    message = "구매가 확정되었습니다";
                else
                    throw new RuntimeException("수락 상태일 때만 거래를 확정할 수 있음");
            }
            else {
                message = "제안의 상태가 변경되었습니다";
            }
            negotiationEntity.setStatus(changeStatus);
            negotiationRepository.save(negotiationEntity);
        }
        return message;
    }

    // 제안 삭제하기
    public void deleteNegotiation(Long itemId, Long proposalId) {
        if(!salesItemRepository.existsById(itemId)) {
            throw new RuntimeException("이런 아이템 없어서 제안 삭제 불가능");
        }

        Optional<NegotiationEntity> negotiationEntityOptional =
                negotiationRepository.findById(proposalId);

        if (negotiationEntityOptional.isEmpty()) {
            throw new RuntimeException("이런 제안 없어서 제안 삭제 불가능");
        }
        negotiationRepository.delete(negotiationEntityOptional.get());
    }
}
