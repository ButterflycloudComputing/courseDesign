package cn.edu.tju.scs.service.impl;

import cn.edu.tju.scs.dao.auth.RoleDao;
import cn.edu.tju.scs.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Takahashi on 2016/11/28.
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    public int addRoleToUser(int userId, int roleId) {
        return roleDao.addRoleToUser(userId, roleId);
    }
}
