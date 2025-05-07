package umc.springboot.service.ReviewService;

public interface ReviewCommandService {
    void saveReview(Long memberId, Long storeId, String body, float score);
}
