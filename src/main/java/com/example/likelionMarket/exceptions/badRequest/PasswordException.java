package com.example.likelionMarket.exceptions.badRequest;

public class PasswordException extends Status404Exception{
    public PasswordException() {
        super("비밀번호가 일치하지 않습니다");
    }
}
