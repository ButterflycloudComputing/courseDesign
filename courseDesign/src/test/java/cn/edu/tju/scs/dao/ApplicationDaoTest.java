package cn.edu.tju.scs.dao;

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
//告诉junit spring 配置文件
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class ApplicationDaoTest {

    @Resource
    private ApplicationDao applicationDao;


    @Test
    public void testGetRecordList(){
        System.out.println(applicationDao.getRecordList("3013218135", 2, 0, 5));
    }


    @Test
    public void testInsertRecordList(){

        System.out.println(applicationDao.insertRecord("3013218145",2,"20161229",1,"buzhidao","zhanwu",2));
    }

    @Test
    public void testQueryById() throws Exception {
        System.out.print(applicationDao.queryById(1));
    }

    @Test
    public void testUpdateStatusById() throws Exception {
        System.out.print(applicationDao.updateStatusById(5, 1));
    }

    @Test
    public void testGetRecordListByProver() throws Exception {
        System.out.println(applicationDao.getRecordListByProver(2, 0, 5));
    }

    @Test
    public void testGetCount() throws Exception {
        System.out.println(applicationDao.getCount("3013218135",2));
    }
}