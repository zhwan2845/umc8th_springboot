package umc.springboot.service.ReviewService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.springboot.domain.Member;
import umc.springboot.domain.Review;
import umc.springboot.domain.Store;
import umc.springboot.repository.ReviewRepository.ReviewRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final EntityManager em;
    private final ReviewRepository reviewRepository;

    @Override
    @Transactional
    public Review saveReview(Long memberId, Long storeId, String title, String body, Float score) {
        Member member = em.getReference(Member.class, memberId);
        Store store = em.getReference(Store.class, storeId);

        Review review = Review.builder()
                .member(member)
                .store(store)
                .title(title)
                .body(body)
                .score(score)
                .build();

        return reviewRepository.save(review);
    }
}
