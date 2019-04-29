package com.demo.controller;

import com.demo.entity.Message;
import com.demo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    @Autowired
    private MessageService messageService;

    public String addMessage(Message message) {
        messageService.addMessage(message);
        return "success";
    }

    public String deleteMessage(Integer id) {
        messageService.deleteMessage(id);
        return "success";
    }

    public Page<Message> findAllMessage(Integer pageCount) {
        return messageService.findAllMessage(pageCount);
    }
}
