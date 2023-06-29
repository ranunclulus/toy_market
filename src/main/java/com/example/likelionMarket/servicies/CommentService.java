package com.example.likelionMarket.servicies;

import com.example.likelionMarket.dtos.CommentDto;
import com.example.likelionMarket.repositories.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.imageio.spi.ServiceRegistry;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final ServiceRegistry serviceRegistry;

    // 새로운 댓글 생성
    public void createComment(Long itemId, CommentDto commentDto) {

    }
}
