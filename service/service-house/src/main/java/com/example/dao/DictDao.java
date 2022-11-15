package com.example.dao;

import com.example.entity.Dict;

import java.util.List;

public interface DictDao {
    //根据id获取改节点下的所有子节点
    List<Dict> findListByParentId(Long parentId);

    //根据id判断该节点是否是父节点
    Integer countIsParent(Long id);

    /**
     * 根据编码获取dict对象
     * @param dictCode
     * @return
     */
    Dict findListByDictCode(String dictCode);

    //根据id获取名字
    String getNameById(Long id);
}
