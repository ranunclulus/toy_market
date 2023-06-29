package com.example.likelionMarket.controllers;

import com.example.likelionMarket.dtos.CommentDto;
import com.example.likelionMarket.dtos.ResponseDto;
import com.example.likelionMarket.servicies.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/items/{itemId}/comments")
public class CommentController {
    private final CommentService commentService;

    // POST /items/{itemId}/comments
    @PostMapping()
    public ResponseDto createComment(
            @PathVariable("itemId") Long itemId,
            @RequestBody CommentDto commentDto
            ) {
        commentService.createComment(itemId, commentDto);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage("댓글이 등록되었습니다");
        return responseDto;
    }
}
