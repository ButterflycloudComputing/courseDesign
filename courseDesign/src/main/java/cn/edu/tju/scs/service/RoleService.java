package cn.edu.tju.scs.service;

/**
 * Created by Takahashi on 2016/11/28.
 */
public interface RoleService {

    /**
     * 给用户加角色
     * @param userId
     * @param roleId
     * @return
     */
    public int addRoleToUser(int userId, int roleId);
}
