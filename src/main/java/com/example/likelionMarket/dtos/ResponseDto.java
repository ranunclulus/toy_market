package com.example.likelionMarket.dtos;

import lombok.Data;

public class ResponseDto {
    private String messgae;

    public ResponseDto(String messgae) {
        this.messgae = messgae;
    }
}
