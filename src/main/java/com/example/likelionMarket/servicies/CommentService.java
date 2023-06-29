package com.example.likelionMarket.servicies;

import com.example.likelionMarket.dtos.CommentDto;
import com.example.likelionMarket.entities.CommentEntity;
import com.example.likelionMarket.repositories.CommentRepository;
import com.example.likelionMarket.repositories.SalesItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final SalesItemRepository salesItemRepository;

    // 새로운 댓글 생성
    public void createComment(Long itemId, CommentDto commentDto) {
        // 댓글을 달고자 하는 물품이 존재하지 않을 때
        if(!salesItemRepository.existsById(itemId)) {
            throw new RuntimeException("물품이 없어서 댓글을 못 달음");
        }
        // 댓글을 달고자 하는 물품이 존재할 때
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setId(commentDto.getId());
        commentEntity.setWriter(commentDto.getWriter());
        commentEntity.setReply(commentDto.getReply());
        commentEntity.setContent(commentDto.getContent());
        commentEntity.setPassword(commentDto.getPassword());
        commentEntity.setItemId(itemId);
        commentRepository.save(commentEntity);
    }
}
