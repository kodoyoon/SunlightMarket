package com.raincloud.sunlightmarket.user.entity;

import com.raincloud.sunlightmarket.item.entity.Item;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "seller")
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seller_id")
    private Long id;

    private Long likes;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column
    private String nickname;

    @OneToMany(mappedBy = "item")
    private List<Item> items = new ArrayList<>();

    @Builder
    public Seller(Long likes, User user) {
        this.likes = likes;
        this.user = user;
        this.nickname = user.getNickname();
    }

}
