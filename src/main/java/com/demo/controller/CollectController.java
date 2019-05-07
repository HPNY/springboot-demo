package com.demo.controller;

import com.demo.entity.Collect;
import com.demo.entity.User;
import com.demo.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CollectController {

    @Autowired
    private CollectService collectService;

    /**
     * 新增收藏
     *
     * @param collect
     * @return
     */
    @RequestMapping("/reception/addCollect")
    public String addCollect(Collect collect) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        collect.setUserId(user.getId());
        collectService.addCollect(collect);
        return "success";
    }

    /**
     * 取消收藏
     *
     * @param collect
     * @return
     */
    @RequestMapping("/reception/deleteCollect")
    public String deleteCollect(Collect collect) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        collect.setUserId(user.getId());
        collectService.deleteCollect(collect);
        return "success";
    }

    /**
     * 判断是否收藏
     *
     * @param articleId
     * @return
     */
    @RequestMapping("/reception/findCollect")
    public boolean findCollect(Integer articleId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return collectService.findCollect(user.getId(), articleId);
    }
}
