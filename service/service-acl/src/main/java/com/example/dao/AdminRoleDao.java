package com.example.dao;

import com.example.entity.AdminRole;

import java.util.List;

public interface AdminRoleDao extends BaseDao<AdminRole>{

    /**
     * 根据用户id删除用户角色
     * @param adminId
     */
    void deleteByAdminId(Long adminId);


    /**
     * 根据用户id查询其角色数据
     * @param adminId
     * @return
     */
    List<Long> findRoleIdByAdminId(Long adminId);
}
