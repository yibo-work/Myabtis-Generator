package com.lost.model;


import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * 评论记录
 *
 * @author lwc
 * @email 212425333@qq.com
 * @date 2020-03-16 15:07:16
 */
@Getter
@Setter
public class CommentRecord implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;    // 标识列

    private String userName;    // 用户Id

    private Long userId;    // 用户名

    private String content;    // 内容

    private Long createTime;    // 创建时间

    private Integer isDel;    // 0未删除 1.删除


    private String date;

    public String getDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(new Date(this.createTime));
    }
}
