package cn.edu.tju.scs.web.controller;

import cn.edu.tju.scs.dao.ReplaceDao;
import cn.edu.tju.scs.dao.auth.UserDao;
import cn.edu.tju.scs.dto.entity.UserBasicInfo;
import cn.edu.tju.scs.dto.entity.UserDTO;
import cn.edu.tju.scs.dto.response.BizCode;
import cn.edu.tju.scs.dto.response.PageResults;
import cn.edu.tju.scs.dto.response.ResponseCode;
import cn.edu.tju.scs.entity.Replace;
import cn.edu.tju.scs.exception.ResourceException;
import cn.edu.tju.scs.service.ReplaceService;
import cn.edu.tju.scs.web.annotation.Logical;
import cn.edu.tju.scs.web.annotation.Owner;
import cn.edu.tju.scs.web.annotation.RequiresRole;
import cn.edu.tju.scs.web.controller.base.BaseController;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Takahashi on 2016/12/19.
 */
@Controller
@RequestMapping("/substitute")
public class ReplaceController  extends BaseController {

    @Autowired
    ReplaceService replaceService;

    @Autowired
    ReplaceDao replaceDao;

    @Autowired
    private UserDao userDao;


    /**
     * 代课申请表提交（老师,教导主任，校长，副校长等等都可，老师则只能是自己）
     * @param httpSession
     * @param uid
     * @param replace
     * @return
     */
    @Owner(requireOwner = true) // 自己
    @RequiresRole(value = {"xiaozhang","fuxiaozhang","jiaodaozhuren"},logical = Logical.OR)
    @RequestMapping(value = "/{uid}",method = RequestMethod.POST, produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public ResponseCode<String> insertNewRecord(HttpSession httpSession,
                                                @PathVariable("uid")String uid,
                                                @RequestBody Replace replace){
        try{
            UserBasicInfo userBasicInfo = userDao.queryUserByUid(replace.getUid2());
            if (userBasicInfo == null) {
                throw new ResourceException(ResourceException.NON_EXISTENT_TYPE, "无此代课老师！");
            }

            replaceService.insertRecord(replace.getUid1(), replace.getUid2(), replace.getTime(), replace.getClassno(), replace.getInfo());

            return new ResponseCode<String>(BizCode.SUCCESS, "操作成功！");
        }
        catch (Exception e)
        {
//            return new ResponseCode<String>(BizCode.FAIL, "操作失败！");
            throw new RuntimeException("操作失败！"+e.getMessage());
        }
    }


    /**
     * 返回待代课的列表（老师,教导主任，校长，副校长等等都可，老师则只能是自己）
     * @param httpSession
     * @param uid
     * @param pageId
     * @return
     */
    @Owner(requireOwner = true) // 自己
    @RequiresRole(value = {"xiaozhang","fuxiaozhang","jiaodaozhuren"},logical = Logical.OR)
    @RequestMapping(value = "/{uid}/{pageId}", method = RequestMethod.GET, produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public ResponseCode<PageResults<Replace>> getReplaceList(HttpSession httpSession,
                                                             @PathVariable("uid") String uid,
                                                             @PathVariable("pageId") int pageId){
        try{
            int totalCount = replaceDao.getCountByUid(uid);
            int pageSize = 5;
            PageResults<Replace> pageResults = new PageResults<Replace>(pageSize, totalCount);
            pageResults.setCurrentPage(pageId);
            pageResults.resetPageNo();

            if(pageId > pageResults.getPageCount())
                pageId = pageResults.getPageCount();
            if(pageId < 1)
                pageId = 1;

            int start = (pageId - 1) * pageSize;

            List<Replace> replaceList = replaceDao.getReplaceList(uid, start, pageSize);

            pageResults.setResults(replaceList);
            return  new ResponseCode<PageResults<Replace>>(BizCode.SUCCESS, pageResults);
        }
        catch (Exception e)
        {
            throw new RuntimeException("操作失败！"+e.getMessage());
//            return  new ResponseCode<PageResults<Replace>>(BizCode.FAIL, null);
        }
    }

}
