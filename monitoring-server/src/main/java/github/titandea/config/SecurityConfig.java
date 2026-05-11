package github.titandea.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * ВРЕМЕННО ОТКЛЮЧАЕТ АУТЕНТИФИКАЦИЮ И CSRF ДЛЯ РАЗРАБОТКИ.
     * Когда будешь готов включить безопасность:
     * 1. Закомментируй этот @Bean
     * 2. Настрой реальные правила доступа и JWT/Session
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()
                );
        return http.build();
    }
}