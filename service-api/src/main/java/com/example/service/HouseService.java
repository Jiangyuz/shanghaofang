package com.example.service;

import com.example.entity.House;
import com.example.vo.HouseQueryVo;
import com.example.vo.HouseVo;
import com.github.pagehelper.PageInfo;

public interface HouseService extends BaseService<House> {
    void publish(Long id, Integer status);

    //前端分页及带条件查询的方法
    PageInfo<HouseVo> findPageList(Integer pageNum, Integer pageSize , HouseQueryVo houseQueryVo);
}
