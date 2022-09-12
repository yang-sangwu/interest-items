package com.interest.controller;

import com.interest.config.Response;
import com.interest.service.ManagerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author a1002
 */
@Api(tags = "login")
@RestController
public class LoginController {
    @Autowired
    private ManagerService managerService;

    @ApiOperation(value = "login")
    @GetMapping("/login")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "账号", required = true),
            @ApiImplicitParam(name = "password", value = "密码", required = true),
    })
    @CrossOrigin
    public Response login(String code, String password) {
        return managerService.login(code,password);
    }
}
