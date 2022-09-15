package com.interest.controller;

import com.interest.config.Response;
import com.interest.pojo.Interest;
import com.interest.service.InterestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "添加学员")
    @PostMapping("/add")
    @ResponseBody
    @CrossOrigin
//    public int insertStudent(String name,String sex,String birthday,String phone,String type,String course,String registerTime,String remaining,String score) {
    public int insertStudent(@RequestBody Interest interest){
        String name = interest.getName();
        String sex = interest.getSex();
        String birthday = interest.getBirthday();
        String phone = interest.getPhone();
        String type = interest.getType();
        String course = interest.getCourse();
        String registerTime = interest.getRegisterTime();
        String remaining = interest.getRemaining();
        String score = interest.getScore();
        return interestService.insertStudent(name,sex,birthday,phone,type,course, registerTime, remaining, score);
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("/delete")
    @ResponseBody
    @CrossOrigin
    @ApiImplicitParam(name = "id", value = "id", required = true)
    public void deleteStudent(Long id) {
        interestService.deleteStudent(id);
    }

    @ApiOperation(value = "修改")
    @PutMapping("/update")
    @ResponseBody
    @CrossOrigin
    public Response updateStudent(Interest interest) {
        return interestService.updateStudent(interest);
    }

    @ApiOperation(value = "分页查询")
    @GetMapping("/queryInterestByPages")
    @ResponseBody
    @CrossOrigin
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pages", value = "第几页", required = true),
            @ApiImplicitParam(name = "num", value = "查询数量", required = true),
    })
    public Map queryInterestByPages(@RequestParam(value = "pages") int pages,  @RequestParam(value = "num")int num) {
        return interestService.queryInterestByPages(pages, num);
    }

    @ApiOperation(value = "模糊查询")
    @GetMapping("/queryInterestVague")
    @ResponseBody
    @CrossOrigin
    @ApiImplicitParams({
            @ApiImplicitParam(name = "thing", value = "模糊查询内容", required = true),
            @ApiImplicitParam(name = "pages", value = "第几页", required = true),
            @ApiImplicitParam(name = "num", value = "查询数量", required = true),
    })
    public Map queryInterestVague(@RequestParam(value = "thing") String thing,@RequestParam(value = "pages")int pages,@RequestParam(value = "num")int num) {
        return interestService.queryInterestVague(thing,pages,num);
    }

    @ApiOperation(value = "添加学分")
    @PutMapping("/addScore")
    @ResponseBody
    @CrossOrigin
    @ApiImplicitParams({
            @ApiImplicitParam(name = "score", value = "学分", required = true),
            @ApiImplicitParam(name = "id", value = "id", required = true),
    })
    public Response updateScore(String score,int id) {
        return interestService.updateScore(score,id);
    }
}