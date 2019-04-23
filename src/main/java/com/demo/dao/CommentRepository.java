package com.demo.dao;

import com.demo.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    Page<Comment> findAllByArticleId(Pageable pageable, Integer articleId);

    List findAllByArticleId(Integer articleId);
}
