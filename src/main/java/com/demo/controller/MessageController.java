package com.demo.controller;

import com.demo.entity.Message;
import com.demo.entity.User;
import com.demo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    @Autowired
    private MessageService messageService;

    /**
     * 新增留言
     *
     * @param message
     * @return
     */
    @RequestMapping("/message/add")
    public String addMessage(Message message) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        messageService.addMessage(message, user.getId());
        return "success";
    }

    /**
     * 后台删除留言
     *
     * @param id
     * @return
     */
    @RequestMapping("/message/delete")
    public String deleteMessage(Integer id) {
        messageService.deleteMessage(id);
        return "success";
    }

    /**
     * 显示所有留言
     *
     * @param content
     * @param pageCount
     * @return
     */
    @RequestMapping("/message/list")
    public Page<Message> findAllMessage(String content, Integer pageCount) {
        if (content == null) {
            return messageService.findAllMessage(pageCount);
        }
        return messageService.findAllByContentContaining(content, pageCount);
    }
}
