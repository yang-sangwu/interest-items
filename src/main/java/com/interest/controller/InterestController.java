package com.interest.controller;

import com.interest.config.Response;
import com.interest.pojo.Interest;
import com.interest.service.InterestService;
import com.interest.service.ManagerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.Map;

/**
 * @author a1002
 */
@Api(tags = "play")
@RestController
@RequestMapping("/interest")
public class InterestController {
    @Resource
    private InterestService interestService;

    @Autowired
    private ManagerService managerService;

    @ApiOperation(value = "添加学员")
    @PostMapping("/add")
    @ResponseBody
    public int insertStudent(String name,String sex,String birthday,String phone,String type,String course,String registerTime,String remaining,String score) {
        return interestService.insertStudent(name,sex,birthday,phone,type,course, registerTime, remaining, score);
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("/delete")
    @ResponseBody
    @ApiImplicitParam(name = "id", value = "id", required = true)
    public void deleteStudent(Long id) {
        interestService.deleteStudent(id);
    }

    @ApiOperation(value = "修改")
    @PutMapping("/update")
    @ResponseBody
    public Response updateStudent(Interest interest) {
        return interestService.updateStudent(interest);
    }

    @ApiOperation(value = "分页查询")
    @GetMapping("/queryInterestByPages")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pages", value = "第几页", required = true),
            @ApiImplicitParam(name = "num", value = "查询数量", required = true),
    })
    public Map queryInterestByPages(int pages, int num) {
        return interestService.queryInterestByPages(pages, num);
    }

    @ApiOperation(value = "模糊查询")
    @GetMapping("/queryInterestVague")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "thing", value = "模糊查询内容", required = true),
            @ApiImplicitParam(name = "pages", value = "第几页", required = true),
            @ApiImplicitParam(name = "num", value = "查询数量", required = true),
    })
    public Map queryInterestVague(String thing,int pages,int num) {
        return interestService.queryInterestVague(thing,pages,num);
    }

    @ApiOperation(value = "login")
    @GetMapping("/login")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "账号", required = true),
            @ApiImplicitParam(name = "password", value = "密码", required = true),
    })
    public Response login(String code,String password) {
        return managerService.login(code,password);
    }
}