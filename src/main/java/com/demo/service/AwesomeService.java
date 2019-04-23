package com.demo.service;

import com.demo.dao.AwesomeRepository;
import com.demo.entity.Awesome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AwesomeService {
    @Autowired
    private AwesomeRepository awesomeRepository;

    public Integer count(Integer articleId) {
        Integer size = awesomeRepository.findAllByArticleId(articleId).size();
        if (size == null) {
            size = 0;
        }
        return size;
    }

    public Awesome addLike(Awesome awesome) {
        return awesomeRepository.save(awesome);
    }

    public void deleteLike(Integer id) {
        awesomeRepository.deleteById(id);
    }

    public boolean judgeAwesomeByUserIdAndArticleId(Integer userId, Integer articleId) {
        if (awesomeRepository.findAwesomeByUserIdAndArticleId(userId, articleId) != null) {
            return true;
        }
        return false;
    }

    public Awesome findAwesomeByUserIdAndArticleId(Integer userId, Integer articleId) {
        return awesomeRepository.findAwesomeByUserIdAndArticleId(userId, articleId);
    }
}
