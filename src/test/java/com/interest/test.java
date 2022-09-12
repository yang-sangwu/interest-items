package com.interest;

import com.interest.mapper.InterestMapper;
import com.interest.pojo.Interest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class test {
    @Autowired
    private InterestMapper interestMapper;


    @Test
    public void testSelectList() {
        //通过条件构造器查询一个list集合，若没有条件，则可以设置null为参数
        List<Interest> list = interestMapper.selectList(null);
        System.out.println(list);
        list.forEach(System.out::println);
    }
}
