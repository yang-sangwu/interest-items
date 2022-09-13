package com.interest.service.impl;


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

import javax.annotation.Resource;
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
    public Map login(String code, String password) {
        Map<String,Object>map=new HashMap<>();
        QueryWrapper<Manager> wrapper = Wrappers.query();
        wrapper.in("code", code).in("password",password).last("limit 1");
        List<Manager> list = managerMapper.selectList(wrapper);
        if(!list.isEmpty()) {
            Manager manager = list.get(0);
            Map<String,String> payload=new HashMap<>();
            payload.put("code",manager.getCode());
            map.put("state",true);
            map.put("msg","认证成功！");
            //登录成功，将生成的token存入redis中
            String token = JWTUtils.getToken(payload);
        //    ValueOperations valueOperations = redisTemplate.opsForValue();
       //     valueOperations.set(token, "default", 30, TimeUnit.MINUTES);
            //响应token
            map.put("token",token);
        }else{
            map.put("state",false);
            map.put("msg","认证失败！");
        }
        return map;
    }

//    /**
//     * 用户登录
//     * @param request
//     * @return
//     */
//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public Result login(@Valid @RequestBody LoginRequest request){
//        Result result = new Result();
//        try {
//            //密码加密
//            request.setPassword(MD5Util.MD5(request.getPassword()));
//            //查询用户是否存在
//            User user = userService.queryByPhoneAndPasswordAndType(request);
//            if(user == null){
//                return new Result(false, MessageEnum.Login_User_FAIL);
//            }
//            //登录成功，将生成的token存入redis中
//            String token = UUID.randomUUID().toString().replaceAll("-", "") + "";
//            //System.out.println(user);
//            ValueOperations valueOperations = redisTemplate.opsForValue();
//            valueOperations.set(token, "default", 30, TimeUnit.MINUTES);
//            result.setFlag(true);
//            result.setMessage(MessageEnum.Login_User_SUCCESS);
//            result.setData(token);
//        }catch (Exception e){
//            logger.error("用户登录发生异常,param:{}, exception:{}", JSON.toJSONString(request),e);
//            return new Result(false, MessageEnum.Login_User_Exception);
//        }
//        logger.info("用户登录成功, user register param:{}", JSON.toJSONString(request));
//        return result;
//    }

//    @Override
//    public Map loginTest(HttpServletRequest request) {
//        String token = request.getHeader("token");
//        DecodedJWT verify = JWTUtils.verify(token);
//        String code = verify.getClaim("id").asString();
//        log.info("用户id：{}", code);
//        //TODO 业务逻辑
//        Map<String, Object> map = new HashMap<>();
//        map.put("state", true);
//        map.put("msg", "请求成功");
//        return map;
//    }
}
