package com.example.likelionMarket.servicies;

import com.example.likelionMarket.repositories.salesItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class salesItemService {
    private final salesItemRepository salesItemRepository;
}
