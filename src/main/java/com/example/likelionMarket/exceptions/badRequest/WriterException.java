package com.example.likelionMarket.exceptions.badRequest;

public class WriterException extends Status404Exception{
    public WriterException() {
        super("작성자가 일치하지 않습니다");
    }
}
