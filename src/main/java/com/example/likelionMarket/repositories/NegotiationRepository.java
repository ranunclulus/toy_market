package com.example.likelionMarket.repositories;


import com.example.likelionMarket.entities.NegotiationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NegotiationRepository
        extends JpaRepository<NegotiationEntity, Long> {
}
