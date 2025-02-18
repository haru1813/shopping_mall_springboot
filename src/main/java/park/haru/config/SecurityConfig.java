package park.haru.config;

import jakarta.servlet.DispatcherType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import park.haru.common.service.LoginService;
import park.haru.config.filter.AuthenticationFilter;
import park.haru.config.filter.AuthorizationFilter;
import park.haru.config.jwt.JwtProperties;
import park.haru.config.jwt.TokenProvider;

import java.time.Duration;
import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private LoginService loginService;

    @Autowired
    private JwtProperties jwtProperties;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf().disable()
                .httpBasic().disable()
                .formLogin().disable()
                .logout().disable()
                .apply(new CustomFilter());

        http.cors().configurationSource(request -> {
            CorsConfiguration config = new CorsConfiguration();
            config.setAllowedOrigins(Arrays.asList("http://localhost:8081","http://localhost:8082","http://localhost:3000", "https://market3.haru.company", "https://market4.haru.company")); // 허용할 도메인
            config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE","PATCH")); // 허용할 HTTP 메서드
            config.setAllowedHeaders(Arrays.asList("*")); // 허용할 헤더
            config.addExposedHeader("Authorization");
            return config;
        });

        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeHttpRequests(authorize -> authorize
                .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                        .requestMatchers("/user/**").hasAuthority("사용자")
                        .requestMatchers("/user/**").hasAuthority("관리자")
                        .requestMatchers("/admin/**").hasAuthority("관리자")
//                .requestMatchers("/api/v1/user/**").hasAnyRole("USER","MANAGER","ADMIN")
//                .requestMatchers("/api/v1/manager/**").hasAnyRole("MANAGER","ADMIN")
//                .requestMatchers("/api/v1/admin/**").hasRole("ADMIN")
                //.requestMatchers("/").hasRole("ADMIN")
                .anyRequest().permitAll()
        );


        return http.build();
    }

    public class CustomFilter extends AbstractHttpConfigurer<CustomFilter,HttpSecurity>{
        @Override
        public void configure(HttpSecurity http) throws Exception{
            AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
            http
                    .addFilter(new AuthenticationFilter(authenticationManager,tokenProvider,loginService,jwtProperties))
                    .addFilter(new AuthorizationFilter(authenticationManager,loginService,tokenProvider));
        }
    }
}
