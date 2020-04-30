package com.lost.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lost.dao.CommentRecordMapper;
import com.lost.model.CommentRecord;
import com.lost.service.CommentRecordService;

@Service
public class CommentRecordServiceImpl implements CommentRecordService {
    @Autowired
    private CommentRecordMapper commentRecordMapper;

    @Override
    public CommentRecord findById(Long id) {
        return commentRecordMapper.findById(id);
    }

    @Override
    public CommentRecord findByMap(Map<String, Object> map) {
        return commentRecordMapper.findByMap(map);
    }

    @Override
    public List<CommentRecord> list(Map<String, Object> map) {
        return commentRecordMapper.list(map);
    }


    @Override
    public int save(CommentRecord commentRecord) {
        return commentRecordMapper.save(commentRecord);
    }

    @Override
    public int update(CommentRecord commentRecord) {
        return commentRecordMapper.update(commentRecord);
    }

    @Override
    public int delete(Long id) {
        return commentRecordMapper.delete(id);
    }



}
