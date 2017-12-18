package cn.edu.tju.scs.web.controller;

import cn.edu.tju.scs.dao.BonusDao;
import cn.edu.tju.scs.dto.response.BizCode;
import cn.edu.tju.scs.dto.response.PageResults;
import cn.edu.tju.scs.dto.response.ResponseCode;
import cn.edu.tju.scs.entity.Bonus;
import cn.edu.tju.scs.service.ApplicationService;
import cn.edu.tju.scs.web.annotation.Logical;
import cn.edu.tju.scs.web.annotation.Owner;
import cn.edu.tju.scs.web.annotation.RequiresRole;
import cn.edu.tju.scs.web.controller.base.BaseController;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Takahashi on 2016/12/19.
 */
@Controller
@RequestMapping("/bonus")
public class BonusController  extends BaseController {

    @Autowired
    BonusDao bonusDao;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     * 返回每月结算津贴情况（老师,教导主任，校长，副校长等等都可，老师则只能是自己）
     * @param httpSession
     * @param uid
     * @param pageId
     * @return
     */
    @Owner(requireOwner = true) // 自己
    @RequiresRole(value = {"xiaozhang","fuxiaozhang","jiaodaozhuren"},logical = Logical.OR)
    @RequestMapping(value = "/{uid}/{pageId}", method = RequestMethod.GET, produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public ResponseCode<PageResults<Bonus>> getBonusList(HttpSession httpSession,
                                                         @PathVariable("uid") String uid,
                                                         @PathVariable("pageId") int pageId){
        try{
            int totalCount = bonusDao.getCountByUid(uid);
            int pageSize = 5;
            PageResults<Bonus> pageResults = new PageResults<Bonus>(pageSize, totalCount);
            pageResults.setCurrentPage(pageId);
            pageResults.resetPageNo();

            if(pageId > pageResults.getPageCount())
                pageId = pageResults.getPageCount();
            if(pageId < 1)
                pageId = 1;

            int start = (pageId - 1) * pageSize;

            List<Bonus> bonusList = bonusDao.getBonusListByUid(uid, start, pageSize);

            pageResults.setResults(bonusList);
            return new ResponseCode<PageResults<Bonus>>(BizCode.SUCCESS, pageResults);
        }
        catch (Exception e)
        {
//            throw new RuntimeException("操作失败！");
            throw new RuntimeException("操作失败！"+e.getMessage());
//            return new ResponseCode<PageResults<Bonus>>(BizCode.FAIL, null);
        }
    }
}
