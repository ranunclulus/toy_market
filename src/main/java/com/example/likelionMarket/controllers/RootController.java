package com.example.likelionMarket.controllers;

import com.example.likelionMarket.dtos.CustomUserDetails;
import com.example.likelionMarket.dtos.ResponseDto;
import com.example.likelionMarket.entities.UserEntity;
import com.example.likelionMarket.exceptions.badRequest.PasswordException;
import com.example.likelionMarket.servicies.JpaUserDetailsManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequiredArgsConstructor
public class RootController {
    private final JpaUserDetailsManager manager;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseDto resister(@RequestParam("username") String username,
                                @RequestParam("password") String password,
                                @RequestParam("password-check") String passwordCheck) {
        ResponseDto responseDto = new ResponseDto();
        if (password.equals(passwordCheck)) {
            manager.createUser(CustomUserDetails.builder()
                    .username(username)
                    .password(passwordEncoder.encode(password))
                    .createAt(LocalDateTime.now())
                    .build());
            responseDto.setMessage("회원 가입이 완료되었습니다");
        }
        else responseDto.setMessage("비밀번호를 확인해 주세요");
        return responseDto;
    }
}
