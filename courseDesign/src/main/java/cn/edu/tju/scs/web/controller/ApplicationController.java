package cn.edu.tju.scs.web.controller;

import cn.edu.tju.scs.dao.ApplicationDao;
import cn.edu.tju.scs.dao.BonusDao;
import cn.edu.tju.scs.dao.auth.RoleDao;
import cn.edu.tju.scs.dao.auth.UserDao;
import cn.edu.tju.scs.dto.entity.ApplicationRecordDTO;
import cn.edu.tju.scs.dto.entity.UserBasicInfo;
import cn.edu.tju.scs.dto.response.BizCode;
import cn.edu.tju.scs.dto.response.PageResults;
import cn.edu.tju.scs.dto.response.ResponseCode;
import cn.edu.tju.scs.entity.Application;
import cn.edu.tju.scs.service.ApplicationService;
import cn.edu.tju.scs.web.annotation.Logical;
import cn.edu.tju.scs.web.annotation.Owner;
import cn.edu.tju.scs.web.annotation.PrivateResource;
import cn.edu.tju.scs.web.annotation.RequiresRole;
import cn.edu.tju.scs.web.controller.base.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.web.http.HttpSessionStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Takahashi on 2016/12/19.
 */
@Controller
@RequestMapping("/applications")
public class ApplicationController extends BaseController{

    @Autowired
    ApplicationDao applicationDao;

    @Autowired
    RoleDao roleDao;

    @Autowired
    UserDao userDao;

    @Autowired
    BonusDao bonusDao;

