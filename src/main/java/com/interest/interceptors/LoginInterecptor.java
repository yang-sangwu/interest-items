package com.interest.interceptors;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Result;
import java.io.IOException;
import java.io.PrintWriter;

@Component
class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private ObjectMapper objectMapper;

//    @Autowired
//    private StringRedisTemplate redisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 从请求头里面获取token,因为每次都会在请求头里面携带token
        System.out.println(request.getRequestURI());
        String token = request.getHeader("TOKEN");
        if (StringUtils.isNotBlank(token)) {
            //根据head中的token，查询redis中是否有数据
            ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
            String tokenType = opsForValue.get(token);
            // 如果能够获取到数据，说明token未过期
            if (StringUtils.isNotBlank(tokenType)) {
                return true;
            }
        }
        //从请求头中获取不到token说明未登录，阻止该请求访问资源
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        try {
            PrintWriter pw = response.getWriter();
            pw.println(objectMapper.writeValueAsString("false!"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}

