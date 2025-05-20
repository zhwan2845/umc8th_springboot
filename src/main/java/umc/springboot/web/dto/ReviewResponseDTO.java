package umc.springboot.web.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ReviewResponseDTO {

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    public static class AddResultDTO {
        private Long reviewId;
        private String storeName;
        private String title;
        private Float score;
        private LocalDateTime createdAt;
    }
}

