package com.example.likelionMarket.controllers;

import com.example.likelionMarket.dtos.salesItemDto;
import com.example.likelionMarket.entities.salesItemEntity;
import com.example.likelionMarket.servicies.salesItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
public class salesItemController {
    private final salesItemService salesItemService;

    // POST /items
    // 중고 아이템 업로드
    @PostMapping()
    public salesItemDto createSalesItem(
            @RequestBody salesItemEntity salesItemEntity
            ) {

    }
}
