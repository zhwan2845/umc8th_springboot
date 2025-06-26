package umc.springboot.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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

    @PostMapping(
            value = "/{storeId}/reviews",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}
    )
    public ApiResponse<ReviewResponseDTO.AddResultDTO> saveReview(
            @RequestPart("request") @Valid ReviewRequestDTO.AddReviewDto request,
            @PathVariable("storeId") Long storeId,
            @RequestParam("memberId") Long memberId,
            @RequestPart("reviewPicture") MultipartFile reviewPicture
    ) {
        Review review = reviewCommandService.saveReview(
                memberId,
                storeId,
                request.getTitle(),
                request.getBody(),
                request.getScore(),
                reviewPicture
        );
        return ApiResponse.onSuccess(ReviewConverter.toAddReviewResultDTO(review));
    }
}
