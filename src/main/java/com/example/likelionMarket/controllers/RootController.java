package com.example.likelionMarket.controllers;

import com.example.likelionMarket.dtos.CustomUserDetails;
import com.example.likelionMarket.dtos.ResponseDto;
import com.example.likelionMarket.entities.UserEntity;
import com.example.likelionMarket.servicies.JpaUserDetailsManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequiredArgsConstructor
public class RootController {
    private final JpaUserDetailsManager manager;

    @PostMapping("/register")
    public ResponseDto resister(CustomUserDetails userDetails) {
        manager.createUser(CustomUserDetails.builder()
                .username(userDetails.getUsername())
                .password(userDetails.getPassword())
                .createAt(LocalDateTime.now())
                .build());
        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage("회원 가입이 완료되었습니다");
        return responseDto;
    }
}
