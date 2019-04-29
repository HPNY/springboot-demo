package com.demo.service;

import com.demo.dao.ArticleRepository;
import com.demo.dao.AwesomeRepository;
import com.demo.dao.CollectRepository;
import com.demo.entity.Article;
import com.demo.entity.Awesome;
import com.demo.entity.Collect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ArticleService {

    public static final int pageSize = 5;

    public static final String sortProperties = "id";

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CollectRepository collectRepository;

    @Autowired
    private AwesomeRepository awesomeRepository;


    public Article addArticle(Article article) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        article.setCreateTime(sdf.format(date));
        return articleRepository.save(article);
    }

    public Article updateArticle(Article article) {
        article.setAuthor(articleRepository.findById(article.getId()).orElse(null).getAuthor());
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        article.setCreateTime(sdf.format(date));
        return articleRepository.save(article);
    }

    public void deleteArticle(Integer id) {
        articleRepository.deleteById(id);
        List<Collect> list=collectRepository.findAllByArticleId(id);
        for (Collect collect:list){
            collectRepository.delete(collect);
        }
    }

    public Page<Article> findAllArticle(Integer pageCount) {
        Sort sort = new Sort(Sort.Direction.ASC, sortProperties);
        Pageable pageable = PageRequest.of(pageCount, pageSize, sort);
        return articleRepository.findAll(pageable);
    }

    public Page<Article> findAllByTitleContaining(String title, Integer pageCount) {
        Sort sort = new Sort(Sort.Direction.ASC, sortProperties);
        Pageable pageable = PageRequest.of(pageCount, pageSize, sort);
        return articleRepository.findAllByTitleContaining(pageable, title);
    }

    public Article findById(Integer id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Page<Article> findAllByCategory(String category, Integer pageCount) {
        Sort sort = new Sort(Sort.Direction.ASC, sortProperties);
        Pageable pageable = PageRequest.of(pageCount, pageSize, sort);
        return articleRepository.findAllByCategory(pageable, category);
    }

    public Page<Article> findAllByCategoryAndTitleContaining(Integer pageCount, String category, String title) {
        Sort sort = new Sort(Sort.Direction.ASC, sortProperties);
        Pageable pageable = PageRequest.of(pageCount, pageSize, sort);
        return articleRepository.findAllByCategoryAndTitleContaining(pageable, category, title);
    }

    public List<Article> findAllByAuthor(String author){
        return articleRepository.findAllByAuthor(author);
    }

    public List<Article> findCollectArticle(Integer userId){
        List<Collect> list=collectRepository.findAllByUserId(userId);
        List<Integer> ArticleId=new ArrayList<>();
        for (Collect collect:list){
            ArticleId.add(collect.getArticleId());
        }
        List<Article> articleList=new ArrayList<>();
        for (Integer temp:ArticleId){
            articleList.add(articleRepository.findById(temp).orElse(null));
        }
        return articleList;
    }

    public List<Article> findAllAwesomeArticle(Integer userId){
        List<Awesome> list=awesomeRepository.findAllByUserId(userId);
        List<Integer> ArticleId=new ArrayList<>();
        for (Awesome awesome:list){
            ArticleId.add(awesome.getArticleId());
        }
        List<Article> articleList=new ArrayList<>();
        for (Integer temp:ArticleId){
            articleList.add(articleRepository.findById(temp).orElse(null));
        }
        return articleList;
    }
}
