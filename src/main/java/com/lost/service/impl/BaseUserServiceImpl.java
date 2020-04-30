package com.lost.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lost.dao.BaseUserMapper;
import com.lost.model.BaseUser;
import com.lost.service.BaseUserService;

@Service
public class BaseUserServiceImpl implements BaseUserService {
    @Autowired
    private BaseUserMapper baseUserMapper;

    @Override
    public BaseUser findById(Long id) {
        return baseUserMapper.findById(id);
    }

    @Override
    public BaseUser findByMap(Map<String, Object> map) {
        return baseUserMapper.findByMap(map);
    }

    @Override
    public List<BaseUser> list(Map<String, Object> map) {
        return baseUserMapper.list(map);
    }


    @Override
    public int save(BaseUser baseUser) {
        return baseUserMapper.save(baseUser);
    }

    @Override
    public int update(BaseUser baseUser) {
        return baseUserMapper.update(baseUser);
    }

    @Override
    public int delete(Long id) {
        return baseUserMapper.delete(id);
    }



}
