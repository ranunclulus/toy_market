package com.example.likelionMarket.controllers;

import com.example.likelionMarket.dtos.ResponseDto;
import com.example.likelionMarket.dtos.SalesItemDto;
import com.example.likelionMarket.entities.SalesItemEntity;
import com.example.likelionMarket.servicies.SalesItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
public class SalesItemController {
    private final SalesItemService salesItemService;

    // POST /items
    // 중고 아이템 업로드
    @PostMapping()
    public ResponseDto createSalesItem(
            @RequestBody SalesItemDto salesItemDto
            ) {
        salesItemService.createItem(salesItemDto);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage("등록을 완료했습니다");
        return responseDto;
    }
}
