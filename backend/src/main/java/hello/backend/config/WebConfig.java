package hello.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.EncodedResourceResolver;

import java.util.concurrent.TimeUnit;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/v1/**")
                        .allowedOrigins("http://175.45.194.64:8080") // 허용할 출처 추가
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .allowCredentials(true);  // 필요시 사용
            }
        };
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // 모든 경로에 대해 CORS 허용
                .allowedOrigins("http://localhost:5173", "http://localhost:8080", "http://175.45.194.64:8080")  // 허용할 출처
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // 허용할 HTTP 메서드
                .allowCredentials(true)  // 자격 증명 허용 (쿠키 등)
                .allowedHeaders("*")  // 허용할 헤더
                .maxAge(3600);  // CORS 요청 캐싱 시간 (초 단위)
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/index.html");
    }
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("*.js", "*.gz", "*.css", "*.woff", "*.ttf", "*.eot", "*.svg", "*.png", "*.jpg", "*.gif", "*.json")
//                .setCacheControl(CacheControl.maxAge(1, TimeUnit.HOURS))
//                .addResourceLocations("classpath:/static/dist/")
//                .resourceChain(true)
//                .addResolver(new EncodedResourceResolver());
//
//        registry.addResourceHandler("/**")
//                .setCacheControl(CacheControl.maxAge(1, TimeUnit.DAYS))
//                .addResourceLocations("classpath:/META-INF/resources/", "classpath:/resources/", "classpath:/static/dist/")
//                .resourceChain(true)
//                .addResolver(new EncodedResourceResolver());
//
////        registry.addResourceHandler("/api/**").addResourceLocations("classpath:/api/");
//    }
}
