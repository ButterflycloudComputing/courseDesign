package cn.edu.tju.scs.dao;

import cn.edu.tju.scs.entity.Bonus;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Takahashi on 2016/12/19.
 */
public interface BonusDao {

    /**
     * 获取某用户的bonus记录总条数
     * @param uid
     * @return
     */
    public int getCountByUid(String uid);

    /**
     * 新建默认记录
     * @return
     */
    public int insertDefaultRecord(@Param("uid") String uid, @Param("month") String month);

    /**
     * 获取用户的津贴列表
     * @param uid
     * @return
     */
    public List<Bonus> getBonusListByUid(@Param("uid") String uid, @Param("start") int start, @Param("offset") int offset);

    /**
     * 获取某个用户某月的bonus
     * @param uid
     * @param month
     * @return
     */
    public Bonus getBonusByUidMonth(@Param("uid") String uid, @Param("month") String month);

    /**
     * 更新某人某月的请人代课次数
     * @param uid
     * @param month
     * @return
     */
    public int updateReplaceA(@Param("uid") String uid, @Param("month") String month, @Param("val") int val);

    /**
     * 更新某人某月的替人代课次数
     * @param uid
     * @param month
     * @return
     */
    public int updateReplaceB(@Param("uid") String uid, @Param("month") String month, @Param("val") int val);

    /**
     * 更新某人某月半薪假期的天数
     * @param uid
     * @param month
     * @param val
     * @return
     */
    public int updateHalf(@Param("uid") String uid, @Param("month") String month, @Param("val") int val);

    /**
     * 更新某人某月零薪的天数
     * @param uid
     * @param month
     * @param val
     * @return
     */
    public int updateZero(@Param("uid") String uid, @Param("month") String month, @Param("val") int val);


    /**
     * 获取某用户某年已经请的事假的天数
     * @param uid
     * @param year
     * @return
     */
    public int getCountOfLeave(@Param("uid") String uid, @Param("year") String year);

}
