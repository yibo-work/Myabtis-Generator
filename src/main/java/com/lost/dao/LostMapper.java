package com.lost.dao;

import com.lost.model.Lost;

import java.util.List;
import java.util.Map;


/**
 * 失物表
 * @author lwc
 * @email 212425333@qq.com
 * @date 2019-12-04 14:40:59
 */
public interface LostMapper {

    Lost findById(Long id);

    Lost findByMap(Map<String, Object> map);

    List<Lost> list(Map<String, Object> map);

    int save(Lost lost);

    int update(Lost lost);

    int delete(Long id);


}
