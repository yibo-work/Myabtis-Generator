package com.lost.controller;

import com.lost.core.ApiResponse;
import com.lost.model.*;
import com.lost.service.ApplyRecordService;
import com.lost.service.LostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/lost")
@Controller
public class LostController {
    @Autowired
    LostService lostService;
    @Autowired
    ApplyRecordService applyRecordService;

    @PostMapping("/add.do")
    @ResponseBody
    /**
     * 上传失物
     */
    public ApiResponse add(@RequestBody Lost lost) {
        Map<String, Object> where = new HashMap<>();
        where.put("lostNo", lost.getLostNo());
        Lost lost1 = lostService.findByMap(where);
        if (lost1 != null) {
            return ApiResponse.getDefaultResponse("失物编号重复");
        }
        lost.setStatus(0);
        lostService.save(lost);
        return ApiResponse.getDefaultResponse("添加成功");
    }

    @GetMapping("/{id}/applyRecord.do")
    public String borrowing(HttpSession httpSession, @PathVariable Long id) {
        BaseUser baseUser = (BaseUser) httpSession.getAttribute("user");
        if (baseUser == null) {
            return "login";
        }
        Lost lost = lostService.findById(id);

        ApplyRecord applyRecord = new ApplyRecord();
        applyRecord.setLostId(lost.getId());
        applyRecord.setLostNo(lost.getLostNo());
        applyRecord.setCreateTime(System.currentTimeMillis() / 1000);
        applyRecord.setStatus(1);
        applyRecord.setUserId(baseUser.getId());
        applyRecord.setLostName(lost.getLostName());
        applyRecord.setUserName(baseUser.getNickname());

        applyRecordService.save(applyRecord);

        return "redirect:/user/lostList.do";
    }

}
