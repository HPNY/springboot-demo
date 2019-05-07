package com.demo.controller;

import com.demo.entity.Awesome;
import com.demo.entity.User;
import com.demo.service.AwesomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AwesomeController {

    @Autowired
    private AwesomeService awesomeService;

    /**
     * 判断是否点赞
     *
     * @param articleId
     * @return
     */
    @RequestMapping("/reception/judgeAwesome")
    public boolean judgeAwesome(Integer articleId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return awesomeService.judgeAwesomeByUserIdAndArticleId(user.getId(), articleId);
    }

    /**
     * 统计点赞数
     *
     * @param articleId
     * @return
     */
    @RequestMapping("/reception/countAwesome")
    public Integer countAwesome(Integer articleId) {
        return awesomeService.count(articleId);
    }

    /**
     * 点赞
     *
     * @param awesome
     * @return
     */
    @RequestMapping("/reception/addAwesome")
    public String addAwesome(Awesome awesome) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        awesome.setUserId(user.getId());
        awesomeService.addLike(awesome);
        return "success";
    }

    /**
     * 取消点赞
     *
     * @param articleId
     * @return
     */
    @RequestMapping("/reception/deleteAwesome")
    public String deleteAwesome(Integer articleId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer id = awesomeService.findAwesomeByUserIdAndArticleId(user.getId(), articleId).getId();
        awesomeService.deleteLike(id);
        return "success";
    }
}
