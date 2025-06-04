package umc.springboot;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.Transactional;
import umc.springboot.domain.*;
import umc.springboot.domain.enums.Gender;
import umc.springboot.domain.enums.MemberStatus;
import umc.springboot.domain.enums.MissionStatus;
import umc.springboot.domain.enums.SocialType;
import umc.springboot.domain.mapping.MemberMission;
import umc.springboot.repository.ReviewRepository.ReviewRepository;
import umc.springboot.service.MissionService.MissionQueryService;
import umc.springboot.service.ReviewService.ReviewCommandService;
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

//			storeService.findStoresByNameAndScore(name, score)
//					.forEach(System.out::println);
//
//
//			SpringbootApplication app = context.getBean(SpringbootApplication.class);
//			app.initData(context); // 이 메서드에 @Transactional이 붙어있음
//			app.saveAndPrintReview(context); // 이것도 트랜잭션 안에서 실행됨
//
//			MissionQueryService missionQueryService = context.getBean(MissionQueryService.class);
//			Long memberId = 1L; // 실제 생성된 Member의 ID로 교체해야 함
//
//			System.out.println("=== 유효한 미션 조회 결과 ===");
//			List<Mission> availableMissions = missionQueryService.findAvailableMissionsByRegion(
//					"서울",
//					memberId,
//					Long.MAX_VALUE
//			);
//
//			availableMissions.forEach(m -> System.out.println(m.getTitle()));
		};
	}

	@Transactional
	public void initData(ApplicationContext context) {
		EntityManager em = context.getBean(EntityManager.class);

		Region region = Region.builder().name("서울").build();
		em.persist(region);

		Store store = Store.builder().name("강남점").score(4.5f).region(region).build();
		em.persist(store);

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

		Mission mission1 = Mission.builder()
				.missionSpec("미션1")
				.reward(1000)
				.store(store)
				.build();
		em.persist(mission1);

		Mission mission2 = Mission.builder()
				.missionSpec("미션2")
				.reward(1000)
				.store(store)
				.build();
		em.persist(mission2);

		MemberMission memberMission = MemberMission.builder()
				.member(member)
				.mission(mission2)
				.status(MissionStatus.COMPLETE)
				.build();
		em.persist(memberMission);
	}

	@Transactional
	public void saveAndPrintReview(ApplicationContext context) {
		ReviewCommandService reviewCommandService = context.getBean(ReviewCommandService.class);
		Review egReview = reviewCommandService.saveReview(1L, 1L, "후기 제목", "매우 만족합니다!", 4.5f);
		System.out.println(egReview);
	}
}
