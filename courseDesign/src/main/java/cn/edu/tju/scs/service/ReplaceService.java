package cn.edu.tju.scs.service;

/**
 * Created by Takahashi on 2016/12/19.
 */
public interface ReplaceService {

    /**
     * 插入新的申请代课记录同时更新bonus
     * @param uid1
     * @param uid2
     * @param time
     * @param classno
     * @param info
     * @return
     */
    public int insertRecord(String uid1,
                            String uid2,
                            String time,
                            String classno,
                            String info);
}
