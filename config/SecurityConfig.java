package umc.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .formLogin().disable() // 로그인 폼 비활성화
                .httpBasic().disable() // Basic 인증 비활성화
                .authorizeHttpRequests()
                .anyRequest().permitAll(); // 모든 요청 허용

        return http.build();
    }
}
