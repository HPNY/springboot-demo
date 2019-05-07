package com.demo.controller;

import com.demo.entity.Comment;
import com.demo.entity.User;
import com.demo.service.CommentService;
import com.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    /**
     * 查询所有评论
     *
     * @param articleId
     * @param pageCount
     * @return
     */
    @RequestMapping("/reception/findAllComment")
    public Page<Comment> findAllComment(Integer articleId, Integer pageCount) {
        if (pageCount == null) {
            pageCount = 0;
        }
        return commentService.findAllComment(articleId, pageCount);
    }

    /**
     * 统计评论数
     *
     * @param articleId
     * @return
     */
    @RequestMapping("/reception/countComment")
    public Integer countComment(Integer articleId) {
        return commentService.conutComment(articleId);
    }

    /**
     * 新增评论
     *
     * @param comment
     * @return
     */
    @RequestMapping("/reception/addComment")
    public String addComment(Comment comment) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        comment.setUserId(user.getId());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        comment.setTime(sdf.format(new Date()));
        comment.setUsername(userService.findById(user.getId()).getUsername());
        commentService.addComment(comment);
        return "success";
    }

    /**
     * 删除评论
     *
     * @param comment
     * @return
     */
    @RequestMapping("/reception/deleteComment")
    public String deleteComment(Comment comment) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        comment.setUserId(user.getId());
        commentService.deleteComment(comment);
        return "success";
    }
}
