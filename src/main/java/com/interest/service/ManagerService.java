package com.interest.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.interest.config.Response;
import com.interest.pojo.Manager;

/**
 * @author a1002
 */
public interface ManagerService extends IService<Manager> {
    Response login(String code,String password);
}
