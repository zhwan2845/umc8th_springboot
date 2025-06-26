package umc.springboot.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import umc.springboot.validation.annotation.ExistStore;

@Getter
@Setter
public class ReviewRequestDTO {

    @Getter
    @Setter
    public static class AddReviewDto {
        @NotBlank(message = "제목은 필수입니다.")
        private String title;

        @NotBlank(message = "본문 내용은 필수입니다.")
        private String body;

        @Schema(description = "리뷰 점수 (0.0 ~ 5.0)", example = "0")
        @NotNull(message = "점수는 필수입니다.")
        @DecimalMin(value = "0.0", message = "최소 점수는 0.0입니다.")
        @DecimalMax(value = "5.0", message = "최대 점수는 5.0입니다.")
        private Float score;

        // storeId는 PathVariable로 받으므로 제거
        @NotNull(message = "회원 ID는 필수입니다.")
        private Long memberId;
    }
}
