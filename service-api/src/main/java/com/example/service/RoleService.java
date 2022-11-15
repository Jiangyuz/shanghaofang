package com.example.service;

import com.example.entity.Role;

import java.util.List;
import java.util.Map;


public interface RoleService extends BaseService<Role> {
    /**
     * 查出所有角色
     * @return
     */
    List<Role> findAll();

    /**
     * 根据用户id获取用户角色数据
     * @param adminId
     * @return
     */
    Map<String, Object> findRoleByAdminId(Long adminId);

    /**
     * 给用户分配角色
     * @param adminId
     * @param roleIds
     */
    void saveUserRoleRealtionShip(Long adminId, Long[] roleIds);

}
