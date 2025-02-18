package park.haru.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import park.haru.config.jwt.TokenProvider;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private TokenProvider tokenProvider;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8081", "http://localhost:8082", "http://localhost:3000", "https://market3.haru.company", "https://market4.haru.company")
                .exposedHeaders("Authorization")
                .allowedMethods("GET", "POST", "PUT", "DELETE","PATCH") // 허용할 메서드
                .allowedHeaders("*")
                .allowCredentials(true);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new TokenExportInterceptor(tokenProvider))
                .addPathPatterns("/**");
    }

    @Bean
    public UserInformationExport userInformationExport(){
        return new UserInformationExport();
    }
}
