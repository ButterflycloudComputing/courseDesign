package cn.edu.tju.scs.dao;

import cn.edu.tju.scs.dto.entity.ApplicationRecordDTO;
import cn.edu.tju.scs.entity.Application;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Takahashi on 2016/12/19.
 */
public interface ApplicationDao {

    /**
     * 得到用户请假记录的数目，不同状态status
     * @param uid
     * @param status
     * @return
     */
    public int getCount(@Param("uid") String uid, @Param("status") int status);


    /**
     * 得到用户请假记录的列表，参数status
     * @return
     */
    public List<ApplicationRecordDTO> getRecordList(@Param("uid") String uid, @Param("status") int status, @Param("start") int start, @Param("offset") int offset);

    /**
     * 新增用户请假记录
     * @param uid
     * @param vid
     * @param date
     * @param count
     * @param reason
     * @param info
     * @return
     */
    public int insertRecord(@Param("uid") String uid,
                     @Param("vid") int vid,
                     @Param("date") String date,
                     @Param("count") int count,
                     @Param("reason") String reason,
                     @Param("info") String info,
                     @Param("prover") int prover);

    /**
     * 根据ID搜索请假记录
     * @param id
     * @return
     */
    public Application queryById(int id);

    /**
     * 修改指定ID的请假记录的status
     * @param id
     * @param status
     * @return
     */
    public int updateStatusById(@Param("id") int id, @Param("status") int status);

    /**
     * 得到指定prover等级的待批复的请假表的数目
     * @param prover
     * @return
     */
    public int getCountOfProver(int prover);


    /**
     * 根据role id获得待批复的请假申请
     * @param prover
     * @return
     */
    public List<ApplicationRecordDTO> getRecordListByProver(@Param("prover") int prover, @Param("start") int start, @Param("offset") int offset);


    /**
     * 根据role id获得已经批复的请假申请
     * @param prover
     * @return
     */
    public List<ApplicationRecordDTO> getRecordedListByProver(@Param("prover") int prover, @Param("start") int start, @Param("offset") int offset);
}
