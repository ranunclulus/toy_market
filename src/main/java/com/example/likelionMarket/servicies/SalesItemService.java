package com.example.likelionMarket.servicies;

import com.example.likelionMarket.dtos.SalesItemDto;
import com.example.likelionMarket.entities.SalesItemEntity;
import com.example.likelionMarket.repositories.SalesItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SalesItemService {
    private final SalesItemRepository salesItemRepository;

    // createItem
    public SalesItemDto createItem(SalesItemDto salesItemDto) {
        SalesItemEntity newSalesItem = new SalesItemEntity();
        newSalesItem.setId(salesItemDto.getId());
        newSalesItem.setTitle(salesItemDto.getTitle());
        newSalesItem.setDescription(salesItemDto.getDescription());
        newSalesItem.setWriter(salesItemDto.getWriter());
        newSalesItem.setStatus(salesItemDto.getStatus());
        newSalesItem.setPassword(salesItemDto.getPassword());
        newSalesItem.setMinPriceWanted(salesItemDto.getMinPriceWanted());
        newSalesItem.setImage_url(salesItemDto.getImage_url());
        return SalesItemDto.fromEntity(
                salesItemRepository.save(newSalesItem));
    }

    public Page<SalesItemDto> readItemPages(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by("id").descending());
        Page<SalesItemEntity> salesItemEntityPage =
                salesItemRepository.findAll(pageable);

        Page<SalesItemDto> salesItemDtoPage =
                salesItemEntityPage.map(SalesItemDto::fromEntity);

        return salesItemDtoPage;
    }
}
