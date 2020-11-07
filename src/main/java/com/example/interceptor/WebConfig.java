package com.example.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/admin/**")  // 配置拦截器，拦截/admin下的所有路径
                .excludePathPatterns("/admin") // 配置拦截器，排除掉该路径，不做拦截
                .excludePathPatterns("/admin/login");
    }
}
