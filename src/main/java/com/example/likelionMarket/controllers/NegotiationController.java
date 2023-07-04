package com.example.likelionMarket.controllers;

import com.example.likelionMarket.dtos.NegotiationDto;
import com.example.likelionMarket.dtos.ResponseDto;
import com.example.likelionMarket.servicies.NegotiationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

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
        negotiationService.updateNegotiation(itemId, proposalId, negotiationDto);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage("제안이 수정되었습니다");
        return responseDto;
    }
}
