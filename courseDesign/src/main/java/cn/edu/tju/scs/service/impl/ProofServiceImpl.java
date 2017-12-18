package cn.edu.tju.scs.service.impl;

import cn.edu.tju.scs.Config;
import cn.edu.tju.scs.dao.ProofDao;
import cn.edu.tju.scs.entity.Proof;
import cn.edu.tju.scs.service.ProofService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Takahashi on 2016/12/19.
 */
@Service
public class ProofServiceImpl implements ProofService {

    @Autowired
    Config config;

    @Autowired
    ProofDao proofDao;

    private String getTypeByTypeNum(Integer num){
        switch (num){
            case 0:
                return config.getImageType();
            case 1:
                return config.getAudioType();
            case 2:
                return config.getVideoType();
            case 3:
                return config.getAnalysisType();
            default:
                return config.getImageType();
        }
    }

    @Override
    public int addNewProof(Proof proof) {
        String type = getTypeByTypeNum(proof.getTypeNum());
        int res = proofDao.insertProof(proof.getAid(),
                proof.getName(), proof.getUrl(),
                proof.getTempUrl(), proof.getSize(),
                type, proof.getTypeNum(),
                proof.getMediaType(),proof.getOriginName(),
                proof.getFileMd5());
        return res;
    }
}
