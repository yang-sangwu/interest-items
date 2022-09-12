package com.interest.service;

import com.interest.config.Response;
import com.interest.pojo.Interest;

/**
 * @author a1002
 */
public interface InterestService {
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

}
