package com.example.likelionMarket.exceptions.badRequest;

public class StatusException extends Status404Exception{
    public StatusException() {
        super("수락 상태일 때만 거래를 확정지을 수 있습니다");
    }
}
