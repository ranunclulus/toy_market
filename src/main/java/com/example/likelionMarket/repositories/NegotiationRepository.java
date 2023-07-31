package com.example.likelionMarket.repositories;


import com.example.likelionMarket.entities.NegotiationEntity;
import com.example.likelionMarket.entities.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.parameters.P;

import java.util.List;

public interface NegotiationRepository
        extends JpaRepository<NegotiationEntity, Long> {
    Page<NegotiationEntity> findAllByUser_UsernameAndPassword(String writer, String password, Pageable pageable);
    NegotiationEntity[] findAllByItemId(Long itemId);

}
