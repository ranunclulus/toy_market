package com.example.likelionMarket.servicies;

import com.example.likelionMarket.dtos.SalesItemDto;
import com.example.likelionMarket.entities.SalesItemEntity;
import com.example.likelionMarket.exceptions.badRequest.PasswordException;
import com.example.likelionMarket.exceptions.notFound.SalesItemExistException;
import com.example.likelionMarket.repositories.SalesItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SalesItemService {
    private final SalesItemRepository salesItemRepository;

    // createItem
    public void createItem(SalesItemDto salesItemDto) {
        SalesItemEntity newSalesItem = new SalesItemEntity();
        newSalesItem.setId(salesItemDto.getId());
        newSalesItem.setTitle(salesItemDto.getTitle());
        newSalesItem.setDescription(salesItemDto.getDescription());
        newSalesItem.setWriter(salesItemDto.getWriter());
        // 초기에 상태는 판매 중
        newSalesItem.setStatus("판매 중");
        newSalesItem.setPassword(salesItemDto.getPassword());
        newSalesItem.setMinPriceWanted(salesItemDto.getMinPriceWanted());
        // 초기에 이미지는 null
        newSalesItem.setImageUrl(null);
        salesItemRepository.save(newSalesItem);
    }

    // 페이징해서 아이템 읽어 오기
    public Page<SalesItemDto> readItemPages(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by("id"));
        Page<SalesItemEntity> salesItemEntityPage =
                salesItemRepository.findAll(pageable);

        Page<SalesItemDto> salesItemDtoPage =
                salesItemEntityPage.map(SalesItemDto::fromEntity);

        return salesItemDtoPage;
    }

    // 원하는 아이템만 하나 읽기
    public SalesItemDto readSalesItem(Long itemId) {
        Optional<SalesItemEntity> targetItem
                = salesItemRepository.findById(itemId);
        if (targetItem.isEmpty()) {
            throw new SalesItemExistException();
        }
        return SalesItemDto.fromEntity(targetItem.get());
    }

    // 아이템 수정하기
    public void updateSalesItem(Long itemId, SalesItemDto salesItemDto) {
        Optional<SalesItemEntity> targetOptionalItem
                = salesItemRepository.findById(itemId);
        // 수정하고자 하는 아이템이 존재하지 않을 때
        if(targetOptionalItem.isEmpty()) {
            throw new SalesItemExistException();
        }
        SalesItemEntity targetEntity = targetOptionalItem.get();

        // 수정하려는 상품의 비밀번호와 사용자가 보낸 비밀번호가 맞지 않다면 오류
        if(!targetEntity.getPassword().equals(salesItemDto.getPassword())) {
            throw new PasswordException();
        }

        // TODO if문 반복을 줄일 수 있을까?
        // id, password, image 는 수정되면 안 됨
        // 제목 수정값이 온다면 수정
        if(salesItemDto.getTitle() != null)
            targetEntity.setTitle(salesItemDto.getTitle());
        // 상태가 수정된다면 수정
        if(salesItemDto.getStatus() != null)
            targetEntity.setStatus(salesItemDto.getStatus());
        // 설명이 수정된다면 수정
        if(salesItemDto.getDescription() != null)
            targetEntity.setDescription(salesItemDto.getDescription());
        // 작성자가 수정된다면 수정
        if(salesItemDto.getWriter() != null)
            targetEntity.setWriter(salesItemDto.getWriter());
        // 판매가가 수정된다면 수정
        if(salesItemDto.getMinPriceWanted() != null)
            targetEntity.setMinPriceWanted(salesItemDto.getMinPriceWanted());
        SalesItemDto.fromEntity(salesItemRepository.save(targetEntity));
    }

    // 물품 삭제
    public void deleteSalesItem(Long itemId) {
        Optional<SalesItemEntity> targetItem
                = salesItemRepository.findById(itemId);
        if(targetItem.isEmpty())
            throw new SalesItemExistException();
        salesItemRepository.delete(targetItem.get());
    }

    // 이미지 업로드
    public void updateItemImage(Long itemId, MultipartFile multipartFile) throws IOException {
        Optional<SalesItemEntity> targetItem =
                salesItemRepository.findById(itemId);
        // 아이템이 없다면 오류
        if(targetItem.isEmpty())
            throw new SalesItemExistException();

        // 저장할 파일 경로 생성
        Files.createDirectories(Path.of("media/itemImages"));
        // 겹치지 않게 시간으로 파일 이름 작성
        LocalDateTime now = LocalDateTime.now();
        String imageUrl = String.format(
                "media/itemImages/%s.png", now.toString());
        Path uploadTo = Path.of(imageUrl);
        multipartFile.transferTo(uploadTo);
        // 객체의 이미지도 바꾸기
        SalesItemEntity salesItemEntity = targetItem.get();
        salesItemEntity.setImageUrl(imageUrl);
        salesItemRepository.save(salesItemEntity);
    }

}
