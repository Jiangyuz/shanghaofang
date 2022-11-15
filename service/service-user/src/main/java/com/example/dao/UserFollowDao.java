package com.example.dao;

import com.example.entity.UserFollow;
import com.example.vo.UserFollowVo;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

public interface UserFollowDao extends BaseDao<UserFollow> {
    Integer countByUserIdAndHouserId(@Param("userId")Long userId, @Param("houseId")Long houseId);
    Page<UserFollowVo> findListPage(@Param("userId")Long userId);
}
