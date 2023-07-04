package com.example.likelionMarket.controllers;

import com.example.likelionMarket.dtos.CommentDto;
import com.example.likelionMarket.dtos.ResponseDto;
import com.example.likelionMarket.servicies.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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

    // GET /items/{itemId}/comments
    @GetMapping
    public Page<CommentDto> readAll(
            @PathVariable("itemId") Long itemId
    ) {
        return commentService.readCommentPages(itemId);
    }

    // PUT /items/{itemId}/comments/{commentId}
    @PutMapping("/{commentId}")
    public ResponseDto updateComment(
            @PathVariable("itemId") Long itemId,
            @PathVariable("commentId") Long commentId,
            @RequestBody CommentDto commentDto
    ) {
        commentService.updateComment(itemId, commentId, commentDto);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage("댓글이 수정되었습니다");
        return responseDto;
    }

    // DELETE /items/{itemId}/comments/{commentId}
    @DeleteMapping("/{commentId}")
    public ResponseDto deleteComment(
            @PathVariable("itemId") Long itemId,
            @PathVariable("commentId") Long commentId,
            @RequestBody CommentDto commentDto
    ) {
        commentService.deleteComment(itemId, commentId, commentDto);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage("댓글을 삭제했습니다");
        return responseDto;
    }
}
