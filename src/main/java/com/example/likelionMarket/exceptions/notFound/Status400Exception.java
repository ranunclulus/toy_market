package com.example.likelionMarket.exceptions.notFound;

public abstract class Status400Exception extends RuntimeException{
    public Status400Exception(String message) {
        super(message);
    }
}