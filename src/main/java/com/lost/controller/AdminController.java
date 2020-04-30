package com.lost.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lost.model.ApplyRecord;
import com.lost.model.BaseUser;
import com.lost.model.Lost;
import com.lost.service.ApplyRecordService;
import com.lost.service.LostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Auther: liuweicheng
 * @Date: 2019-12-04 15:12
 */
@SuppressWarnings("all")
@Controller
public class AdminController {

    @Autowired
    LostService lostService;
    @Autowired
    ApplyRecordService applyRecordService;


    /**
     * 新增失物
     *
     * @param httpSession
     * @param model
     * @return
     */
    @RequestMapping("/admin/addLost")
    public String addBook(HttpSession httpSession, Model model,Integer page) {
        BaseUser baseUser = (BaseUser) httpSession.getAttribute("user");
        if (baseUser == null || baseUser.getRoleId() != 2) {
            return "login";
        }
        //查所有图书
        page = page == null ? 1 : page;
        PageHelper.startPage(page, 5);
        model.addAttribute("list", new PageInfo<>(lostService.list(null)));
        return "admin_addlost";
    }


    /**
     * 查看认领情况渲染
     */
    @RequestMapping("/admin/list.do")
    public String recordList(Model model, HttpSession httpSession, Integer page) {
        BaseUser baseUser = (BaseUser) httpSession.getAttribute("user");
        if (baseUser == null) {
            return "login";
        }
        Map<String, Object> where = new HashMap<>();
        //查询用户的认领记录
        page = page == null ? 1 : page;
        PageHelper.startPage(page, 10);
        List<ApplyRecord> list = applyRecordService.list(where);
        model.addAttribute("list", new PageInfo<>(list));

        return "admin_borrow";
    }

    @RequestMapping("/admin/ok.do/{id}")
    @Transactional
    public String ok(@PathVariable Long id, HttpSession httpSession) {
        BaseUser baseUser = (BaseUser) httpSession.getAttribute("user");
        if (baseUser == null) {
            return "login";
        }
        ApplyRecord applyRecord = applyRecordService.findById(id);
        applyRecord.setStatus(1);
        applyRecordService.update(applyRecord);

        //同时拒绝其他的人,对此失物的申请。
        Map<String, Object> where = new HashMap<>();
        where.put("lostId", applyRecord.getLostId());
        //查询用户的认领记录
        List<ApplyRecord> list = applyRecordService.list(where);
        for (ApplyRecord record : list) {
            if (!applyRecord.getId().equals(record.getId())) {
                record.setStatus(2);
                applyRecordService.update(record);
            }
        }

        //改变失物的状态
        Lost lost = lostService.findById(applyRecord.getLostId());
        lost.setStatus(1);
        lostService.update(lost);

        return "redirect:/admin/list.do";
    }

    @RequestMapping("/admin/refuse.do/{id}")
    public String refuse(@PathVariable Long id, HttpSession httpSession) {
        BaseUser baseUser = (BaseUser) httpSession.getAttribute("user");
        if (baseUser == null) {
            return "login";
        }
        ApplyRecord applyRecord = applyRecordService.findById(id);
        applyRecord.setStatus(2);
        applyRecordService.update(applyRecord);
        return "redirect:/admin/list.do";
    }
}
