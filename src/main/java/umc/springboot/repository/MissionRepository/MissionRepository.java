package umc.springboot.repository.MissionRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.springboot.domain.Mission;

public interface MissionRepository extends JpaRepository<Mission, Long>, MissionRepositoryCustom {
}
