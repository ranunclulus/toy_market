package com.example.likelionMarket.exceptions.notFound;

public class NegotiationExistException extends Status400Exception{
    public NegotiationExistException() {
        super("요청하신 제안이 존재하지 않습니다");
    }
}
