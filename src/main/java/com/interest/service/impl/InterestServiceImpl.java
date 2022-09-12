package com.interest.service.impl;

import com.interest.config.Response;
import com.interest.mapper.InterestMapper;
import com.interest.pojo.Interest;
import com.interest.service.InterestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author a1002
 */
@Service
public class InterestServiceImpl implements InterestService {
    @Autowired
    private InterestMapper interestMapper;

    @Override
    public int insertStudent(Interest interest) {
        return interestMapper.insert(interest);
    }

    @Override
    public void deleteStudent(Long id) {
        interestMapper.deleteById(id);
    }

    @Override
    public Response updateStudent(Interest interest) {
        Long id=interest.getId();
        Interest interest1=interestMapper.selectById(id);
        if(interest1 != null){
            interestMapper.updateById(interest);
            return Response.ok("success!");
        }else{
            return Response.error("failed!");
        }
    }
}
