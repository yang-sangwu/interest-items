package com.interest.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.interest.config.Response;
import com.interest.mapper.InterestMapper;
import com.interest.pojo.Interest;
import com.interest.service.InterestService;
import com.interest.utils.IDutils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author a1002
 */
@Service
public class InterestServiceImpl extends ServiceImpl<InterestMapper,Interest> implements InterestService {
    @Autowired
    private InterestMapper interestMapper;

    @Override
    public int insertStudent(String name,String sex,String birthday,String phone,String type,String course,String registerTime,String remaining,String score) {
        String code=IDutils.getUUID(16);
        Interest interest=new Interest(code,name,sex,birthday,phone,type,course, registerTime, remaining, score);
        return interestMapper.insert(interest);
    }

    @Override
    public void deleteStudent(Long id) {
        interestMapper.deleteById(id);
    }

    @Override
    public Response updateStudent(Interest interest) {
        Long id=interest.getId();
        Interest interest1=interestMapper.selectById(id);
        if(interest1 != null){
            interestMapper.updateById(interest);
            return Response.ok("success!");
        }else{
            return Response.error("failed!");
        }
    }

    @Override
    public Map queryInterestByPages(int pages, int num) {
        List<Interest> userList = interestMapper.selectList(null);
        Map<String, List> map = new HashMap<>();
        if (userList.size() > num) {
            List<Interest> listIn = new LinkedList<>();//用来存放分页后获取的数据
            int count = userList.size();//集合中数据总数量
            int totalPages;
            int total;
            if (count % num == 0) {
                totalPages = count / num;
            } else {
                total = count / num;
                totalPages = total + 1;
            }
            List<Integer> list2 = new LinkedList<>();
            list2.add(totalPages);
            map.put("总共的页数为", list2);
            int thePage = (pages - 1) * num;
            //使用listIn来存放分页查询数据
            for (int i = thePage; i < num; i++) {
                listIn.add(userList.get(i));
            }
            map.put("查询信息", listIn);
            List<Integer> listCounts = new LinkedList<>();
            listCounts.add(userList.size());
            map.put("总条数为", listCounts);
            return map;
        } else {
            List<Integer> integerList = new LinkedList<>();
            integerList.add(userList.size());
            map.put("总条数为", integerList);
            map.put("查询信息", userList);
            List<Integer> list2 = new LinkedList<>();
            list2.add(1);
            map.put("总共的页数为", list2);
            return map;
        }
    }

    @Override
    public Map queryInterestVague(String thing,int pages,int num) {
        QueryWrapper<Interest> wrapper = new QueryWrapper();
        wrapper.like("name", thing).or().like("sex",thing).or().like("birthday",thing).or().like("phone",thing).or().like("type",thing).or().like("score",thing);
        List<Interest> userList = interestMapper.selectList(wrapper);
        Map<String, List> map = new HashMap<>();
        if (userList.size() > num) {
            List<Interest> listIn = new LinkedList<>();//用来存放分页后获取的数据
            int count = userList.size();//集合中数据总数量
            int totalPages;
            int total;
            if (count % num == 0) {
                totalPages = count / num;
            } else {
                total = count / num;
                totalPages = total + 1;
            }
            List<Integer> list2 = new LinkedList<>();
            list2.add(totalPages);
            map.put("总共的页数为", list2);
            int thePage = (pages - 1) * num;
            //使用listIn来存放分页查询数据
            for (int i = thePage; i < num; i++) {
                listIn.add(userList.get(i));
            }
            map.put("查询信息", listIn);
            List<Integer> listCounts = new LinkedList<>();
            listCounts.add(userList.size());
            map.put("总条数为", listCounts);
            return map;
        } else {
            List<Integer> integerList = new LinkedList<>();
            integerList.add(userList.size());
            map.put("总条数为", integerList);
            map.put("查询信息", userList);
            List<Integer> list2 = new LinkedList<>();
            list2.add(1);
            map.put("总共的页数为", list2);
            return map;
        }
    }
}
