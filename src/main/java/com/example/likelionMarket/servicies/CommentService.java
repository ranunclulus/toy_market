package com.example.likelionMarket.servicies;

import com.example.likelionMarket.dtos.CommentDto;
import com.example.likelionMarket.dtos.ResponseDto;
import com.example.likelionMarket.entities.CommentEntity;
import com.example.likelionMarket.repositories.CommentRepository;
import com.example.likelionMarket.repositories.SalesItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;


@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final SalesItemRepository salesItemRepository;
    private final SalesItemService salesItemService;


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
        commentEntity.setReply(null);
        commentEntity.setContent(commentDto.getContent());
        commentEntity.setPassword(commentDto.getPassword());
        commentEntity.setItemId(itemId);
        commentRepository.save(commentEntity);
    }

    // 페이징해서 보여 주기
    // size: 25 page: 0
    public Page<CommentDto> readCommentPages(Long itemId) {
        // 댓글을 달고자 하는 물품이 존재하지 않을 때
        if(!salesItemRepository.existsById(itemId)) {
            throw new RuntimeException("물품이 없어서 댓글을 못 달음");
        }

        Pageable pageable = PageRequest.of(
                0, 25, Sort.by("id"));
        Page<CommentEntity> commentEntitiyPage =
                commentRepository.findAll(pageable);

        Page<CommentDto> commentDtoPage =
                commentEntitiyPage.map(CommentDto::fromEntity);
        return commentDtoPage;
    }

    // 댓글 삭제하기
    public void deleteComment(Long itemId, Long commentId, CommentDto commentDto) {
        // 댓글을 달고자 하는 물품이 존재하지 않을 때
        if(!salesItemRepository.existsById(itemId)) {
            throw new RuntimeException("물품이 없어서 댓글을 못 삭제");
        }
        Optional<CommentEntity> commentEntityOptional =
                commentRepository.findById(commentId);
        if(commentEntityOptional.isEmpty())
            throw new RuntimeException("그런 댓글 없어서 삭제 못 함");
        if(!commentDto.getPassword().equals(commentEntityOptional.get().getPassword() ))
            throw new RuntimeException("비밀번호가 달라서 삭제 못 함");
        commentRepository.delete(commentEntityOptional.get());
    }

    // 댓글 업데이트하기
    public void updateComment(Long itemId, Long commentId, CommentDto commentDto) {
        // 댓글을 달고자 하는 물품이 존재하지 않을 때
        if(!salesItemRepository.existsById(itemId)) {
            throw new RuntimeException("물품이 없어서 댓글 수정 불가");
        }
        Optional<CommentEntity> commentEntityOptional =
                commentRepository.findById(commentId);
        if(commentEntityOptional.isEmpty())
            throw new RuntimeException("그런 댓글 없어서 수정 불가");
        if(!commentDto.getPassword().equals(commentEntityOptional.get().getPassword() ))
            throw new RuntimeException("비밀번호가 달라서 수정 불가");
        CommentEntity targetEntity = commentEntityOptional.get();

        // writer, content만 수정 가능
        if(commentDto.getWriter() != null)
            targetEntity.setWriter(commentDto.getWriter());
        if(commentDto.getContent() != null)
            targetEntity.setContent(commentDto.getContent());
        commentRepository.save(targetEntity);
    }

    // 답글 추가하기
    public void updateReply(Long itemId, Long commentId, CommentDto commentDto) {
        // 댓글을 달고자 하는 물품이 존재하지 않을 때
        if(!salesItemRepository.existsById(itemId)) {
            throw new RuntimeException("물품이 없어서 댓글 답글 불가");
        }
        Optional<CommentEntity> commentEntityOptional =
                commentRepository.findById(commentId);
        if(commentEntityOptional.isEmpty())
            throw new RuntimeException("그런 댓글 없어서 답글 불가");
        if(!commentDto.getWriter().equals(salesItemRepository.findById(itemId).get().getWriter()))
            throw new RuntimeException("작성자 달라서 답글 불가");
        if(!commentDto.getPassword().equals(salesItemRepository.findById(itemId).get().getPassword()))
            throw new RuntimeException("비밀번호가 달라서 답글 불가");
        CommentEntity targetEntity = commentEntityOptional.get();
        targetEntity.setReply(commentDto.getReply());
        commentRepository.save(targetEntity);
    }
}
