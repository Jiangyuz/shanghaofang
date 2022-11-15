package com.example.dao;

import com.example.entity.House;
import com.example.vo.HouseQueryVo;
import com.example.vo.HouseVo;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

public interface HouseDao extends BaseDao<House> {

    Page<HouseVo> findPageList(HouseQueryVo houseQueryVo);
}
