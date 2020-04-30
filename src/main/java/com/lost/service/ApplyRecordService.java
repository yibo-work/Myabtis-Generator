package com.lost.service;

import com.lost.model.ApplyRecord;

import java.util.List;
import java.util.Map;

/**
 * 认领记录
 * 
 * @author lwc
 * @email 212425333@qq.com
 * @date 2019-12-04 14:40:59
 */
public interface ApplyRecordService {
	
	ApplyRecord findById(Long id);

	ApplyRecord findByMap(Map<String, Object> map);
	
	List<ApplyRecord> list(Map<String, Object> map);
	
	int save(ApplyRecord applyRecord);
	
	int update(ApplyRecord applyRecord);

	int delete(Long id);

}
