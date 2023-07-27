package com.example.likelionMarket.dtos;

import lombok.Data;

@Data
public class JwtRequestDto {
    private String username;
    private String password;
}
