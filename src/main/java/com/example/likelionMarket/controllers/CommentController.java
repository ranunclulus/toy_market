package com.example.likelionMarket.controllers;

import com.example.likelionMarket.servicies.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/items/{itemId}/comments")
public class CommentController {
    private final CommentService commentService;
}
