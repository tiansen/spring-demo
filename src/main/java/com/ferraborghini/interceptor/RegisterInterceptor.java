package com.ferraborghini.interceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class RegisterInterceptor extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        ApiInterceptor apiInterceptor = new ApiInterceptor();


        registry.addInterceptor(apiInterceptor).addPathPatterns("/v1/hello");
        super.addInterceptors(registry);
    }
}