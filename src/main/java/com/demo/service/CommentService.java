package com.demo.service;

import com.demo.dao.CommentRepository;
import com.demo.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    public static final int pageSize = 5;

    public static final String sortProperties = "id";

    @Autowired
    private CommentRepository commentRepository;

    public Page<Comment> findAllComment(Integer articleId, Integer pageCount) {
        Sort sort = new Sort(Sort.Direction.ASC, sortProperties);
        Pageable pageable = PageRequest.of(pageCount, pageSize, sort);
        return commentRepository.findAllByArticleId(pageable, articleId);
    }

    public Comment addComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public void deleteComment(Comment comment) {
        commentRepository.delete(comment);
    }

    public Integer conutComment(Integer articleId){
        return commentRepository.findAllByArticleId(articleId).size();
    }
}
