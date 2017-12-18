package cn.edu.tju.scs.dao;

import cn.edu.tju.scs.entity.Replace;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Takahashi on 2016/12/19.
 */
public interface ReplaceDao {

    /**
     * 获取某个用户代课的记录条数
     * @param uid2
     * @return
     */
    public int getCountByUid(String uid2);

    /**
     * 插入新的代课记录
     * @param uid1
     * @param uid2
     * @param time
     * @param classno
     * @param info
     * @return
     */
    public int insertRecord(@Param("uid1") String uid1,
                            @Param("uid2") String uid2,
                            @Param("time") String time,
                            @Param("classno") String classno,
                            @Param("info") String info);

    /**
     * 查看自己所需要去代课的记录信息
     * @param uid2
     * @return
     */
    public List<Replace> getReplaceList(@Param("uid2") String uid2, @Param("start") int start, @Param("offset") int offset);
}
