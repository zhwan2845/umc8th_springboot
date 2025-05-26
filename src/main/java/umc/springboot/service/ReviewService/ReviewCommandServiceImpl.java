package umc.springboot.service.ReviewService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.springboot.apiPayload.code.status.ErrorStatus;
import umc.springboot.apiPayload.exception.handler.StoreHandler;
import umc.springboot.domain.Member;
import umc.springboot.domain.Review;
import umc.springboot.domain.Store;
import umc.springboot.repository.ReviewRepository.ReviewRepository;
import umc.springboot.repository.StoreRepository.StoreRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final EntityManager em;
    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;

    @Override
    @Transactional
    public Review saveReview(Long memberId, Long storeId, String title, String body, Float score) {
        Member member = em.getReference(Member.class, memberId);

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreHandler(ErrorStatus.STORE_NOT_FOUND));

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
