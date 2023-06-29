package com.example.likelionMarket.repositories;


import com.example.likelionMarket.entities.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository
        extends JpaRepository<CommentEntity, Long> {
}
