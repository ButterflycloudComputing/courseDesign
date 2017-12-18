package cn.edu.tju.scs.service.impl;

import cn.edu.tju.scs.dao.BonusDao;
import cn.edu.tju.scs.dao.ReplaceDao;
import cn.edu.tju.scs.service.ReplaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Takahashi on 2016/12/19.
 */

@Service
public class ReplaceServiceImpl implements ReplaceService {

    @Autowired
    ReplaceDao replaceDao;

    @Autowired
    BonusDao bonusDao;

    @Override
    public int insertRecord(String uid1, String uid2, String time, String classno, String info) {

        //更新bonus表
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");//可以方便地修改日期格式
        String date = dateFormat.format( now );
//        System.out.println(date);

        //先判断是否该user当月有记录
        if(bonusDao.getBonusByUidMonth(uid1, date) == null)
            bonusDao.insertDefaultRecord(uid1, date);
        if(bonusDao.getBonusByUidMonth(uid2, date) == null)
            bonusDao.insertDefaultRecord(uid2, date);

        int resA = bonusDao.updateReplaceA(uid1, date, 1);
        int resB = bonusDao.updateReplaceB(uid2, date, 1);

        return replaceDao.insertRecord(uid1, uid2, time, classno, info);
    }
}
