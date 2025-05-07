package umc.springboot.repository.ReviewRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.springboot.domain.Review;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByStoreId(Long storeId);
}
