package com.demo.controller;

import com.demo.entity.*;
import com.demo.service.ArticleService;
import com.demo.service.UserService;
import org.apache.commons.lang3.StringUtils;
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

    /**
     * 根据ID查找文章
     *
     * @param id
     * @return
     */
    @RequestMapping("/reception/findArticle")
    public Article findArticle(Integer id) {
        Article article = articleService.findById(id);
        return article;
    }

    /**
     * 删除文章
     *
     * @param id
     * @return
     */
    @RequestMapping("/reception/deleteArticle")
    public String deleteArticle(Integer id) {
        articleService.deleteArticle(id);
        return "success";
    }

    /**
     * 添加新文章
     *
     * @param article
     * @return
     */
    @RequestMapping("/reception/addArticle")
    public String addArticle(Article article) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String author = user.getUsername();
        article.setAuthor(author);
        articleService.addArticle(article);
        return "success";
    }

    /**
     * 更新文章内容
     *
     * @param article
     * @return
     */
    @RequestMapping("/reception/updateArticle")
    public String updateArticle(Article article) {
        articleService.updateArticle(article);
        return "success";
    }

    /**
     * 根据分类分页显示文章
     *
     * @param category
     * @param pageCount
     * @return
     */
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

    /**
     * 模糊查询标题
     *
     * @param pageCount
     * @param title
     * @param category
     * @return
     */
    @RequestMapping("/reception/Containing")
    public Page<Article> findAllByTitleContaining(Integer pageCount, String title, String category) {
        Page<Article> page;
        if (category.equals("全部分类"))
            page = articleService.findAllByTitleContaining(title, pageCount);
        else
            page = articleService.findAllByCategoryAndTitleContaining(pageCount, category, title);
        return page;
    }

    /**
     * 查询所有文章
     *
     * @param pageCount
     * @param title
     * @param category
     * @return
     */
    @RequestMapping("/reception/findall")
    public Page<Article> findall(Integer pageCount, String title, String category) {
        Page<Article> page;
        if (StringUtils.isBlank(title)) {
            if (category.equals("全部分类") || category == null)
                page = articleService.findAllArticle(pageCount);
            else
                page = articleService.findAllByCategory(category, pageCount);
        } else if (category.equals("全部分类") || category == null) {
            page = articleService.findAllByTitleContaining(title, pageCount);
        } else {
            page = articleService.findAllByCategoryAndTitleContaining(pageCount, title, category);
        }
        return page;
    }

    /**
     * 判断是否能修改
     *
     * @param articleId
     * @return
     */
    @RequestMapping("/reception/judgeChange")
    public boolean judgeChange(Integer articleId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getUsername().equals(articleService.findById(articleId).getAuthor());
    }

    /**
     * 显示自己上传的文章
     *
     * @return
     */
    @RequestMapping("/reception/myArticle")
    public List<Article> findAllByAuthor() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return articleService.findAllByAuthor(userService.findById(user.getId()).getUsername());
    }

    /**
     * 显示自己收藏的文章
     *
     * @return
     */
    @RequestMapping("/reception/findAllCollect")
    public List<Article> findAllCollect() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Article> list = articleService.findCollectArticle(user.getId());
        return list;
    }

    /**
     * 显示自己点赞的文章
     *
     * @return
     */
    @RequestMapping("/reception/findAllAwesome")
    public List<Article> findAllAwesome() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Article> list = articleService.findAllAwesomeArticle(user.getId());
        return list;
    }

}


