package com.example.dao;

import com.example.entity.Permission;

import java.util.List;

public interface PermissionDao extends BaseDao<Permission> {
    /**
     * 查所有的权限
     * @return
     */
    List<Permission> findAll();

    /**
     * 根据adminId获取其权限列表
     * @param adminId
     * @return
     */
    List<Permission> findListByAdminId(Long adminId);

    List<String> findAllCodeList();

    List<String> findCodeListByAdminId(Long adminId);
}
