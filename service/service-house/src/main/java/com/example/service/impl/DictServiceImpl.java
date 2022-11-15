package com.example.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.example.dao.DictDao;
import com.example.entity.Dict;
import com.example.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = DictService.class)
public class DictServiceImpl implements DictService {

    @Autowired
    private DictDao dictDao;


    @Override
    public List<Map<String, Object>> findZnodes(Long id) {
        //调用dao获得该节点的子节点
        List<Dict> dictList = dictDao.findListByParentId(id);
        //创建放回的List
        List<Map<String,Object>> zNodes = new ArrayList<>();
        //遍历dictList
        for (Dict dict : dictList ) {
            //放回数据格式[{ id : 2 ,isParent:true , name:"..."}]
            //创建Map
            Map map = new HashMap();
            map.put("id",dict.getId());
            map.put("name",dict.getName());
            //调用DictDao中判断改节点是否是父节点的方法
            Integer count = dictDao.countIsParent(dict.getId());
            map.put("isParent",count > 0 ? true : false);
            //将map放到放回的list中
            zNodes.add(map);
        }
        return zNodes;
    }

    //根据id获取子节点数据列表
    @Override
    public List<Dict> findListByParentId(Long parentId) {
        return dictDao.findListByParentId(parentId);
    }

    //根据编码获取子节点数据列表
    @Override
    public List<Dict> findListByDictCode(String dictCode) {
        //调用dao得到dict对象方法
        Dict listByDictCode = dictDao.findListByDictCode(dictCode);
        if(listByDictCode == null) return null;
        Long id = listByDictCode.getId();
        return this.findListByParentId(id);
    }

    //根据id获取dict名字
    @Override
    public String getNameById(Long houseTypeId) {
        return dictDao.getNameById(houseTypeId);
    }
}
