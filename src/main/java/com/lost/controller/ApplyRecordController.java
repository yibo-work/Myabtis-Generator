package com.lost.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lost.core.ApiResponse;
import com.lost.model.*;
import com.lost.service.ApplyRecordService;
import com.lost.service.CommentRecordService;
import com.lost.service.LostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/applyRecord")
@Controller
public class ApplyRecordController {
    @Autowired
    ApplyRecordService applyRecordService;
    @Autowired
    LostService lostService;
    @Autowired
    CommentRecordService commentRecordService;


    /**
     * 查看认领情况渲染
     */
    @RequestMapping("/user/list.do")
    public String recordList(Model model, HttpSession httpSession, Integer page) {
        BaseUser baseUser = (BaseUser) httpSession.getAttribute("user");
        if (baseUser == null) {
            return "login";
        }
        Map<String, Object> where = new HashMap<>();
        //查询用户的认领记录
        where.put("userId", baseUser.getId());

        page = page == null ? 1 : page;
        PageHelper.startPage(page, 5);
        List<ApplyRecord> list = applyRecordService.list(where);
        model.addAttribute("list", new PageInfo<>(list));

        return "user_borrow";
    }

    @RequestMapping("/user/add.do")
    @ResponseBody
    public ApiResponse addRecord(@RequestBody ApplyRecord req, HttpSession httpSession) {
        BaseUser baseUser = (BaseUser) httpSession.getAttribute("user");
        if (baseUser == null) {
            return ApiResponse.getDefaultResponse("登陆会话超时");
        }

        Lost lost = lostService.findById(req.getLostId());

        ApplyRecord applyRecord = new ApplyRecord();
        applyRecord.setLostId(lost.getId());
        applyRecord.setLostNo(lost.getLostNo());
        applyRecord.setCreateTime(System.currentTimeMillis() / 1000);
        applyRecord.setStatus(0);
        applyRecord.setUserId(baseUser.getId());
        applyRecord.setUserPhone(req.getUserPhone());
        applyRecord.setUserId(baseUser.getId());
        applyRecord.setLostName(lost.getLostName());
        applyRecord.setUserName(baseUser.getNickname());
        applyRecord.setDescription(req.getDescription());

        if (applyRecordService.save(applyRecord) == 1) {
            return ApiResponse.getDefaultResponse("添加成功");
        } else {
            return ApiResponse.getDefaultResponse("添加失败");
        }

    }

}
