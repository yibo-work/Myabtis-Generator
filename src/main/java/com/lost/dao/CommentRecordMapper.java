package com.lost.dao;

import com.lost.model.CommentRecord;

import java.util.List;
import java.util.Map;


/**
 * 评论记录
 * @author lwc
 * @email 212425333@qq.com
 * @date 2020-03-16 15:07:16
 */
public interface CommentRecordMapper {

    CommentRecord findById(Long id);

    CommentRecord findByMap(Map<String, Object> map);

    List<CommentRecord> list(Map<String, Object> map);

    int save(CommentRecord commentRecord);

    int update(CommentRecord commentRecord);

    int delete(Long id);


}
