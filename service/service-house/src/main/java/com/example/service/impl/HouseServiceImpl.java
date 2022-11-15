package com.example.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.example.dao.BaseDao;
import com.example.dao.DictDao;
import com.example.dao.HouseDao;
import com.example.entity.House;
import com.example.service.HouseService;
import com.example.vo.HouseQueryVo;
import com.example.vo.HouseVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Service(interfaceClass = HouseService.class)
@Transactional
public class HouseServiceImpl extends BaseServiceImpl<House> implements HouseService {

    @Autowired
    private HouseDao houseDao;
    @Autowired
    private DictDao dictDao;

    @Override
    protected BaseDao<House> getEntityDao() {
        return houseDao;
    }

    @Override
    public void publish(Long id, Integer status) {
        House house = new House();
        house.setId(id);
        house.setStatus(status);
        houseDao.update(house);
    }
    //重写该方法是为了展示房源中的户型、楼层、朝向
    @Override
    public House getById(Serializable id) {
        House house = houseDao.getById(id);
        if(null == house) return null;
        //户型：
        String houseTypeName = dictDao.getNameById(house.getHouseTypeId());
        //楼层
        String floorName = dictDao.getNameById(house.getFloorId());
        //建筑结构：
        String buildStructureName = dictDao.getNameById(house.getBuildStructureId());
        //朝向：
        String directionName = dictDao.getNameById(house.getDirectionId());
        //装修情况：
        String decorationName = dictDao.getNameById(house.getDecorationId());
        //房屋用途：
        String houseUseName = dictDao.getNameById(house.getHouseUseId());
        house.setHouseTypeName(houseTypeName);
        house.setFloorName(floorName);
        house.setBuildStructureName(buildStructureName);
        house.setDirectionName(directionName);
        house.setDecorationName(decorationName);
        house.setHouseUseName(houseUseName);
        return house;
    }

    @Override
    public PageInfo<HouseVo> findPageList(Integer pageNum, Integer pageSize ,HouseQueryVo houseQueryVo) {
        //开启分页
        PageHelper.startPage(pageNum,pageSize);
        //调用HouseDao中前端分也饿及带条件查询的方法
        Page<HouseVo> page = houseDao.findPageList(houseQueryVo);
        //遍历page 把id变成值
        for (HouseVo houseVo : page) {
            //获取房屋类型
            String houseTypeName = dictDao.getNameById(houseVo.getHouseTypeId());
            //获取楼层
            String floorName = dictDao.getNameById(houseVo.getFloorId());
            //获取朝向
            String directionName = dictDao.getNameById(houseVo.getDirectionId());
            houseVo.setHouseTypeName(houseTypeName);
            houseVo.setFloorName(floorName);
            houseVo.setDirectionName(directionName);
        }
        return new PageInfo<>(page,5);
    }
}
