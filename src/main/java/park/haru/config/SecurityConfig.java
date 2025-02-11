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
import park.haru.config.filter.AuthenticationFilter;
import park.haru.config.jwt.TokenProvider;

import java.time.Duration;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private TokenProvider tokenProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf().disable()
                .httpBasic().disable()
                .formLogin().disable()
                .logout().disable()
                .apply(new CustomFilter());

        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeHttpRequests(authorize -> authorize
                .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
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
                    .addFilter(new AuthenticationFilter(authenticationManager,tokenProvider));
        }
    }
}
