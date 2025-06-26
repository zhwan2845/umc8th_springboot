package umc.springboot.repository.UuidRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.springboot.domain.Uuid;

public interface UuidRepository extends JpaRepository<Uuid, Long> {
}