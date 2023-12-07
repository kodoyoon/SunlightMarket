package com.raincloud.sunlightmarket.order.dto;

import com.raincloud.sunlightmarket.item.entity.Item;
import com.raincloud.sunlightmarket.order.entity.Order;
import com.raincloud.sunlightmarket.user.entity.Buyer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class OrderResponseDto {
    private String buyerName;

    private String address;

    private String orderStatus;


    public OrderResponseDto(Order order){
        buyerName = order.getBuyer().getNickname();
        address = order.getAddress();
        orderStatus = order.getOrderStatus();
    }
}