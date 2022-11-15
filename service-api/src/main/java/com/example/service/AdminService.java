package com.example.service;

import com.example.entity.Admin;

import java.util.List;

public interface AdminService extends BaseService<Admin> {
     List<Admin> findAll();

    Admin getByUsername(String username);
}
