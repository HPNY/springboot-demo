package com.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("/")
    public String root() {
        return "redirect:/index";
    }

    @RequestMapping("/login")
    public String login() {
        return "/login";
    }

    @RequestMapping("/index")
    public String index() {
        return "/index";
    }

    @RequestMapping("/register")
    public String register() {
        return "/register";
    }

    @RequestMapping("/reception/login")
    public String loginReception() {
        return "/reception/login";
    }

    @RequestMapping("/reception/register")
    public String registerReception() {
        return "/reception/register";
    }

    @RequestMapping("/reception/article_add")
    public String add() {
        return "/reception/article_add";
    }


    @RequestMapping("/reception/findById")
    public String findById(Integer id, Model model) {
        model.addAttribute("id", id);
        return "/reception/article_detail";
    }

    @RequestMapping("/reception/update")
    public String update(Integer id, Model model) {
        model.addAttribute("id", id);
        return "/reception/article_update";
    }

    @RequestMapping("/reception/index")
    public String findAll() {
        return "/reception/index";
    }

    @RequestMapping("/reception/personal")
    public String personal() {
        return "/reception/personal";
    }

    @RequestMapping("/reception/favorite")
    public String favorite() {
        return "/reception/favorite";
    }


    @RequestMapping("/reception/myself")
    public String myself() {
        return "/reception/myself";
    }

    @RequestMapping("/role/findone")
    public String findOneRole(Integer id, Model model) {
        model.addAttribute("id", id);
        return "/role/role_update";
    }

    @RequestMapping("/role/role_add")
    public String addPage() {
        return "/role/role_add";
    }

    @RequestMapping("/role/role_list")
    public String roleList() {
        return "/role/role_list";
    }

    @RequestMapping("/user/findone")
    public String findoneUser(Integer id, Model model) {
        model.addAttribute("id", id);
        return "/user/user_update";
    }

    @RequestMapping("/user/user_list")
    public String findAllUser() {
        return "/user/user_list";
    }

    @RequestMapping("/user/role")
    public String role(Integer id, Model model) {
        model.addAttribute("id", id);
        return "/user_role";
    }

    @RequestMapping("/user/userAddAdmin")
    public String userAddAdmin() {
        return "/user/user_add";
    }

    @RequestMapping("/dictionary/dictionary_list")
    public String dictionaryList() {
        return "/dictionary/dictionary_list";
    }

    @RequestMapping("/reception/message")
    public String Message() {
        return "/reception/message";
    }

    @RequestMapping("/reception/awesome")
    public String awesome() {
        return "/reception/awesome";
    }

    @RequestMapping("/message/message_list")
    public String message() {
        return "/message/message_list";
    }

    @RequestMapping("/article/article_list")
    public String article() {
        return "/article/article_list";
    }

}
