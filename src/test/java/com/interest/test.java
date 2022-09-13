package com.interest;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import com.interest.mapper.InterestMapper;
import com.interest.pojo.Interest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class test {
    @Autowired
    private InterestMapper interestMapper;

    @Test
    void contextLoads1(){
        HashMap<String,Object>map=new HashMap<>();
        Calendar instance=Calendar.getInstance();
        instance.add(Calendar.SECOND,200);
        String token=JWT.create()
                .withHeader(map)
                .withClaim("userId",1)
                .withClaim("username","yangsaiya")
                .withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256("FDHKR^$#$"));
        System.out.println(token);
    }

    @Test
    public void test1(){
        JWTVerifier jwtVerifier=JWT.require(Algorithm.HMAC256("FDHKR^$#$")).build();
        DecodedJWT verify=jwtVerifier.verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2NjMwMzAzNzIsInVzZXJJZCI6MSwidXNlcm5hbWUiOiJ5YW5nc2FpeWEifQ.G8XDgmIWEEcKSpO-gMTTVZcpnZ5KpL4E8MEq1rTfK2Q");
        System.out.println("用户Id：" + verify.getClaim("userId").asInt());
        System.out.println("用户名：" + verify.getClaim("username").asString());
        System.out.println("过期时间：" + verify.getExpiresAt());
    }





    //令牌获取
    @Test
    void contextLoads() {
        Map<String, Object> map = new HashMap<>();

        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND, 2000);

        String token = JWT.create().withHeader(map) //header
                .withClaim("userId", 21)//payload
                .withClaim("username", "xiaochen")//payload
                .withExpiresAt(instance.getTime())//指定令牌的过期时间
                .sign(Algorithm.HMAC256("!Q@W#E$R")) //签名
                ;
        System.out.println(token);
    }

    //令牌验证:根据令牌和签名解析数据
    //常见异常：
    //SignatureVerificationException 签名不一致异常
    //TokenExpiredException 令牌过期异常
    //AlgorithmMismatchException 算法不匹配异常
    //InvalidClaimException 失效的payload异常
    @Test
    void test() {
//        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1OTk2NDkxMzMsInVzZXJJZCI6MjEsInVzZXJuYW1lIjoieGlhb2NoZW4ifQ.LmTERviRHnmKpOeXO0f9K2nR1C7AovGfAV6Fmx7tcw0";
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1OTk2NTEzOTgsInVzZXJJZCI6MjEsInVzZXJuYW1lIjoieGlhb2NoZW4ifQ.KgJhjqs0T2WCijB9MDJQE9pCoKaC_eO3H6ILDmrhz1A";

        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("!Q@W#E$R")).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        System.out.println("用户Id：" + decodedJWT.getClaim("userId").asInt());
        System.out.println("用户名：" + decodedJWT.getClaim("username"));
        System.out.println("过期时间：" + decodedJWT.getExpiresAt());

//        用户Id：21
//        用户名：com.auth0.jwt.impl.JsonNodeClaim@236e3f4e
//        过期时间：Wed Sep 09 19:36:38 CST 2020
//    @Test
//    public void testSelectList() {
//        //通过条件构造器查询一个list集合，若没有条件，则可以设置null为参数
//        List<Interest> list = interestMapper.selectList(null);
//        System.out.println(list);
//        list.forEach(System.out::println);
//    }
    }
}
