package io.may4th.chat.web;

import io.may4th.chat.security.api.CurrentUserArgumentResolver;
import io.may4th.chat.security.api.JwtProperties;
import io.may4th.chat.security.api.SecurityHandlerInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private final CurrentUserArgumentResolver currentUserArgumentResolver;

    @Autowired
    private final SecurityHandlerInterceptor securityHandlerInterceptor;

    @Value("${app.jwt.secret}")
    private String jwtSecret;

    @Value("${app.jwt.expms}")
    private long jwtExpms;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(currentUserArgumentResolver);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(securityHandlerInterceptor);
    }

    @Bean
    public JwtProperties JwtProperties() {
        return new JwtProperties(jwtSecret, jwtExpms);
    }
}
