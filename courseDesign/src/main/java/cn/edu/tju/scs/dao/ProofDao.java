package cn.edu.tju.scs.dao;

import org.apache.ibatis.annotations.Param;

/**
 * Created by Takahashi on 2016/12/19.
 */
public interface ProofDao {

    /**
     * 插入新的proof记录
     * @return
     */
    public int insertProof(@Param("aid") int aid,
                           @Param("name") String name,
                           @Param("url") String url,
                           @Param("tempUrl") String tempUrl,
                           @Param("size") String size,
                           @Param("type") String type,
                           @Param("typeNum") int typeNum,
                           @Param("mediaType") String mediaType,
                           @Param("originName") String originName,
                           @Param("fileMd5") String fileMd5);
}
