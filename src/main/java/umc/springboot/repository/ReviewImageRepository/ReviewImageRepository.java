package umc.springboot.repository.ReviewImageRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.springboot.domain.ReviewImage;

public interface ReviewImageRepository extends JpaRepository<ReviewImage, Long> {
}
