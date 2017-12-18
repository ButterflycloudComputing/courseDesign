package cn.edu.tju.scs.dao.auth;

import org.apache.ibatis.annotations.Param;

/**
 * Created by Takahashi on 2016/11/29.
 */
public interface User_RoleDao {

    /**
     * 更行用户的角色
     * @param userId
     * @param roleId
     * @return
     */
    public int updateRoleToUser(@Param("userId") int userId, @Param("roleId") int roleId);
}
