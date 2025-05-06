package umc.springboot.repository.StoreRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.springboot.domain.Store;

public interface StoreRepository extends JpaRepository<Store, Long>, StoreRepositoryCustom {
}