package com.raincloud.sunlightmarket.item.controller;

import com.raincloud.sunlightmarket.global.dto.ApiResponse;
import com.raincloud.sunlightmarket.global.security.UserDetailsImpl;
import com.raincloud.sunlightmarket.item.dto.ItemAllResponseDto;
import com.raincloud.sunlightmarket.item.dto.ItemRequestDto;
import com.raincloud.sunlightmarket.item.dto.ItemResponseDto;
import com.raincloud.sunlightmarket.item.dto.ItemUpdateRequest;
import com.raincloud.sunlightmarket.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.RejectedExecutionException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/items")
public class ItemController {

    private final ItemService itemService;

    //상품 등록
    @PostMapping("/add")
    public ApiResponse<ItemResponseDto> addItem(
            @RequestBody ItemRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        try {
            ItemResponseDto responseDto = itemService.addItem(requestDto, userDetails.getUser());
            ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
            return new ApiResponse<ItemResponseDto>(HttpStatus.CREATED.value(),"아이템 추가 성공했습니다",responseDto);
        }catch (RejectedExecutionException | IllegalArgumentException ex){
            return new ApiResponse<ItemResponseDto>(HttpStatus.BAD_REQUEST.value(),ex.getMessage());
        }
    }

    //상품 업데이트
    @PutMapping("")
    public ApiResponse<ItemResponseDto> updatePost(
            @RequestParam Long id,
            @RequestBody ItemUpdateRequest request,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        ItemResponseDto responseDto = itemService.updateItem(id, request, userDetails.getUser());
        return new ApiResponse<ItemResponseDto>(HttpStatus.OK.value(),"아이템 수정 성공했습니다",responseDto);
    }

    @DeleteMapping("")
    public ApiResponse<Void> deletePost(
            @RequestParam Long id,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ){
        itemService.deletePost(id, userDetails.getUser());
        return new ApiResponse<Void>(HttpStatus.OK.value(),"아이템 삭제 성공했습니다");
    }

    //선택 상품 조회
    @GetMapping("/{itemId}")
    public ApiResponse<ItemResponseDto> getItem(
            @PathVariable Long itemId
    ) {
        try {
            ItemResponseDto responseDto = itemService.getItem(itemId);
            return new ApiResponse<ItemResponseDto>(HttpStatus.OK.value(),"아이템 조회에 성공했습니다",responseDto);
        }catch (RejectedExecutionException | IllegalArgumentException ex){
            return new ApiResponse<ItemResponseDto>(HttpStatus.BAD_REQUEST.value(),ex.getMessage());
        }
    }

    //전체 상품 조회
    @GetMapping("")
    public ApiResponse<ItemAllResponseDto> getItems(
            @RequestParam String type
    ) {
        if(type.equals("All")){ return getAllItems();}
        else if(type.equals("Myselect")){return new ApiResponse<ItemAllResponseDto>(HttpStatus.BAD_REQUEST.value(),"myselect");}
        else{return new ApiResponse<ItemAllResponseDto>(HttpStatus.BAD_REQUEST.value(),"올바르지 않은 요청입니다");}
    }

    public ApiResponse<ItemAllResponseDto> getAllItems()
    {
        try {
            ItemAllResponseDto responseDto = new ItemAllResponseDto();
            responseDto.setItemResponseDtos(itemService.getAllItems());
            return new ApiResponse<ItemAllResponseDto>(HttpStatus.OK.value(),"아이템 조회에 성공했습니다",responseDto);
        }catch (RejectedExecutionException | IllegalArgumentException ex){
            return new ApiResponse<ItemAllResponseDto>(HttpStatus.BAD_REQUEST.value(),ex.getMessage());
        }
    }

}
