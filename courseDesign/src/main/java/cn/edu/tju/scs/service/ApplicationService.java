package cn.edu.tju.scs.service;

/**
 * Created by Takahashi on 2016/12/19.
 */
public interface ApplicationService {

    /**
     * 插入新的请假记录, 根据请假的具体类型以及时间，选择相应的prover
     * @return
     */
    public int insertRecord(String uid, int vid, String date, int count, String reason, String info);
}
