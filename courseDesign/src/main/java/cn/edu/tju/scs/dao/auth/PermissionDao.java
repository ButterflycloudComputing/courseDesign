package cn.edu.tju.scs.dao.auth;


import cn.edu.tju.scs.dto.entity.PermissionDTO;
import cn.edu.tju.scs.entity.auth.Role;

import java.util.List;

/**
 * Created by haoxiaotian on 2016/9/4 17:05.
 */
public interface PermissionDao {

    /**
     * 根据角色查询所有权限
     * @param roles
     * @return
     */
    public List<PermissionDTO> queryPermissionByRoles(List<Role> roles);


}
