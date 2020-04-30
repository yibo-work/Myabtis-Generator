package com.lost.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lost.core.ApiResponse;
import com.lost.generator.CalendarUtil;
import com.lost.model.BaseUser;
import com.lost.model.CommentRecord;
import com.lost.service.CommentRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @Description:
 * @Auther: liuweicheng
 * @Date: 2020-03-16 14:28
 */
@SuppressWarnings("all")
@RequestMapping("/comment")
@RestController
public class CommentController {
    @Autowired
    CommentRecordService commentRecordService;

    @RequestMapping("/commit.do")
    public ApiResponse commimt(HttpSession httpSession, String content) {
        CommentRecord commentRecord = new CommentRecord();
        BaseUser baseUser = (BaseUser) httpSession.getAttribute("user");
        commentRecord.setCreateTime(CalendarUtil.getCurrMillis());
        commentRecord.setUserId(baseUser.getId());
        commentRecord.setContent(content);
        commentRecord.setUserName(baseUser.getNickname());
        commentRecordService.save(commentRecord);

        return ApiResponse.getDefaultResponse();
    }


    @RequestMapping("/list.do")
    public ApiResponse list(HttpSession httpSession, Integer page) {
        page = page == null ? 1 : page;
        PageHelper.startPage(page, 5);
        return ApiResponse.getDefaultResponse(new PageInfo<>(commentRecordService.list(null)));
    }

}
