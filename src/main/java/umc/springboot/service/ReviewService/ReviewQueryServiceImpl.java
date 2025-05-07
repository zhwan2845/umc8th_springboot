package umc.springboot.service.ReviewService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.springboot.domain.Review;
import umc.springboot.repository.ReviewRepository.ReviewRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewQueryServiceImpl implements ReviewQueryService {

    private final ReviewRepository reviewRepository;

    @Override
    public List<Review> findReviewsByStoreId(Long storeId) {
        // storeId에 해당하는 리뷰들을 반환
        return reviewRepository.findByStoreId(storeId);
    }
}
