package com.demo.dao;

import com.demo.entity.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Integer> {

    Page<Message> findAllByContentContaining(String content, Pageable pageable);
}
