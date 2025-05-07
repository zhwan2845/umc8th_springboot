package umc.springboot;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import umc.springboot.domain.Member;
import umc.springboot.domain.Mission;
import umc.springboot.domain.Region;
import umc.springboot.domain.Store;
import umc.springboot.domain.enums.Gender;
import umc.springboot.domain.enums.MemberStatus;
import umc.springboot.domain.enums.MissionStatus;
import umc.springboot.domain.enums.SocialType;
import umc.springboot.domain.mapping.MemberMission;
import umc.springboot.service.MissionService.MissionQueryService;
import umc.springboot.service.StoreService.StoreQueryService;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
@EnableJpaAuditing
public class SpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(ApplicationContext context) {
		return args -> {
			StoreQueryService storeService = context.getBean(StoreQueryService.class);

			// 파라미터 값 설정
			String name = "요아정";
			Float score = 4.0f;

			// 쿼리 메서드 호출 및 쿼리 문자열과 파라미터 출력
			System.out.println("Executing findStoresByNameAndScore with parameters:");
			System.out.println("Name: " + name);
			System.out.println("Score: " + score);

			storeService.findStoresByNameAndScore(name, score)
					.forEach(System.out::println);



			// 필요한 서비스 가져오기
			MissionQueryService missionQueryService = context.getBean(MissionQueryService.class);
			EntityManager em = context.getBean(EntityManager.class);

			// 트랜잭션 시작
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();

			// Region 생성
			Region region = Region.builder()
					.name("서울")
					.build();
			em.persist(region);

			// Store 생성
			Store store = Store.builder()
					.name("강남점")
					.score(4.5f)
					.region(region)
					.build();
			em.persist(store);

			// Member 생성
			Member member = Member.builder()
					.name("홍길동")
					.address("서울시 강남구")
					.specAddress("역삼동")
					.gender(Gender.MALE)
					.socialType(SocialType.KAKAO)
					.status(MemberStatus.ACTIVE)
					.missionStatus(MissionStatus.CHALLENGING)
					.email("hong@example.com")
					.point(0)
					.build();
			em.persist(member);

			// Mission 1 (유효한 미션)
			Mission mission1 = Mission.builder()
					.missionSpec("미션1") // 미션 설명 (예시로 제목 대신 사용)
					.reward(1000)
//					.deadline(LocalDateTime.now().plusDays(1)) // 아직 마감되지 않음
					.store(store)
					.build();
			em.persist(mission1);

			// Mission 2 (이미 참여한 미션)
			Mission mission2 = Mission.builder()
					.missionSpec("미션2") // 미션 설명 (예시로 제목 대신 사용)
					.reward(1000)
//					.deadline(LocalDateTime.now().plusDays(1))
					.store(store)
					.build();
			em.persist(mission2);

			// MemberMission 생성 (member가 mission2에 참여함)
			MemberMission memberMission = MemberMission.builder()
					.member(member)
					.mission(mission2)
					.status(MissionStatus.COMPLETE)
					.build();
			em.persist(memberMission);

			transaction.commit();

			// ✅ 테스트 실행
			System.out.println("=== 유효한 미션 조회 결과 ===");
			List<Mission> availableMissions = missionQueryService.findAvailableMissionsByRegion(
					"서울",
					member.getId(),
					Long.MAX_VALUE // 가장 큰 커서부터 시작
			);

			availableMissions.forEach(m -> System.out.println(m.getTitle()));
		};
	}
}
