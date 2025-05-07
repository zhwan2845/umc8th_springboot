package umc.springboot.service.ReviewService;

import umc.springboot.domain.Review;
import java.util.List;

public interface ReviewQueryService {
    List<Review> findReviewsByStoreId(Long storeId);
}
