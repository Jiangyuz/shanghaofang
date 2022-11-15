package com.example.service;

import com.example.entity.Community;
import com.example.service.BaseService;
import java.util.List;

public interface CommunityService extends BaseService<Community>{

    List<Community> findAll();
}
