package umc.springboot.converter;

import umc.springboot.domain.Member;
import umc.springboot.domain.Review;
import umc.springboot.domain.Store;
import umc.springboot.web.dto.ReviewRequestDTO;
import umc.springboot.web.dto.ReviewResponseDTO;

import java.time.LocalDateTime;

public class ReviewConverter {

    // Review → DTO (AddReviewResultDTO)
    public static ReviewResponseDTO.AddResultDTO toAddReviewResultDTO(Review review) {
        return ReviewResponseDTO.AddResultDTO.builder()
                .reviewId(review.getId())
                .storeName(review.getStore().getName())
                .title(review.getTitle())
                .score(review.getScore())
                .createdAt(LocalDateTime.now()) // 혹은 review.getCreatedAt()이 있다면 그걸 사용
                .build();
    }

    // DTO → Review
    public static Review toReview(ReviewRequestDTO.AddReviewDto request, Store store, Member member) {
        return Review.builder()
                .title(request.getTitle())
                .body(request.getBody())
                .score(request.getScore())
                .store(store)
                .member(member)
                .build();
    }
}
