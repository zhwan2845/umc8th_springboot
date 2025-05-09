package umc.springboot.service.ReviewService;

import umc.springboot.domain.Review;

public interface ReviewCommandService {
    Review saveReview(Long memberId, Long storeId, String title, String body, Float score);
}
