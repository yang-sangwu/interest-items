package com.interest.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.interest.pojo.Manager;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author a1002
 */
public interface ManagerService extends IService<Manager> {
    Map login(String code, String password);

    Map loginTest(HttpServletRequest request);
}
