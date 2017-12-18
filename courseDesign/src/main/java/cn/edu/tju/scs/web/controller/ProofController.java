package cn.edu.tju.scs.web.controller;

import cn.edu.tju.scs.dto.response.BizCode;
import cn.edu.tju.scs.dto.response.ResponseCode;
import cn.edu.tju.scs.entity.Proof;
import cn.edu.tju.scs.service.ProofService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.ws.Response;

/**
 * Created by Takahashi on 2016/12/19.
 */
@Controller
@RequestMapping("/proof")
public class ProofController {

    private static Logger logger = LoggerFactory.getLogger(ProofController.class);

    @Autowired
    ProofService proofService;

    @RequestMapping(value = "/add",method = RequestMethod.POST, produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public ResponseCode<String> addProof(@RequestParam("aid") int aid,
                                         @RequestParam("name") String name,
                                         @RequestParam("url") String url,
                                         @RequestParam("tempUrl") String tempUrl,
                                         @RequestParam("size") String size,
                                         @RequestParam("typeNum") Integer typeNum,
                                         @RequestParam("originName") String originName,
                                         @RequestParam("mediaType") String mediaType,
                                         @RequestParam("md5") String md5){
        Proof proof = new Proof(aid, name, url, tempUrl, size, typeNum, mediaType, originName, md5);
        logger.info("Proof: \n" + proof.toString());
        proofService.addNewProof(proof);
        return new ResponseCode<String>(BizCode.SUCCESS,"操作成功！");
    }
}
