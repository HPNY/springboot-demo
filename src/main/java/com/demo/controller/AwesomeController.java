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

    @RequestMapping("/reception/judgeAwesome")
    public boolean judgeAwesome(Integer articleId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return awesomeService.judgeAwesomeByUserIdAndArticleId(user.getId(), articleId);
    }

    @RequestMapping("/reception/countAwesome")
    public Integer countAwesome(Integer articleId) {
        return awesomeService.count(articleId);
    }

    @RequestMapping("/reception/addAwesome")
    public String addAwesome(Awesome awesome) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        awesome.setUserId(user.getId());
        awesomeService.addLike(awesome);
        return "success";
    }

    @RequestMapping("/reception/deleteAwesome")
    public String deleteAwesome(Integer articleId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer id = awesomeService.findAwesomeByUserIdAndArticleId(user.getId(), articleId).getId();
        awesomeService.deleteLike(id);
        return "success";
    }
}
