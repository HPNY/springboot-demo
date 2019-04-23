package com.demo.controller;

import com.demo.entity.User;
import com.demo.service.RoleService;
import com.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 创建普通用户
     *
     * @param user
     * @return
     */
    @RequestMapping("/user/user_add")
    public String addUser(User user) {
        user = userService.addUser(user);
        if (user == null) {
            return "userExist";
        }
        return "success";
    }

    /**
     * 密码重置123456
     *
     * @param id
     * @return
     */
    @RequestMapping("/user/resetPassword")
    public String resetPassword(Integer id) {
        userService.resetPassword(id);
        return "success";
    }


    /**
     * 添加管理员用户
     *
     * @param user
     * @param role
     * @return
     */
    @RequestMapping("/user/user_admin")
    public String userAdmin(User user, String[] role) {
        user = userService.addAdmin(user, role);
        return "success";
    }

    /**
     * 查询单个用户
     *
     * @param id
     * @return
     */
    @RequestMapping("/user/user_findoneuser")
    public User findOneUser(Integer id) {
        return userService.findById(id);
    }

    /**
     * 更新用户
     *
     * @param user
     * @return
     */
    @RequestMapping("/user/user_update")
    public String updateUser(User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        user = userService.findById(user.getId());
        user.setUsername(username);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        userService.updateUser(user);
        return "success";
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @RequestMapping("/user/delete")
    public String delUser(Integer id) {
        userService.delUser(id);
        return "success";
    }

    /**
     * 分页显示用户，有查询条件就查询分页
     *
     * @param pageCount
     * @param containing
     * @return
     */
    @RequestMapping("/user/list")
    public Page findAll(Integer pageCount, String containing) {
        Page page;
        if (containing == null) {
            page = userService.findAllUser(pageCount);
        } else {
            page = userService.findAllByUsernameContaining(containing, pageCount);
        }
        return page;
    }

    /**
     * 返回用户权限信息
     *
     * @param id
     * @return
     */
    @RequestMapping("/user/user_role")
    public Collection selectRole(Integer id) {
        Collection collection = userService.findById(id).getAuthorities();
        return collection;
    }

    /**
     * 获取当前登录用户ID
     *
     * @return
     */
    @RequestMapping("/user/getuser")
    public Integer getUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getId();
    }

    /**
     * 分页跳转
     * @param pageCount
     * @param username
     * @return
     */
    @RequestMapping("/user/containing")
    public Page<User> findAllByUsernameContaining(Integer pageCount, String username) {
        if (pageCount == null) {
            pageCount = 0;
        }
        Page<User> page = userService.findAllByUsernameContaining(username, pageCount);
        return page;
    }

    /**
     * 更新用户权限
     *
     * @param role
     * @param idNo
     * @return
     */
    @RequestMapping("/user/user")
    public String updateRole(String[] role, Integer idNo) {
        User user = userService.findById(idNo);
        List list = new ArrayList();
        for (String temp : role) {
            Integer id = Integer.parseInt(temp);
            list.add(roleService.findById(id));
        }
        user.setAuthorities(list);
        userService.updateUser(user);
        return "success";
    }

    /**
     * 用户更新个人信息
     *
     * @param id
     * @param nickname
     * @param actualName
     * @param sex
     * @param year
     * @param month
     * @param day
     * @param introduction
     * @return
     */
    @RequestMapping("/reception/changeInformation")
    public String changeInformation(String id, String nickname, String actualName, Integer sex, Integer year,
                                    Integer month, Integer day, String introduction) {
        User user = userService.findById(Integer.parseInt(id));
        user.setNickname(nickname);
        user.setActualName(actualName);
        user.setSex(sex);
        Date birthday = new Date(year - 1900, month - 1, day);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        user.setBirthday(sdf.format(birthday));
        user.setIntroduction(introduction);
        user.getPassword();
        userService.updateUser(user);
        return "success";
    }

    /**
     * 用户更新密码
     * @param userId
     * @param password
     * @return
     */
    @RequestMapping("/reception/updatepassword")
    public String updatePassword(Integer userId, String password) {
        User user = userService.findById(userId);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        userService.updateUser(user);
        return "success";
    }

    /**
     * 获取当前登录用户信息
     * @return
     */
    @RequestMapping("/reception/data")
    public User data() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user = userService.findById(user.getId());
        return user;
    }

    /**
     * 用户更新头像
     * @param base64
     * @return
     */
    @RequestMapping("/reception/changePicture")
    public String changePicture(String base64) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user = userService.findById(user.getId());
        user.setPicture(base64);
        userService.updateUser(user);
        return "success";
    }

}
