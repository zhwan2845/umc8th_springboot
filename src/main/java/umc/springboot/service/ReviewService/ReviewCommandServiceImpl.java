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

    private final ReviewRepository reviewRepository;

    @PersistenceContext
    private final EntityManager em;

//    @Override
//    public void saveReview(Long memberId, Long storeId, String body, float score) {
//        Member member = em.getReference(Member.class, memberId);
//        Store store = em.getReference(Store.class, storeId);
//
//        Review review = Review.builder()
//                .member(member)
//                .store(store)
//                .body(body)
//                .score(score)
//                .createdAt(LocalDateTime.now())
//                .build();
//
//        reviewRepository.save(review);
//    }
    @Override
    public void saveReview(Long memberId, Long storeId, String body, float score) {
        Member member = em.getReference(Member.class, memberId);
        Store store = em.getReference(Store.class, storeId);

        Review review = Review.builder()
                .member(member)
                .store(store)
                .body(body)
                .score(score)
                .build(); // createdAt은 수동으로 설정

//        review.setCreatedAt(LocalDateTime.now()); // createdAt 수동 설정
        reviewRepository.save(review);
    }

}
