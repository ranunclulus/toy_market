package com.example.likelionMarket.servicies;

import com.example.likelionMarket.dtos.NegotiationDto;
import com.example.likelionMarket.dtos.ResponseDto;
import com.example.likelionMarket.entities.NegotiationEntity;
import com.example.likelionMarket.entities.SalesItemEntity;
import com.example.likelionMarket.entities.UserEntity;
import com.example.likelionMarket.exceptions.badRequest.PasswordException;
import com.example.likelionMarket.exceptions.badRequest.StatusException;
import com.example.likelionMarket.exceptions.badRequest.WriterException;
import com.example.likelionMarket.exceptions.notFound.NegotiationExistException;
import com.example.likelionMarket.exceptions.notFound.SalesItemExistException;
import com.example.likelionMarket.repositories.NegotiationRepository;
import com.example.likelionMarket.repositories.SalesItemRepository;
import com.example.likelionMarket.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class NegotiationService {
    private final NegotiationRepository negotiationRepository;
    private final SalesItemRepository salesItemRepository;
    private final UserRepository userRepository;
    private final JpaUserDetailsManager manager;

    // 제안 만들기
    public void createNegotiation(Long itemId, NegotiationDto negotiationDto) {
        if (!salesItemRepository.existsById(itemId)) {
            throw new SalesItemExistException();
        }
        NegotiationEntity negotiationEntity = new NegotiationEntity();
        negotiationEntity.setId(negotiationDto.getId());
        negotiationEntity.setUser(userRepository.findByUsername(negotiationDto.getWriter()).get());
        negotiationEntity.setItem(salesItemRepository.findById(itemId).get());
        negotiationEntity.setPassword(negotiationDto.getPassword());
        negotiationEntity.setSuggestedPrice(negotiationDto.getSuggestedPrice());
        negotiationEntity.setStatus("제안");
        negotiationRepository.save(negotiationEntity);
    }




    // 제안 수정하기
    public String updateNegotiation(Long itemId, Long proposalId, NegotiationDto negotiationDto) {
        String message = null;
        // 그런 아이템 없을 때
        if(!salesItemRepository.existsById(itemId)) {
            throw new SalesItemExistException();
        }

        Optional<NegotiationEntity> negotiationEntityOptional =
                negotiationRepository.findById(proposalId);
        // 그런 제안 없을 때
        if (negotiationEntityOptional.isEmpty()) {
            throw new NegotiationExistException();
        }

        NegotiationEntity negotiationEntity = negotiationEntityOptional.get();

        // 아이템 작성자가 수락 혹은 거절을 할 때
        if(negotiationDto.getWriter().equals(salesItemRepository.findById(itemId).get().getUser().getUsername())) {

        }
        if(!negotiationEntity.getUser().equals(negotiationDto.getWriter())) {
            throw new WriterException();
        }

        if(!negotiationEntity.getPassword().equals(negotiationDto.getPassword())) {
            throw new PasswordException();
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
                    throw new StatusException();
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
            throw new SalesItemExistException();
        }

        Optional<NegotiationEntity> negotiationEntityOptional =
                negotiationRepository.findById(proposalId);

        if (negotiationEntityOptional.isEmpty()) {
            throw new NegotiationExistException();
        }
        negotiationRepository.delete(negotiationEntityOptional.get());
    }

    public Page<NegotiationDto> readNegotiation(
            String writer,
            String password,
            Integer page,
            Long itemId) {
        if(!salesItemRepository.existsById(itemId))
            throw new SalesItemExistException();
        Pageable pageable = PageRequest.of(
                page,
                25,
                Sort.by("id"));

        Page<NegotiationEntity> negotiationEntityPage =
                negotiationRepository.findAllByUser_UsernameAndPassword(writer, password, pageable);

        Page<NegotiationDto> negotiationDtoPage = negotiationEntityPage.map(NegotiationDto::fromEntity);

        return negotiationDtoPage;
    }

    // 글 작성자가 거래를 수락 혹은 거절
    public ResponseDto updateStatus(Long itemId, Long proposalId, NegotiationDto negotiationDto) {
        // 그런 아이템 없을 때
        if(!salesItemRepository.existsById(itemId)) {
            throw new SalesItemExistException();
        }

        Optional<NegotiationEntity> negotiationEntityOptional =
                negotiationRepository.findById(proposalId);
        // 그런 제안 없을 때
        if (negotiationEntityOptional.isEmpty()) {
            throw new NegotiationExistException();
        }

        NegotiationEntity negotiationEntity = negotiationEntityOptional.get();

        // 아이템 작성자 다를 때
        if(!negotiationDto.getWriter().equals(salesItemRepository.findById(itemId).get().getUser().getUsername())) {
            throw new WriterException();
        }

        // 비밀번호가 다를 때
        if(!negotiationDto.getPassword().equals(salesItemRepository.findById(itemId).get().getPassword())) {
            throw new PasswordException();
        }

        negotiationEntity.setStatus(negotiationDto.getStatus());
        negotiationRepository.save(negotiationEntity);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage("제안의 상태가 변경되었습니다");
        return responseDto;
    }

    public ResponseDto successStatus(Long itemId, Long proposalId, NegotiationDto negotiationDto) {
        // 그런 아이템 없을 때
        if(!salesItemRepository.existsById(itemId)) {
            throw new SalesItemExistException();
        }

        Optional<NegotiationEntity> negotiationEntityOptional =
                negotiationRepository.findById(proposalId);

        // 그런 제안 없을 때
        if (negotiationEntityOptional.isEmpty()) {
            throw new NegotiationExistException();
        }

        NegotiationEntity negotiationEntity = negotiationEntityOptional.get();

        // 수락 상태가 아닐 때
        if (!negotiationEntity.getStatus().equals("수락"))
            throw new StatusException();

        // 제안 작성자 다를 때
        if(!negotiationDto.getWriter().equals(negotiationEntity.getUser().getUsername())) {
            throw new WriterException();
        }

        // 비밀번호가 다를 때
        if(!negotiationDto.getPassword().equals(negotiationEntity.getPassword())) {
            throw new PasswordException();
        }

        negotiationEntity.setStatus(negotiationDto.getStatus());
        negotiationRepository.save(negotiationEntity);

        // 다른 구매 협상 모두 거절
        NegotiationEntity[] otherNegotiations = negotiationRepository.findAllByItemId(itemId);
        if(otherNegotiations.length != 0) {
            for (NegotiationEntity negotiation:otherNegotiations
                 ) {
                // 내가 확정한 데이터일 때 패스
                if(negotiation.equals(negotiationEntity)) continue;
                // 아니라면 거절로 바꾸기
                negotiation.setStatus("거절");
                negotiationRepository.save(negotiation);
            }
        }
        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage("구매가 확정되었습니다");
        return responseDto;
    }

    // 가격이 변동될 떄
    public ResponseDto updateSuggestedPrice(Long itemId, Long proposalId, NegotiationDto negotiationDto) {
        // 그런 아이템 없을 때
        if(!salesItemRepository.existsById(itemId)) {
            throw new SalesItemExistException();
        }

        Optional<NegotiationEntity> negotiationEntityOptional =
                negotiationRepository.findById(proposalId);

        // 그런 제안 없을 때
        if (negotiationEntityOptional.isEmpty()) {
            throw new NegotiationExistException();
        }

        NegotiationEntity negotiationEntity = negotiationEntityOptional.get();

        // 제안 작성자 다를 때
        if(!negotiationDto.getWriter().equals(negotiationEntity.getUser().getUsername())) {
            throw new WriterException();
        }

        // 비밀번호가 다를 때
        if(!negotiationDto.getPassword().equals(negotiationEntity.getPassword())) {
            throw new PasswordException();
        }

        negotiationEntity.setSuggestedPrice(negotiationDto.getSuggestedPrice());
        negotiationRepository.save(negotiationEntity);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage("제안이 수정되었습니다");
        return responseDto;
    }
}
