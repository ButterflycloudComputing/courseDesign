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
public class UserDaoTest {

    @Resource
    UserDao userDao;

    @Test
    public void testQueryUserByUid() throws Exception {
        System.out.println(userDao.queryUserByUid("3013218145"));
    }

    @Test
    public void testQueryUser() throws Exception {
        System.out.println(userDao.queryUser("3013218145"));
    }
}