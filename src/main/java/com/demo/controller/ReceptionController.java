package com.demo.controller;

import com.demo.entity.*;
import com.demo.service.ArticleService;
import com.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReceptionController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserService userService;


    @RequestMapping("/reception/findArticle")
    public Article findArticle(Integer id) {
        Article article = articleService.findById(id);
        return article;
    }

    @RequestMapping("/reception/deleteArticle")
    public String deleteArticle(Integer id) {
        articleService.deleteArticle(id);
        return "success";
    }

    @RequestMapping("/reception/addArticle")
    public String addArticle(Article article) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String author = user.getUsername();
        article.setAuthor(author);
        articleService.addArticle(article);
        return "success";
    }

    @RequestMapping("/reception/updateArticle")
    public String updateArticle(Article article) {
        articleService.updateArticle(article);
        return "success";
    }

    @RequestMapping("/reception/findAllByCategory")
    public Page findAllByCategory(String category, Integer pageCount) {
        Page<Article> page;
        if (pageCount == null) {
            pageCount = 0;
        }
        if (category.equals("全部分类")) {
            page = articleService.findAllArticle(pageCount);
        } else
            page = articleService.findAllByCategory(category, pageCount);
        return page;
    }


    @RequestMapping("/reception/findAllByTitle")
    public String findAllByTitle(String title, Integer pageCount) {
        if (pageCount == null) {
            pageCount = 0;
        }
        Page<Article> page = articleService.findAllByTitleContaining(title, pageCount);
        return "success";
    }

    @RequestMapping("/reception/Containing")
    public Page<Article> findAllByTitleContaining(Integer pageCount, String title, String category) {
        Page<Article> page;
        if (category.equals("全部分类"))
            page = articleService.findAllByTitleContaining(title, pageCount);
        else
            page = articleService.findAllByCategoryAndTitleContaining(pageCount, category, title);
        return page;
    }

    @RequestMapping("/reception/findall")
    public Page<Article> findall(Integer pageCount, String title, String category) {
        Page<Article> page;
        if (title == null || title.equals("")) {
            if (category.equals("全部分类"))
                page = articleService.findAllArticle(pageCount);
            else
                page = articleService.findAllByCategory(category, pageCount);
        } else if (category.equals("全部分类")) {
            page = articleService.findAllByTitleContaining(title, pageCount);
        } else {
            page = articleService.findAllByCategoryAndTitleContaining(pageCount, title, category);
        }
        return page;
    }

    @RequestMapping("/reception/judgeChange")
    public boolean judgeChange(Integer articleId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getUsername().equals(articleService.findById(articleId).getAuthor());
    }

    @RequestMapping("/reception/myArticle")
    public List<Article> findAllByAuthor() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return articleService.findAllByAuthor(userService.findById(user.getId()).getUsername());
    }

    @RequestMapping("/reception/findAllCollect")
    public List<Article> findAllCollect() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Article> list = articleService.findCollectArticle(user.getId());
        return list;
    }

    @RequestMapping("/reception/findAllAwesome")
    public List<Article> findAllAwesome() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Article> list = articleService.findAllAwesomeArticle(user.getId());
        return list;
    }

    @RequestMapping("/article/list")
    public Page<Article> findAllArticle(String title, Integer pageCount) {
        if (title == null) {
            return articleService.backstageFindAllArticle(pageCount);
        }
        return articleService.backstageFindAllByTitleContaining(title, pageCount);
    }

    @RequestMapping("/article/delete")
    public String articleDelete(Integer id) {
        articleService.deleteArticle(id);
        return "success";
    }

}


