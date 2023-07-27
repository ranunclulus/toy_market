package com.example.likelionMarket.controllers;

import com.example.likelionMarket.dtos.CustomUserDetails;
import com.example.likelionMarket.dtos.JwtRequestDto;
import com.example.likelionMarket.dtos.JwtTokenDto;
import com.example.likelionMarket.dtos.ResponseDto;
import com.example.likelionMarket.servicies.JpaUserDetailsManager;
import com.example.likelionMarket.utils.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequiredArgsConstructor
public class RootController {
    private final JpaUserDetailsManager manager;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtils jwtTokenUtils;

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

    @PostMapping("/login")
    public JwtTokenDto issueJwt(@RequestBody JwtRequestDto dto) {
        CustomUserDetails customUserDetails
                = manager.loadUserByUsername(dto.getUsername());

        if(!passwordEncoder.matches(dto.getPassword(), customUserDetails.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }


        JwtTokenDto jwtTokenDto = new JwtTokenDto();
        jwtTokenDto.setToken(jwtTokenUtils.generateToken(customUserDetails.getUsername()));
        return jwtTokenDto;
    }
}
