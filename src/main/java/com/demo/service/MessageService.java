package com.demo.service;

import com.demo.dao.MessageRepository;
import com.demo.dao.UserRepository;
import com.demo.entity.Message;
import com.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    public static final int pageSize = 10;

    public static final String sortProperties = "id";

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    public Message addMessage(Message message,Integer userId) {
        User user=userRepository.findById(userId).orElse(null);
        message.setUserId(user.getId());
        message.setUsername(user.getUsername());
        return messageRepository.save(message);
    }

    public void deleteMessage(Integer id) {
        messageRepository.deleteById(id);
    }

    public Page<Message> findAllMessage(Integer pageCount) {
        Sort sort = new Sort(Sort.Direction.ASC, sortProperties);
        Pageable pageable = PageRequest.of(pageCount, pageSize, sort);
        return messageRepository.findAll(pageable);
    }

    public Page<Message> findAllByContentContaining(String content, Integer pageCount) {
        Sort sort = new Sort(Sort.Direction.ASC, sortProperties);
        Pageable pageable = PageRequest.of(pageCount, pageSize, sort);
        return messageRepository.findAllByContentContaining(content, pageable);
    }
}
