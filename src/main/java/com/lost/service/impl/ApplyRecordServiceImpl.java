package com.lost.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lost.dao.ApplyRecordMapper;
import com.lost.model.ApplyRecord;
import com.lost.service.ApplyRecordService;

@Service
public class ApplyRecordServiceImpl implements ApplyRecordService {
    @Autowired
    private ApplyRecordMapper applyRecordMapper;

    @Override
    public ApplyRecord findById(Long id) {
        return applyRecordMapper.findById(id);
    }

    @Override
    public ApplyRecord findByMap(Map<String, Object> map) {
        return applyRecordMapper.findByMap(map);
    }

    @Override
    public List<ApplyRecord> list(Map<String, Object> map) {
        return applyRecordMapper.list(map);
    }


    @Override
    public int save(ApplyRecord applyRecord) {
        return applyRecordMapper.save(applyRecord);
    }

    @Override
    public int update(ApplyRecord applyRecord) {
        return applyRecordMapper.update(applyRecord);
    }

    @Override
    public int delete(Long id) {
        return applyRecordMapper.delete(id);
    }



}
