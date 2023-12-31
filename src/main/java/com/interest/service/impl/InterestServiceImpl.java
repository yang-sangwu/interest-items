package com.interest.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
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
    public int insertStudent(Interest interest) {
        interest.setCode(IDutils.getUUID(16));
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
        QueryWrapper<Interest>queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        List<Interest> userList = interestMapper.selectList(queryWrapper);
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
            map.put("totalPages", list2);
            int thePage = (pages - 1) * num;
            //使用listIn来存放分页查询数据
            for (int i = thePage; i < thePage+num && i < userList.size(); i++) {
                if(userList.get(i) != null){
                    listIn.add(userList.get(i));
                }
            }
            map.put("data", listIn);
            List<Integer> listCounts = new LinkedList<>();
            listCounts.add(userList.size());
            map.put("totals", listCounts);
            return map;
        } else {
            List<Integer> integerList = new LinkedList<>();
            integerList.add(userList.size());
            map.put("totals", integerList);
            map.put("data", userList);
            List<Integer> list2 = new LinkedList<>();
            list2.add(1);
            map.put("totalPages", list2);
            return map;
        }
    }

    @Override
    public Map queryInterestVague(String thing,int pages,int num) {
        QueryWrapper<Interest> wrapper = new QueryWrapper();
        wrapper.like("name", thing).or().like("sex",thing).or().like("birthday",thing).or().like("phone",thing).or().like("type",thing).or().like("score",thing).orderByDesc("id");
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
            map.put("totalPages", list2);
            int thePage = (pages - 1) * num;
            //使用listIn来存放分页查询数据
            for (int i = thePage; i < num+thePage && i < userList.size(); i++) {
                if(userList.get(i) != null){
                    listIn.add(userList.get(i));
                }
            }
            map.put("data", listIn);
            List<Integer> listCounts = new LinkedList<>();
            listCounts.add(userList.size());
            map.put("totals", listCounts);
            return map;
        } else {
            List<Integer> integerList = new LinkedList<>();
            integerList.add(userList.size());
            map.put("totals", integerList);
            map.put("data", userList);
            List<Integer> list2 = new LinkedList<>();
            list2.add(1);
            map.put("totalPages", list2);
            return map;
        }
    }

    @Override
    public Response updateScore(String score,long id) {
        Interest interest=interestMapper.selectById(id);
        UpdateWrapper<Interest> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("score",score).eq("id",id);
        int result = interestMapper.update(interest,updateWrapper);
        return Response.ok(result);
    }

    @Override
    public Interest findUserById(long id) {
        return interestMapper.selectById(id);
    }

    @Override
    public int delete(List<Long> list) {
        for(long l:list) {
            interestMapper.deleteById(l);
        }
        return 1;
    }
}
