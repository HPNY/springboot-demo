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

    /**
     * 添加权限角色
     *
     * @param role
     * @return
     */
    @RequestMapping("/role/add")
    public String addRole(Role role) {
        roleService.addRole(role);
        return "success";
    }

    /**
     * 更新权限角色信息
     *
     * @param role
     * @return
     */
    @RequestMapping("/role/role_update")
    public String updateRole(Role role) {
        roleService.updateRole(role);
        return "success";
    }

    /**
     * 删除权限角色
     *
     * @param id
     * @return
     */
    @RequestMapping("/role/delete")
    public String delRole(Integer id) {
        roleService.delRole(id);
        return "success";
    }

    /**
     * 查看单个权限角色
     *
     * @param id
     * @return
     */
    @RequestMapping("/role/findonerole")
    public Role findOne(Integer id) {
        return roleService.findById(id);
    }

    /**
     * 分页显示权限角色，若有条件，则分页模糊查询
     *
     * @param pageCount
     * @param containing
     * @return
     */
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

    /**
     * 根据角色名进行模糊查询（分页）
     *
     * @param pageCount
     * @param name
     * @return
     */
    @RequestMapping("/role/containing")
    public Page<Role> findAllByNameContaining(Integer pageCount, String name) {
        if (pageCount == null) {
            pageCount = 0;
        }
        Page<Role> page = roleService.findAllByNameContaining(name, pageCount);
        return page;
    }

}
