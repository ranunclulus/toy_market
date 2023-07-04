package com.example.likelionMarket.repositories;

import com.example.likelionMarket.entities.SalesItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesItemRepository
        extends JpaRepository<SalesItemEntity, Long> {

}
