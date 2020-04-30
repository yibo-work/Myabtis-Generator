package com.lost.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lost.model.BaseUser;
import com.lost.service.ApplyRecordService;
import com.lost.service.LostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class PageController {

    @Autowired
    LostService lostService;
    @Autowired
    ApplyRecordService applyRecordService;

    @RequestMapping("/login")
    public String index() {
        return "login";
    }


    /**
     * 失物认领页面渲染
     *
     * @param model
     * @return
     */
    @RequestMapping("/user/lostList.do")
    public String bookList(Model model, HttpSession httpSession, String lostName, Integer page) {
        BaseUser baseUser = (BaseUser) httpSession.getAttribute("user");
        if (baseUser == null) {
            return "login";
        }
        Map<String, Object> listWhere = new HashMap<>();
        listWhere.put("lostName", lostName);
        page = page == null ? 1 : page;
        PageHelper.startPage(page, 5);
        model.addAttribute("list", new PageInfo<>(lostService.list(listWhere)));
        return "user_lostlist";

    }

    /**
     * 首页
     *
     * @param httpSession
     * @return
     */
    @RequestMapping("/home.do")
    public String home(HttpSession httpSession) {
        BaseUser baseUser = (BaseUser) httpSession.getAttribute("user");
        if (baseUser == null) {
            return "login";
        }

        if (baseUser.getRoleId() == 4) {
            return "detail_admin";
        } else {
            return "detail_user";
        }
    }

    @RequestMapping("/register")
    public String register() {
        return "register";
    }


    public static void main(String[] args) {

    }
}
