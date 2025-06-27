package umc.springboot.service.ReviewService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import umc.springboot.apiPayload.code.status.ErrorStatus;
import umc.springboot.apiPayload.exception.handler.StoreHandler;
import umc.springboot.aws.AmazonS3Manager;
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
    private final AmazonS3Manager amazonS3Manager;

    @Override
    @Transactional
    public Review saveReview(Long memberId, Long storeId, String title, String body, Float score, MultipartFile reviewPicture) {
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

    @Override
    public void deleteReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 리뷰입니다."));

        String s3Key = extractS3KeyFromUrl(review.getReviewImage().getImageUrl());

        amazonS3Manager.deleteFile(s3Key);

        reviewRepository.delete(review);
    }

    private String extractS3KeyFromUrl(String imageUrl) {
        return imageUrl.substring(imageUrl.indexOf("review/"));
    }
}
