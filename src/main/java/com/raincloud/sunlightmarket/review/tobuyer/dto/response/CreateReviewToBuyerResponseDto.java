package com.raincloud.sunlightmarket.review.tobuyer.dto.response;

import com.raincloud.sunlightmarket.review.tobuyer.entity.ReviewToBuyer;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class CreateReviewToBuyerResponseDto {

    private Long id;
    private String nickname;
    private String comment;
    private Boolean liked;
    private LocalDateTime date;

    public CreateReviewToBuyerResponseDto(ReviewToBuyer review) {
        this.id = review.getId();
        this.nickname = review.getUser().getNickname();
        this.comment = review.getComment();
        this.liked = review.getLiked();
        this.date = review.getCreatedAt();
    }
}
