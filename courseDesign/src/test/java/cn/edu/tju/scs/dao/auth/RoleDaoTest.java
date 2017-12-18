package cn.edu.tju.scs.dao.auth;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by Takahashi on 2016/12/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class RoleDaoTest {

    @Resource
    RoleDao roleDao;

    @Test
    public void testQueryRoleByUserId() throws Exception {

    }

    @Test
    public void testAddRoleToUser() throws Exception {

    }

    @Test
    public void testGetRoleIDByUID() throws Exception {
        System.out.println(roleDao.getRoleIDByUID("3013218138"));
    }
}