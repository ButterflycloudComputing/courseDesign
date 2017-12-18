package cn.edu.tju.scs.service;

import cn.edu.tju.scs.entity.Proof;

/**
 * Created by Takahashi on 2016/12/19.
 */
public interface ProofService {

    /**
     * 添加新的附件
     * @param proof
     * @return
     */
    public int addNewProof(Proof proof);
}
