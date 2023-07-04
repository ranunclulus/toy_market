package com.example.likelionMarket.exceptions.notFound;

public class SalesItemExistException extends Status400Exception{

    public SalesItemExistException() {
        super("중고 거래 상품이 존재하지 않습니다");
    }
}
