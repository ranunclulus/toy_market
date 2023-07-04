package com.example.likelionMarket.repositories;


import com.example.likelionMarket.entities.NegotiationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NegotiationRepository
        extends JpaRepository<NegotiationEntity, Long> {
    public List<NegotiationEntity> findAllByItemId(Long writer);

}
