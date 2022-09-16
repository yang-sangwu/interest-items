package com.interest.controller;

import com.interest.service.ManagerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author a1002
 */
@Api(tags = "login")
@RestController
public class LoginController {
    @Autowired
    private ManagerService managerService;

    @ApiOperation(value = "login")
    @PostMapping("/login")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "账号", required = true),
            @ApiImplicitParam(name = "password", value = "密码", required = true),
    })
    @CrossOrigin
    public Map login(@RequestParam("code") String code, @RequestParam("password") String password) {
        return managerService.login(code,password);
    }

    @ApiOperation(value = "loginTest")
    @PostMapping("/loginTest")
    @ResponseBody
    @CrossOrigin
    public Map loginTest(HttpServletRequest request) {
        return managerService.loginTest(request);
    }
}
