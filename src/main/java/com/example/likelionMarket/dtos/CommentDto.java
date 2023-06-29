package com.example.likelionMarket.dtos;

import com.example.likelionMarket.entities.CommentEntity;
import jakarta.persistence.Column;
import lombok.Data;

@Data
public class CommentDto {
    private Long id;
    private Long itemId;
    private String writer;
    private String password;
    private String content;
    private String reply;

    public CommentDto fromEntity(CommentEntity commentEntity) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(commentEntity.getId());
        commentDto.setItemId(commentEntity.getItemId());
        commentDto.setWriter(commentEntity.getWriter());
        commentDto.setPassword(commentEntity.getPassword());
        commentDto.setContent(commentEntity.getContent());
        commentDto.setReply(commentEntity.getReply());
        return commentDto;
    }
}
