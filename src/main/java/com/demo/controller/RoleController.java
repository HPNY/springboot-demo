package com.demo.controller;

import com.demo.entity.Role;
import com.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("/role/add")
    public String addRole(Role role) {
        roleService.addRole(role);
        return "success";
    }

    @RequestMapping("/role/role_update")
    public String updateRole(Role role) {
        roleService.updateRole(role);
        return "success";
    }

    @RequestMapping("/role/delete")
    public String delRole(Integer id) {
        roleService.delRole(id);
        return "success";
    }

    @RequestMapping("/role/findonerole")
    public Role findOne(Integer id) {
        return roleService.findById(id);
    }

    @RequestMapping("/role/list")
    public Page findAllRole(Integer pageCount, String containing) {
        Page page;
        if (containing == null) {
            page = roleService.findAllRoleByPage(pageCount);
        } else {
            page = roleService.findAllByNameContaining(containing, pageCount);
        }
        return page;
    }

    @RequestMapping("/role/containing")
    public Page<Role> findAllByNameContaining(Integer pageCount, String name) {
        if (pageCount == null) {
            pageCount = 0;
        }
        Page<Role> page = roleService.findAllByNameContaining(name, pageCount);
        return page;
    }

}
