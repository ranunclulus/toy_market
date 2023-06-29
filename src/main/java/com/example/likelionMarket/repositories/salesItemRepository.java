package com.example.likelionMarket.repositories;

import com.example.likelionMarket.entities.salesItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface salesItemRepository
        extends JpaRepository<salesItemEntity, Long> {
}
