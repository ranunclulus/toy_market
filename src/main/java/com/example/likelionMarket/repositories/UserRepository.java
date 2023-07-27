package com.example.likelionMarket.repositories;

import com.example.likelionMarket.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
