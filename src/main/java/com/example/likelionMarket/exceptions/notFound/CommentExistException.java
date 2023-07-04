package com.example.likelionMarket.exceptions.notFound;

public class CommentExistException extends Status400Exception{
    public CommentExistException() {
        super("요청하신 댓글이 존재하지 않습니다");
    }
}
