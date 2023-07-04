package com.example.likelionMarket.controllers;

import com.example.likelionMarket.dtos.NegotiationDto;
import com.example.likelionMarket.dtos.ResponseDto;
import com.example.likelionMarket.servicies.NegotiationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/items/{itemId}/proposal")
public class NegotiationController {
    private final NegotiationService negotiationService;

    // POST /items/{itemId}/proposal
    @PostMapping()
    public ResponseDto createProposal(
            @PathVariable("itemId") Long itemId,
            @RequestBody NegotiationDto negotiationDto
            ) {
        negotiationService.createNegotiation(itemId, negotiationDto);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage("구매 제안이 등록되었습니다");
        return responseDto;
    }
}
