package com.example.likelionMarket.dtos;

import com.example.likelionMarket.entities.CommentEntity;
import jakarta.persistence.Column;
import lombok.Data;

@Data
public class CommentDto {
    private Long id;
    private SalesItemDto item;
    private String writer;
    private String password;
    private String content;
    private String reply;

    public static CommentDto fromEntity(CommentEntity commentEntity) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(commentEntity.getId());
        commentDto.setItem(SalesItemDto.fromEntity(commentEntity.getItem()));
        commentDto.setWriter(commentEntity.getWriter());
        commentDto.setPassword(commentEntity.getPassword());
        commentDto.setContent(commentEntity.getContent());
        commentDto.setReply(commentEntity.getReply());
        return commentDto;
    }

    public static CommentEntity toEntity(CommentDto commentDto) {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setId(commentDto.getId());
        commentEntity.setItem(SalesItemDto.toEntity(commentDto.getItem()));
        commentEntity.setWriter(commentDto.getWriter());
        commentEntity.setPassword(commentDto.getPassword());
        commentEntity.setContent(commentDto.getContent());
        commentEntity.setReply(commentDto.getReply());
        return commentEntity;
    }
}
