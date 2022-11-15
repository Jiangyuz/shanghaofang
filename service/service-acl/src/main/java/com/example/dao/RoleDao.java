package com.example.dao;

import com.example.entity.Role;

import java.util.List;


public interface RoleDao extends BaseDao<Role> {
    List<Role> findAll();


}
