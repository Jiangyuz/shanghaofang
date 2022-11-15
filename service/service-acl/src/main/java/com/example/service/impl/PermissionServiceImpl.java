package com.example.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.example.dao.BaseDao;
import com.example.dao.PermissionDao;
import com.example.dao.RolePermissionDao;
import com.example.entity.Permission;
import com.example.entity.RolePermission;
import com.example.helper.PermissionHelper;
import com.example.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service(interfaceClass = PermissionService.class)
@Transactional
public class PermissionServiceImpl extends BaseServiceImpl<Permission> implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;
    @Autowired
    private RolePermissionDao rolePermissionDao;

    @Override
    protected BaseDao<Permission> getEntityDao() {
        return permissionDao;
    }


    /**
     * 查出所有的权限和当前角色的权限
     * @param roleId
     * @return
     */
    @Override
    public List<Map<String, Object>> findPermissionByRoleId(Long roleId) {
        //查出所有权限列表
        List<Permission> permissionList = permissionDao.findAll();
        //获取角色已经有的权限
        List<Long> permissionIdList = rolePermissionDao.findPermissionIdListByRoleId(roleId);
        //构建ztree数据
        //参考文档：http://www.treejs.cn/v3/demo.php#_201
        // { id:2, pId:0, name:"随意勾选 2", checked:true, open:true},
        List<Map<String,Object>> zNodes = new ArrayList<>();
        for (Permission permission : permissionList) {
            Map<String,Object> map = new HashMap();
            map.put("id",permission.getId());
            map.put("pId",permission.getParentId());
            map.put("name",permission.getName());
            if(permissionIdList.contains(permission.getId())){
                map.put("checked", true);
            }
            zNodes.add(map);
        }

        return zNodes;
    }

    /**
     * 保存修改的用户的权限
     * @param roleId
     * @param permissionIds
     */
    @Override
    public void saveRolePermissionRealtionShip(Long roleId, Long[] permissionIds) {
        //先删除所有权限
        rolePermissionDao.deleteByRoleId(roleId);
        //重新赋予权限
        for (Long permissionId : permissionIds) {
            //判断是否有空
            if(StringUtils.isEmpty(permissionId)) continue;
            //创建对象，重新赋予权限
            RolePermission rolePermission = new RolePermission();
            rolePermission.setPermissionId(permissionId);
            rolePermission.setRoleId(roleId);
            rolePermissionDao.insert(rolePermission);
        }

    }

    @Override
    public List<Permission> findMenuPermissionByAdminId(Long adminId) {
        List<Permission> permissionList = null;
        //admin账号id为：1
        if(adminId == 1) {
            //如果是超级管理员，获取所有菜单
            permissionList = permissionDao.findAll();
        } else {
            permissionList = permissionDao.findListByAdminId(adminId);
        }
        //把权限数据构建成树形结构数据
        List<Permission> result = PermissionHelper.bulid(permissionList);
        return result;
    }

    @Override
    public List<Permission> findAllMenu() {
        //全部权限列表
        List<Permission> permissionList = permissionDao.findAll();
        if(CollectionUtils.isEmpty(permissionList)) return null;

        //构建树形数据,总共三级
        //把权限数据构建成树形结构数据
        List<Permission> result = PermissionHelper.bulid(permissionList);
        return result;
    }
    @Override
    public List<String> findCodeListByAdminId(Long adminId) {
        //超级管理员admin账号id为：1
        if(adminId.longValue() == 1) {
            return permissionDao.findAllCodeList();
        }
        return permissionDao.findCodeListByAdminId(adminId);
    }
}
