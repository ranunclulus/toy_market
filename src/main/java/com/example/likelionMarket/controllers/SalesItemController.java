package com.example.likelionMarket.controllers;

import com.example.likelionMarket.dtos.ResponseDto;
import com.example.likelionMarket.dtos.SalesItemDto;
import com.example.likelionMarket.entities.SalesItemEntity;
import com.example.likelionMarket.servicies.SalesItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;

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

    // GET /items?page={page}&limit={limit}
    @GetMapping
    public Page<SalesItemDto> readAll(
            @RequestParam("page") Integer page,
            @RequestParam("size") Integer size
    ) {
        return salesItemService.readItemPages(page, size);
    }

    // GET /items/{itemId}
    @GetMapping("/{itemId}")
    public SalesItemDto readItem(
            @PathVariable("itemId") Long itemId
    ) {
        return salesItemService.readSalesItem(itemId);
    }

    // PUT /items/{itemId}
    @PutMapping("/{itemId}")
    public ResponseDto updateItem(
            @PathVariable("itemId") Long itemId,
            @RequestBody SalesItemDto salesItemDto
    ) {
        salesItemService.updateSalesItem(itemId, salesItemDto);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage("물품이 수정되었습니다");
        return responseDto;
    }

    // PUT /items/{itemId}/image
    @PutMapping(value = "/{itemId}/image",
    consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseDto updateItemImage(
            @PathVariable("itemId") Long itemId,
            @RequestParam("photo") MultipartFile multipartFile
            ) throws IOException {
        salesItemService.updateItemImage(itemId, multipartFile);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage("이미지가 등록되었습니다");
        return responseDto;
    }

    // DELETE /items/{itemId}
    @DeleteMapping("/{itemId}")
    public ResponseDto deleteItem(
            @PathVariable("itemId") Long itemId
    ) {
        salesItemService.deleteSalesItem(itemId);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage("물품을 삭제했습니다");
        return responseDto;
    }
}
