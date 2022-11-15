package com.example.dao;

import com.example.entity.RolePermission;

import java.util.List;

public interface RolePermissionDao extends BaseDao<RolePermission>{
    /**
     * 根据角色id删除权限
     * @param roleId
     */
    void deleteByRoleId(Long roleId);

    /**
     * 根据角色id查出该角色的权限
     * @param roleId
     * @return
     */
    List<Long> findPermissionIdListByRoleId(Long roleId);
}
