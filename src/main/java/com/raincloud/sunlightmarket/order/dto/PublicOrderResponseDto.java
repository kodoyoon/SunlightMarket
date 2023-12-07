package com.raincloud.sunlightmarket.order.dto;

import com.raincloud.sunlightmarket.order.entity.Order;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PublicOrderResponseDto {


    private String buyerName;

    private String price;

    private String orderStatus;


    public PublicOrderResponseDto(Order order){

        buyerName = order.getBuyer().getNickname();
        orderStatus = order.getOrderStatus();
        price = order.getPrice();

    }
}