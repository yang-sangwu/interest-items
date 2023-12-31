package com.interest.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.interest.config.Response;
import com.interest.pojo.Interest;

import java.util.List;
import java.util.Map;

/**
 * @author a1002
 */
public interface InterestService extends IService<Interest> {
    /**
     * fetch data by rule id
     * @param interest rule id
     * @return Result<XxixDO>
     */
    int insertStudent(Interest interest);

    /**
     * fetch data by rule id
     * @param id rule id
     * @return Result<XxixDO>
     */
    void deleteStudent(Long id);

    /**
     * fetch data by rule id
     * @param interest rule id
     * @return Result<XxixDO>
     */
    Response updateStudent(Interest interest);

    /**
     * fetch data by rule id
     * @param pages rule id
     * @return Result<XxixDO>
     */
    Map queryInterestByPages(int pages, int num);

    Map queryInterestVague(String thing,int pages,int num);

    Response updateScore(String score,long id);

    Interest findUserById(long id);

    int delete(List<Long> list);
}
