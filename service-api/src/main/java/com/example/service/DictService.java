package com.example.service;

import com.example.entity.Dict;

import java.util.List;
import java.util.Map;

public interface DictService {
    //查询数据字典中的数据
    List<Map<String,Object>> findZnodes(Long id);

    /**
     * 根据id获取子节点数据列表
     * @param parentId
     * @return
     */
    List<Dict> findListByParentId(Long parentId);

    //根据编码获取子节点数据列表
    List<Dict> findListByDictCode(String dictCode);

    String getNameById(Long houseTypeId);
}
