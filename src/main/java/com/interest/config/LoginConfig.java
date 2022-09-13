//package com.interest.config;
//
//
//import com.interest.interceptors.LoginInterceptor;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import javax.annotation.Resource;
//
///**
// * @author a1002
// */
//@Configuration
//public class LoginConfig implements WebMvcConfigurer {
//
//    @Resource
//  //  private LoginInterceptor loginInterceptor;
//
//    //不拦截的请求
//    String[] excludePatterns = {
//            "/login"};
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        System.out.println("got there");
//        // 添加拦截器
//        registry.addInterceptor(loginInterceptor)
//                // 要拦截的请求（所有请求）
//                .addPathPatterns("/**")
//                // 排除的拦截请求
//                .excludePathPatterns(excludePatterns);
//    }
//}
