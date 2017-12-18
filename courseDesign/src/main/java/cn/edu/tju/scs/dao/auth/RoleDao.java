package cn.edu.tju.scs.dao.auth;


import cn.edu.tju.scs.entity.auth.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by haoxiaotian on 2016/9/4 17:05.
 */
public interface RoleDao {

    /**
     * 根据用户id查询用户所有 角色
     * @param id
     * @return
     */
    public List<Role> queryRoleByUserId(int id);

    /**
     * 给用户加角色
     * @param userId
     * @param roleId
     * @return
     */
    public int addRoleToUser(@Param("userId") int userId,
                             @Param("roleId") int roleId);

    /**
     * 根据用户的uid获得用户的role的id
     * @param uid
     * @return
     */
    public int getRoleIDByUID(String uid);
}
