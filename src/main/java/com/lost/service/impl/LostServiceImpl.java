package com.lost.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lost.dao.LostMapper;
import com.lost.model.Lost;
import com.lost.service.LostService;

@Service
public class LostServiceImpl implements LostService {
    @Autowired
    private LostMapper lostMapper;

    @Override
    public Lost findById(Long id) {
        return lostMapper.findById(id);
    }

    @Override
    public Lost findByMap(Map<String, Object> map) {
        return lostMapper.findByMap(map);
    }

    @Override
    public List<Lost> list(Map<String, Object> map) {
        return lostMapper.list(map);
    }


    @Override
    public int save(Lost lost) {
        return lostMapper.save(lost);
    }

    @Override
    public int update(Lost lost) {
        return lostMapper.update(lost);
    }

    @Override
    public int delete(Long id) {
        return lostMapper.delete(id);
    }



}
