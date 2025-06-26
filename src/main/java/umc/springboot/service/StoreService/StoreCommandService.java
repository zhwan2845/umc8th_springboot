package umc.springboot.service.StoreService;

import org.springframework.web.multipart.MultipartFile;
import umc.springboot.domain.Review;
import umc.springboot.web.dto.ReviewRequestDTO;

public interface StoreCommandService {

    Review createReview(Long memberId, Long storeId, ReviewRequestDTO.AddReviewDto request, MultipartFile reviewPicture);
}