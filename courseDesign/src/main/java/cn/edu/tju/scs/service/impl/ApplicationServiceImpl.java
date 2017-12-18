package cn.edu.tju.scs.service.impl;

import cn.edu.tju.scs.dao.ApplicationDao;
import cn.edu.tju.scs.dao.BonusDao;
import cn.edu.tju.scs.dao.auth.RoleDao;
import cn.edu.tju.scs.entity.Bonus;
import cn.edu.tju.scs.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Takahashi on 2016/12/19.
 */

@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    ApplicationDao applicationDao;

    @Autowired
    RoleDao roleDao;

    @Autowired
    BonusDao bonusDao;

    @Override
    public int insertRecord(String uid, int vid, String date, int count, String reason, String info) {

        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");//可以方便地修改日期格式
        String nowdate = dateFormat.format( now );
        //根据uid获取请假人的角色信息
        int role = roleDao.getRoleIDByUID(uid);
        int prover = 0;

        // 校长批复公假和副校长的假以及3天以上的事假
        // 教导主任审批1天以内的病假和事假
        // 其余为副校长批复
        if (vid == 1 || (vid == 2 && count > 3) || role == 2)
            prover = 1;
        else if((vid == 2 || vid == 3) && count <= 1)
            prover = 3;
        else
            prover = 2;

        int res = applicationDao.insertRecord(uid, vid, date, count, reason, info, prover);

        //同时更新bonus表
        if(res == 1)
        {
            if(bonusDao.getBonusByUidMonth(uid, nowdate) == null)
                bonusDao.insertDefaultRecord(uid, nowdate);
            if(vid == 2)
                bonusDao.updateZero(uid, nowdate, count);
            else if(vid == 3)
                bonusDao.updateHalf(uid, nowdate, count);
        }
        return res;
    }
}
