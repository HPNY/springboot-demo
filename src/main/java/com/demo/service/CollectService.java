package com.demo.service;

import com.demo.dao.CollectRepository;
import com.demo.entity.Article;
import com.demo.entity.Collect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CollectService {

    @Autowired
    private CollectRepository collectRepository;

    public void addCollect(Collect collect) {
        collectRepository.save(collect);
    }

    public void deleteCollect(Collect collect) {
        collect=collectRepository.findCollectByUserIdAndArticleId(collect.getUserId(),collect.getArticleId());
        collectRepository.delete(collect);
    }



    public boolean findCollect(Integer userId,Integer articleId){
        if (collectRepository.findCollectByUserIdAndArticleId(userId,articleId)!=null){
            return true;
        }
        return false;
    }


}
