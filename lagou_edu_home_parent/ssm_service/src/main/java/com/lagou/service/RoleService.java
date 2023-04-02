package com.lagou.service;

import com.lagou.domain.Role;
import com.lagou.domain.RoleMenuVO;
import com.lagou.domain.Role_menu_relation;

import java.util.List;

public interface RoleService {
    /*查询所有角色*/
    public List<Role> findAllRole(Role role);
    /*根据角色Id查询该角色关联的菜单信息*/
    public List<String> findMenuByRoleId(Integer roleid);
    /*为角色分配菜单信息*/
    public void RoleContextMenu(RoleMenuVO roleMenuVO);
    /*删除角色*/
    public void deleteRole(Integer roleid);
}
