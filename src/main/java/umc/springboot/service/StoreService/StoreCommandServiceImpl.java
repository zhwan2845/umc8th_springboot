package umc.springboot.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import umc.springboot.aws.AmazonS3Manager;
import umc.springboot.converter.ReviewConverter;
import umc.springboot.domain.Review;
import umc.springboot.domain.Store;
import umc.springboot.domain.Member;
import umc.springboot.domain.Uuid;
import umc.springboot.repository.MemberRepository.MemberRepository;
import umc.springboot.repository.ReviewImageRepository.ReviewImageRepository;
import umc.springboot.repository.ReviewRepository.ReviewRepository;
import umc.springboot.repository.StoreRepository.StoreRepository;
import umc.springboot.repository.UuidRepository.UuidRepository;
import umc.springboot.converter.StoreConverter;
import umc.springboot.web.dto.ReviewRequestDTO;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class StoreCommandServiceImpl implements StoreCommandService{

    private final ReviewRepository reviewRepository;

    private final MemberRepository memberRepository;

    private final StoreRepository storeRepository;

    private final AmazonS3Manager s3Manager;

    private final UuidRepository uuidRepository;

    private final ReviewImageRepository reviewImageRepository;

    @Override
    public Review createReview(Long memberId, Long storeId, ReviewRequestDTO.AddReviewDto request, MultipartFile reviewPicture) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 가게입니다."));

        Review review = ReviewConverter.toReview(request, store, member);

        String uuid = UUID.randomUUID().toString();
        Uuid savedUuid = uuidRepository.save(Uuid.builder()
                .uuid(uuid).build());

        String pictureUrl = s3Manager.uploadFile(s3Manager.generateReviewKeyName(savedUuid), reviewPicture);

        reviewImageRepository.save(ReviewConverter.toReviewImage(pictureUrl, review));
        return reviewRepository.save(review);
    }
}