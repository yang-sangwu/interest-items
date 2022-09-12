package com.interest.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.interest.config.Response;
import com.interest.mapper.ManagerMapper;
import com.interest.pojo.Manager;
import com.interest.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author a1002
 */
@Service
public class ManagerServiceImpl extends ServiceImpl<ManagerMapper, Manager> implements ManagerService {
    @Autowired
    private ManagerMapper managerMapper;

    @Override
    public Response login(String code, String password) {
        QueryWrapper<Manager> wrapper = Wrappers.query();
        wrapper.in("code", code).last("limit 1");
        List<Manager> list = managerMapper.selectList(wrapper);
        Manager manager=list.get(0);
        if(manager.getPassword().equals(password)){
            return Response.ok("success!");
        }else{
            return Response.error("failed!");
        }
    }
}
