package com.lost.model;


import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * 认领记录
 *
 * @author lwc
 * @email 212425333@qq.com
 * @date 2019-12-04 15:54:23
 */
@Getter
@Setter
public class ApplyRecord implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;	// 标识列

	private String lostNo;	// 失物编号

	private Long lostId;	// 失物id

	private Long userId;	// 用户id

	private String userName;	// 申请人

	private String userPhone;	// 手机号码

	private String lostName;	// 失物名

	private String description;	// 失物描述

	private Integer status;	// 申请状态 0.申请中 1同意 2.已拒绝

	private Long createTime;	// 创建时间

	private Integer isDel;	// 0未删除 1.删除


	public String getStatusString() {
		if(status == 0){
			return "申请中";
		}else if(status == 1){
			return "已同意";
		}else if(status ==2){
			return "拒绝";
		}

		return "";
	}

	public String getCreateTimeString() {
		return new SimpleDateFormat("yyyy-MM-dd").format(new Date(this.createTime*1000));
	}
}
