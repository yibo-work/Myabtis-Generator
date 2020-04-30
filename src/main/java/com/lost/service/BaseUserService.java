package com.lost.service;

import com.lost.model.BaseUser;

import java.util.List;
import java.util.Map;

/**
 * 用户表
 * 
 * @author lwc
 * @email 212425333@qq.com
 * @date 2019-12-04 14:40:59
 */
public interface BaseUserService {
	
	BaseUser findById(Long id);

	BaseUser findByMap(Map<String, Object> map);
	
	List<BaseUser> list(Map<String, Object> map);
	
	int save(BaseUser baseUser);
	
	int update(BaseUser baseUser);

	int delete(Long id);

}
