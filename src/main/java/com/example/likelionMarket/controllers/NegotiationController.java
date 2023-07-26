package com.example.likelionMarket.controllers;

import com.example.likelionMarket.dtos.NegotiationDto;
import com.example.likelionMarket.dtos.ResponseDto;
import com.example.likelionMarket.entities.NegotiationEntity;
import com.example.likelionMarket.servicies.NegotiationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/items/{itemId}/proposals")
public class NegotiationController {
    private final NegotiationService negotiationService;

    // POST /items/{itemId}/proposal
    @PostMapping()
    public ResponseDto createProposal(
            @PathVariable("itemId") Long itemId,
            @RequestBody NegotiationDto negotiationDto
            ) {
        log.info(itemId.toString());
        negotiationService.createNegotiation(itemId, negotiationDto);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage("구매 제안이 등록되었습니다");
        return responseDto;
    }

    // GET /items/{itemId}/proposals?writer=jeeho.edu&password=qwerty1234&page=1
    @GetMapping()
    public Page<NegotiationDto> readProposal(
            @RequestParam("writer") String writer,
            @RequestParam("password") String password,
            @RequestParam("page") Integer page,
            @PathVariable("itemId") Long itemId
    ) {
        return negotiationService.readNegotiation(writer, password, page, itemId);
    }

    // PUT /items/{itemId}/proposals/{proposalId}
    @PutMapping("/{proposalId}")
    public ResponseDto updateProposal(
            @PathVariable("itemId") Long itemId,
            @PathVariable("proposalId") Long proposalId,
            @RequestBody NegotiationDto negotiationDto
    ) {
        String newStatus = negotiationDto.getStatus();
        Long newSuggestedPrice = negotiationDto.getSuggestedPrice();
        // 금액 제안인 경우
        if (newStatus == null) {
            return negotiationService.updateSuggestedPrice(itemId, proposalId, negotiationDto);
        }
        // 상태 변경인 경우
        if (newStatus.equals("수락") || newStatus.equals("거절")) {
            return negotiationService.updateStatus(itemId, proposalId, negotiationDto);
        }
        // 거래 확정인 경우
        return negotiationService.successStatus(itemId, proposalId, negotiationDto);
    }

    // DELETE /items/{itemId}/proposals/{proposalId}
    @DeleteMapping("/{proposalId}")
    public ResponseDto deleteProposal(
            @PathVariable("itemId") Long itemId,
            @PathVariable("proposalId") Long proposalId
    ) {
        negotiationService.deleteNegotiation(itemId, proposalId);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage("제안이 삭제되었습니다");
        return responseDto;
    }
}
