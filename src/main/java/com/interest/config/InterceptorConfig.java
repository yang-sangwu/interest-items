//package com.interest.config;
//
//import com.interest.interceptors.JWTInterceptor;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
///**
// * @author a1002
// */
//@Configuration
//public class InterceptorConfig implements WebMvcConfigurer {
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new JWTInterceptor())
//                .addPathPatterns("/loginTest")
//            //    .addPathPatterns("/interest/**")
//                //放行
//                .excludePathPatterns("/login")
//        ;
//    }
//}
