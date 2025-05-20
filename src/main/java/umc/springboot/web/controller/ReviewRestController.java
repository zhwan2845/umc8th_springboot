package umc.springboot.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.springboot.apiPayload.ApiResponse;
import umc.springboot.converter.ReviewConverter;
import umc.springboot.domain.Review;
import umc.springboot.service.ReviewService.ReviewCommandService;
import umc.springboot.web.dto.ReviewRequestDTO;
import umc.springboot.web.dto.ReviewResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class ReviewRestController {

    private final ReviewCommandService reviewCommandService;

    @PostMapping("/{storeId}/reviews")
    public ApiResponse<ReviewResponseDTO.AddResultDTO> saveReview(
            @PathVariable("storeId") Long storeId,
            @RequestBody @Valid ReviewRequestDTO.AddReviewDto request
    ) {
        Review review = reviewCommandService.saveReview(
                request.getMemberId(),
                storeId,
                request.getTitle(),
                request.getBody(),
                request.getScore()
        );
        return ApiResponse.onSuccess(ReviewConverter.toAddReviewResultDTO(review));
    }
}
