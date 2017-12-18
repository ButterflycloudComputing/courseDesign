package cn.edu.tju.scs.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by Takahashi on 2016/12/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring 配置文件
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class ReplaceDaoTest {

    @Resource
    ReplaceDao replaceDao;

    @Test
    public void testInsertRecord() throws Exception {
        System.out.println(replaceDao.insertRecord("3013218145","3013218135", "周二下午第二节课", "高三7班", "进行小测验"));
    }

    @Test
    public void testGetReplaceList() throws Exception {
//        System.out.println(replaceDao.getReplaceList("3013218135"));
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");//可以方便地修改日期格式
        String date = dateFormat.format( now );
        System.out.println(date);
    }

}