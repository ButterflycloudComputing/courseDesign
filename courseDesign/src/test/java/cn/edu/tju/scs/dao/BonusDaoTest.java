package cn.edu.tju.scs.dao;

import cn.edu.tju.scs.entity.Bonus;
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
public class BonusDaoTest {

    @Resource
    BonusDao bonusDao;

    @Test
    public void testGetBonusListByUid() throws Exception {
        System.out.println(bonusDao.getBonusListByUid("3013218145", 0 ,5));
    }

    @Test
    public void testUpdateReplaceA() throws Exception {
        System.out.println(bonusDao.updateReplaceA("3013218145","2016-12", 1));
    }

    @Test
    public void testUpdateReplaceB() throws Exception {
        System.out.println(bonusDao.updateReplaceB("3013218145","2016-12", -1));
    }

    @Test
    public void testGetBonusByUidMonth() throws Exception {
        System.out.println(bonusDao.getBonusByUidMonth("3013218145","2015-12"));
    }

    @Test
    public void testInsertDefaultRecord() throws Exception {
        System.out.println(bonusDao.insertDefaultRecord("3013218145","2015-12"));
    }

    @Test
    public void testUpdateHalf() throws Exception {
        System.out.println(bonusDao.updateHalf("3013218145","2016-11", 3));
    }

    @Test
    public void testUpdateZero() throws Exception {
        System.out.println(bonusDao.updateZero("3013218145","2016-12", 5));
    }

    @Test
    public void testGetCountOfLeave() throws Exception {
        System.out.println(bonusDao.getCountOfLeave("3013218145","2016%"));
    }

    @Test
    public void testGetCountByUid() throws Exception {
        System.out.println(bonusDao.getCountByUid("3013218145"));
    }


}