    @Autowired
    ApplicationService applicationService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     * 新申请（老师,教导主任，校长，副校长等等都可，老师则只能是自己）
     * @param httpSession
     * @param uid
     * @param vid
     * @param date
     * @param count
     * @param reason
     * @param info
     * @return
     */
    @Owner(requireOwner = true) // 自己
    @RequiresRole(value = {"xiaozhang","fuxiaozhang","jiaodaozhuren"},logical = Logical.OR)
    @RequestMapping(value = "/{uid}", method = RequestMethod.POST,produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public ResponseCode<String> addRecord(HttpSession httpSession,
                                          @PathVariable("uid")String uid,
                                          @RequestParam(value = "vid", required = false) int vid,
                                          @RequestParam(value = "date", required = false) String date,
                                          @RequestParam(value = "count", required = false) int count,
                                          @RequestParam(value = "reason", required = false) String reason,
                                          @RequestParam(value = "info", required = false) String info){

        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");//可以方便地修改日期格式
        String nowdate = dateFormat.format( now );

        if(vid == 2 && count > 3) {
            return new ResponseCode<String>(BizCode.FAIL, "单次请假不得超过3天！");
        }
        if(vid == 2 && count + bonusDao.getCountOfLeave(uid, nowdate + "%") > 15) {
            return new ResponseCode<String>(BizCode.FAIL, "剩余可申请事假天数不足！");
        }
        try{
            int res = applicationService.insertRecord(uid, vid, date, count, reason, info);
            if(res == 1) {
                return new ResponseCode<String>(BizCode.SUCCESS, "申请成功！");
            } else {
                throw new RuntimeException("操作失败！");
            }
//                return new ResponseCode<String>(BizCode.FAIL,"操作失败！");
        }
        catch (Exception e)
        {
//            return new ResponseCode<String>(BizCode.FAIL,"操作失败！");
            throw new RuntimeException("操作失败！"+e.getMessage());
        }
    }




    /**
     * //获取已经完成的请假记录（老师,教导主任，校长，副校长等等都可，老师则只能是自己）
     * @param uid
     * @param pageId
     * @return
     */
    @Owner(requireOwner = true) // 自己
    @RequiresRole(value = {"xiaozhang","fuxiaozhang","jiaodaozhuren"},logical = Logical.OR)
    @RequestMapping(value = "/{uid}/completes/{pageId}", method = RequestMethod.GET,produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public ResponseCode<PageResults<ApplicationRecordDTO>> getCompleteList(HttpSession httpSession,
                                                                           @PathVariable("uid") String uid,
                                                                           @PathVariable("pageId") int pageId) {
        try {
            int totalCount = applicationDao.getCount(uid, 2);
            int pageSize = 5;
            PageResults<ApplicationRecordDTO> pageResults = new PageResults<ApplicationRecordDTO>(pageSize, totalCount);
            pageResults.setCurrentPage(pageId);
            pageResults.resetPageNo();

            if(pageId > pageResults.getPageCount()) {
                pageId = pageResults.getPageCount();
            }
            if(pageId < 1) {
                pageId = 1;
            }

            int start = (pageId - 1) * pageSize;

            List<ApplicationRecordDTO> recordDTOList = applicationDao.getRecordList(uid, 2, start, pageSize);

            pageResults.setResults(recordDTOList);
            return new ResponseCode<PageResults<ApplicationRecordDTO>>(BizCode.SUCCESS,pageResults);
        }
        catch (Exception e)
        {
//            throw new RuntimeException("操作失败！");
            throw new RuntimeException("操作失败！"+e.getMessage());
//            return null;
        }
    }



    /**
     * 获取已通过但还未销假的请假记录，（老师,教导主任，校长，副校长等等都可，老师则只能是自己）
     * @param uid
     * @param pageId
     * @return
     */
    @Owner(requireOwner = true) // 自己
    @RequiresRole(value = {"xiaozhang","fuxiaozhang","jiaodaozhuren"},logical = Logical.OR)
    @RequestMapping(value = "/{uid}/uncompletes/{pageId}", method = RequestMethod.GET,produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public ResponseCode<PageResults<ApplicationRecordDTO>> getCUnclearList(HttpSession httpSession,
                                                                           @PathVariable("uid") String uid,
                                                                           @PathVariable("pageId") int pageId) {
        try {
            int totalCount = applicationDao.getCount(uid, 1);
            int pageSize = 5;
            PageResults<ApplicationRecordDTO> pageResults = new PageResults<ApplicationRecordDTO>(pageSize, totalCount);
            pageResults.setCurrentPage(pageId);
            pageResults.resetPageNo();

            if(pageId > pageResults.getPageCount())
                pageId = pageResults.getPageCount();
            if(pageId < 1)
                pageId = 1;

            int start = (pageId - 1) * pageSize;

            List<ApplicationRecordDTO> recordDTOList = applicationDao.getRecordList(uid, 1, start, pageSize);

            pageResults.setResults(recordDTOList);
            return new ResponseCode<PageResults<ApplicationRecordDTO>>(BizCode.SUCCESS,pageResults);
        }
        catch (Exception e)
        {
//            throw new RuntimeException("操作失败！");
            throw new RuntimeException("操作失败！"+e.getMessage());
//            return null;
        }
    }


    /**
     * 获取已提交但还未通过的请假记录，（老师,教导主任，校长，副校长等等都可，老师则只能是自己）
     * @param uid
     * @param pageId
     * @return
     */
    @Owner(requireOwner = true) // 自己
    @RequiresRole(value = {"xiaozhang","fuxiaozhang","jiaodaozhuren"},logical = Logical.OR)
    @RequestMapping(value = "/{uid}/unapprovals/{pageId}", method = RequestMethod.GET,produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public ResponseCode<PageResults<ApplicationRecordDTO>> getUnproveList(HttpSession httpSession,
                                                                          @PathVariable("uid") String uid,
                                                                          @PathVariable("pageId") int pageId) {
        try {
            int totalCount = applicationDao.getCount(uid, 0);
            int pageSize = 5;
            PageResults<ApplicationRecordDTO> pageResults = new PageResults<ApplicationRecordDTO>(pageSize, totalCount);
            pageResults.setCurrentPage(pageId);
            pageResults.resetPageNo();

            if(pageId > pageResults.getPageCount())
                pageId = pageResults.getPageCount();
            if(pageId < 1)
                pageId = 1;

            int start = (pageId - 1) * pageSize;

            List<ApplicationRecordDTO> recordDTOList = applicationDao.getRecordList(uid, 0, start, pageSize);

            pageResults.setResults(recordDTOList);
            return new ResponseCode<PageResults<ApplicationRecordDTO>>(BizCode.SUCCESS,pageResults);
        }
        catch (Exception e)
        {
//            throw new RuntimeException("操作失败！");
            throw new RuntimeException("操作失败！"+e.getMessage());

//            return null;
        }
    }


    /**
     * 获取提交且被拒绝的请假记录，（老师,教导主任，校长，副校长等等都可，老师则只能是自己）
     * @param uid
     * @param pageId
     * @return
     */
    @Owner(requireOwner = true) // 自己
    @RequiresRole(value = {"xiaozhang","fuxiaozhang","jiaodaozhuren"},logical = Logical.OR)
    @RequestMapping(value = "/{uid}/rejects/{pageId}", method = RequestMethod.GET,produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public ResponseCode<PageResults<ApplicationRecordDTO>> getRejectList(HttpSession httpSession,
                                                                         @PathVariable("uid") String uid,
                                                                         @PathVariable("pageId") int pageId) {
        try {
            int totalCount = applicationDao.getCount(uid, -1);
            int pageSize = 5;
            PageResults<ApplicationRecordDTO> pageResults = new PageResults<ApplicationRecordDTO>(pageSize, totalCount);
            pageResults.setCurrentPage(pageId);
            pageResults.resetPageNo();

            if(pageId > pageResults.getPageCount())
                pageId = pageResults.getPageCount();
            if(pageId < 1)
                pageId = 1;

            int start = (pageId - 1) * pageSize;

            List<ApplicationRecordDTO> recordDTOList = applicationDao.getRecordList(uid, -1, start, pageSize);

            pageResults.setResults(recordDTOList);
            return new ResponseCode<PageResults<ApplicationRecordDTO>>(BizCode.SUCCESS,pageResults);
        }
        catch (Exception e)
        {
//            throw new RuntimeException("操作失败！");
            throw new RuntimeException("操作失败！"+e.getMessage());

//            return null;
        }
    }


    /**
     * 根据请假表的id获取请假申请表的详细信息，老师查看自己信息（老师,教导主任，校长，副校长等等都可，老师则只能是自己）
     * TODO:资源也必须私有
     * @param uid
     * @param id
     * @return
     */
    @Owner(requireOwner = true) // 自己
    @PrivateResource(requireOwner = true)  // 私有资源
    @RequiresRole(value = {"xiaozhang","fuxiaozhang","jiaodaozhuren"},logical = Logical.OR)
    @RequestMapping(value = "/{uid}/{id}", method = RequestMethod.GET,produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public ResponseCode<Application> getDetailById(HttpSession httpSession,
                                                   @PathVariable(value = "uid") String uid,
                                                   @PathVariable(value = "id") int id)
    {
        try{
            Application application = applicationDao.queryById(id);
            return new ResponseCode<Application>(BizCode.SUCCESS, application);
        }
        catch (Exception e)
        {
//            throw new RuntimeException("操作失败！");
            throw new RuntimeException("操作失败！"+e.getMessage());
//            return new ResponseCode<Application>(BizCode.FAIL, null);
        }
    }


    /**
     * 获取待批准的请假记录列表（教导主任，校长，副校长可）：审批
     * @param uid
     * @param pageId
     * @return
     */
    @RequiresRole(value = {"xiaozhang","fuxiaozhang","jiaodaozhuren"},logical = Logical.OR)
    @RequestMapping(value = "/{uid}/approves/{pageId}", method = RequestMethod.GET,produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public ResponseCode<PageResults<ApplicationRecordDTO>> getApproveList(HttpSession httpSession,
                                                                          @PathVariable("uid") String uid,
                                                                          @PathVariable("pageId") int pageId){
        try{
            int prover = roleDao.getRoleIDByUID(uid);

            int totalCount = applicationDao.getCountOfProver(prover);
            int pageSize = 5;
            PageResults<ApplicationRecordDTO> pageResults = new PageResults<ApplicationRecordDTO>(pageSize, totalCount);
            pageResults.setCurrentPage(pageId);
            pageResults.resetPageNo();

            if(pageId > pageResults.getPageCount())
                pageId = pageResults.getPageCount();
            if(pageId < 1)
                pageId = 1;

            int start = (pageId - 1) * pageSize;

            List<ApplicationRecordDTO> applicationRecordDTOList = applicationDao.getRecordListByProver(prover, start, pageSize);

            pageResults.setResults(applicationRecordDTOList);
            return new ResponseCode<PageResults<ApplicationRecordDTO>>(BizCode.SUCCESS, pageResults);
        }
        catch (Exception e)
        {
//            throw new RuntimeException("操作失败！");
            throw new RuntimeException("操作失败！"+e.getMessage());
//            return new ResponseCode<PageResults<ApplicationRecordDTO>>(BizCode.FAIL, null);
        }
    }


    /**
     * 获取所有的审批历史（教导主任，校长，副校长可）：审批
     * @param uid
     * @param pageId
     * @return
     */
    @RequiresRole(value = {"xiaozhang","fuxiaozhang","jiaodaozhuren"},logical = Logical.OR)
    @RequestMapping(value = "/{uid}/approved/{pageId}", method = RequestMethod.GET,produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public ResponseCode<PageResults<ApplicationRecordDTO>> getApprovedList(HttpSession httpSession,
                                                                           @PathVariable("uid") String uid,
                                                                           @PathVariable("pageId") int pageId){
        try{
            int prover = roleDao.getRoleIDByUID(uid);

            int totalCount = applicationDao.getCountOfProver(prover);
            int pageSize = 5;
            PageResults<ApplicationRecordDTO> pageResults = new PageResults<ApplicationRecordDTO>(pageSize, totalCount);
            pageResults.setCurrentPage(pageId);
            pageResults.resetPageNo();

            if(pageId > pageResults.getPageCount())
                pageId = pageResults.getPageCount();
            if(pageId < 1)
                pageId = 1;

            int start = (pageId - 1) * pageSize;

            List<ApplicationRecordDTO> applicationRecordDTOList = applicationDao.getRecordedListByProver(prover, start, pageSize);

            pageResults.setResults(applicationRecordDTOList);
            return new ResponseCode<PageResults<ApplicationRecordDTO>>(BizCode.SUCCESS, pageResults);
        }
        catch (Exception e)
        {
//            throw new RuntimeException("操作失败！");
            throw new RuntimeException("操作失败！"+e.getMessage());
//            return new ResponseCode<PageResults<ApplicationRecordDTO>>(BizCode.FAIL, null);
        }
    }



    /**
     * 获取待批准的请假表详情信息，包括请假人信息和请假信息（教导主任，校长，副校长可）：审批
     * @param httpSession
     * @param id
     * @param uid
     * @return
     */
    @RequiresRole(value = {"xiaozhang","fuxiaozhang","jiaodaozhuren"},logical = Logical.OR)
    @RequestMapping(value = "/approves/{uid}/{id}", method = RequestMethod.GET,produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public ResponseCode<Map<String, Object>> getUnapproveDetail(HttpSession httpSession,
                                                                @PathVariable(value = "id") int id,
                                                                @PathVariable(value = "uid") String uid){
        try{
            Application application = applicationDao.queryById(id);
            UserBasicInfo userInfo = userDao.queryUserByUid(uid);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("application", application);
            map.put("userInfo", userInfo);

            return new ResponseCode<Map<String, Object>>(BizCode.SUCCESS,map);
        }
        catch (Exception e)
        {
//            throw new RuntimeException("操作失败！");
            throw new RuntimeException("操作失败！"+e.getMessage());
//            return new ResponseCode<Map<String, Object>>(BizCode.FAIL, null);
        }
    }


    /**
     * 申请不通过（教导主任，校长，副校长可）：审批
     * @param httpSession
     * @param id
     * @return
     */
    @RequiresRole(value = {"xiaozhang","fuxiaozhang","jiaodaozhuren"},logical = Logical.OR)
    @RequestMapping(value = "/reject/{id}", method = RequestMethod.PUT,produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public ResponseCode<String> rejectApplication(HttpSession httpSession,
                                                  @PathVariable(value = "id") int id){
        try{
            int res = applicationDao.updateStatusById(id, -1);
            if(res == 1)
                return new ResponseCode<String>(BizCode.SUCCESS, "操作成功！");
            else
//                return new ResponseCode<String>(BizCode.FAIL, "操作失败！");
                throw new RuntimeException("操作失败！");
        }
        catch (Exception e)
        {
//            return new ResponseCode<String>(BizCode.FAIL, "操作失败！");
//            throw new RuntimeException("操作失败！");
            throw new RuntimeException("操作失败！"+e.getMessage());

        }
    }

    //批准假期

    /**
     * 申请通过（教导主任，校长，副校长可）：审批
     * @param httpSession
     * @param id
     * @return
     */
    @RequestMapping(value = "/approve/{id}", method = RequestMethod.PUT,produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public ResponseCode<String> approveApplication(HttpSession httpSession,
                                                   @PathVariable(value = "id") int id){
        try{
            int res = applicationDao.updateStatusById(id, 1);
            if(res == 1)
                return new ResponseCode<String>(BizCode.SUCCESS, "操作成功！");
            else
//                return new ResponseCode<String>(BizCode.FAIL, "操作失败！");
                throw new RuntimeException("操作失败！");
        }
        catch (Exception e)
        {
//            throw new RuntimeException("操作失败！");
            throw new RuntimeException("操作失败！"+e.getMessage());

//            return new ResponseCode<String>(BizCode.FAIL, "操作失败！");
        }
    }



    /**
     * 用户销假（自己或者管理员可）
     * @param uid
     * @param id
     * @return
     */
    @Owner(requireOwner = true) // 自己
    @PrivateResource(requireOwner = true)  // 私有资源
    @RequiresRole(value = {"xiaozhang","fuxiaozhang","jiaodaozhuren"},logical = Logical.OR)
    @RequestMapping(value = "/{uid}/clear/{id}", method = RequestMethod.PUT,produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public ResponseCode<String> clearApplication(HttpSession httpSession,
                                                 @PathVariable("uid")String uid,
                                                 @PathVariable(value = "id") int id){
        try{
            int res = applicationDao.updateStatusById(id, 2);
            if(res == 1)
            {
                return new ResponseCode<String>(BizCode.SUCCESS, "操作成功！");
            }
            else
                throw new RuntimeException("操作失败！");
        }
        catch (Exception e)
        {
//            return new ResponseCode<String>(BizCode.FAIL, "操作失败！");
//            throw new RuntimeException("操作失败！");
            throw new RuntimeException("操作失败！"+e.getMessage());
        }
    }
}