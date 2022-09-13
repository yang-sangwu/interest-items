package com.interest.service.impl;


import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.interest.mapper.ManagerMapper;
import com.interest.pojo.Manager;
import com.interest.service.ManagerService;
import com.interest.utils.JWTUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author a1002
 */
@Slf4j
@Service
public class ManagerServiceImpl extends ServiceImpl<ManagerMapper, Manager> implements ManagerService {
    @Resource
    private ManagerMapper managerMapper;

    @Override
    public Map login(@RequestParam("code") String code, @RequestParam("password") String password) {
        Map<String,Object>map=new HashMap<>();
        QueryWrapper<Manager> wrapper = Wrappers.query();
        wrapper.in("code", code).in("password",password).last("limit 1");
        List<Manager> list = managerMapper.selectList(wrapper);
        if(!list.isEmpty()) {
            Manager manager = list.get(0);
            Map<String,String> payload=new HashMap<>();
            payload.put("code",manager.getCode());
            String token = JWTUtils.getToken(payload);
            map.put("state",true);
            map.put("msg","认证成功！");
            map.put("token",token);
        }else{
            map.put("state",false);
            map.put("msg","认证失败！");
        }
        return map;
    }

    @Override
    public Map loginTest(HttpServletRequest request) {
        String token = request.getHeader("token");
        DecodedJWT verify = JWTUtils.verify(token);
        String code = verify.getClaim("code").asString();
        log.info("用户code：{}", code);
        //TODO 业务逻辑
        Map<String, Object> map = new HashMap<>();
        map.put("state", true);
        map.put("msg", "请求成功");
        return map;
    }
}
