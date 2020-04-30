package com.lost.model;


import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * 失物表
 *
 * @author lwc
 * @email 212425333@qq.com
 * @date 2019-12-04 14:40:59
 */
@Getter
@Setter
public class Lost implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;	// 标识列

	private String lostNo;	// 失物编号

	private String lostName;	// 失物名字

	private String address;	// 遗失地点

	private Integer status;	// 借阅状态 o.未认领 1。已认领

	private Long createTime;	// 创建时间

	private Integer isDel;	// 0未删除 1.删除

	public String getCreateTimeString() {
		return new SimpleDateFormat("yyyy-MM-dd").format(new Date(this.createTime*1000));
	}
	public void setCreateTime(String createTime) throws ParseException {
		long time = new SimpleDateFormat("yyyy-MM-dd").parse(createTime).getTime();
		this.createTime = time / 1000;
	}
	public void setCreateTime(Long createTime) throws ParseException {
		this.createTime = createTime;
	}

	public String getStatusString() {
		if (status == 0) {
			return "未认领";
		} else if (status == 1) {
			return "已认领";
		}
		return "默认";
	}
}
