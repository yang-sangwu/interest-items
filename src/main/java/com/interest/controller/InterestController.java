package com.interest.controller;

import com.interest.config.Response;
import com.interest.pojo.Interest;
import com.interest.service.InterestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "play")
@RestController
@RequestMapping("/interest")
public class InterestController {
    @Resource
    private InterestService interestService;

    @ApiOperation(value = "添加学员")
    @PutMapping("/add")
    @ResponseBody
    public int insertStudent(Interest interest) {
        return interestService.insertStudent(interest);
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("/delete")
    @ResponseBody
    @ApiImplicitParam(name = "id", value = "id", required = true)
    public void deleteStudent(Long id) {
        interestService.deleteStudent(id);
    }

    @ApiOperation(value = "修改")
    @GetMapping("/update")
    @ResponseBody
    public Response updateStudent(Interest interest) {
        return interestService.updateStudent(interest);
    }

}