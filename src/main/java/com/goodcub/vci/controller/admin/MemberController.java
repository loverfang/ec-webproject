package com.goodcub.vci.controller.admin;

import com.goodcub.common.enums.StatusEnum;
import com.goodcub.common.utils.DateUtil;
import com.goodcub.common.utils.JsonResult;
import com.goodcub.common.utils.MD5Util;
import com.goodcub.vci.entity.Members;
import com.goodcub.vci.service.admin.MemberService;
import com.goodcub.vci.vo.admin.MembersVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName MemberController
 * @Description TODO
 * @Author Luo.z.x
 * @Date 2019/9/258:15
 * @Version 1.0
 */
@Controller
@RequestMapping("api/manage")
public class MemberController {

    @Resource
    MemberService memberService;

    /**
     * 获取用户列表
     * @return
     */
    @GetMapping("/memberList")
    @ResponseBody
    public JsonResult memberList(
            @RequestParam(value = "name", required = false)String name,
            @RequestParam(value = "page", required = false)Integer page,
            @RequestParam(value = "limit", required = false)Integer limit){
        Map<String,Object> params = new HashMap<>();
        params.put("name",name);
        return JsonResult.success(memberService.queryMembersList(params, page, limit));
    }

    /**
     * 获取用户详情
     * @return
     */
    @GetMapping("/memberInfo")
    public JsonResult memberInfo(){
        return JsonResult.success();
    }

    /**
     * 添加用户信息
     * @return
     */
    @PostMapping("/saveMember")
    @ResponseBody
    public JsonResult saveMmember(@RequestBody MembersVO membersVO){
        Members member = new Members();
        member.setUsername(membersVO.getUsername());
        member.setPassword(MD5Util.MD5(membersVO.getPassword()));
        member.setUsertype(membersVO.getUsertype());
        member.setCardnum(membersVO.getCardnum());
        member.setName(membersVO.getName());
        member.setPhone(membersVO.getPhone());
        member.setEmail(membersVO.getEmail());
        member.setCompany(membersVO.getCompany());
        member.setJobtitle(membersVO.getJobtitle());
        member.setVciguwen(membersVO.getVciguwen());
        member.setViewcount(membersVO.getViewcount());
        member.setTotaldays(membersVO.getTotaldays());
        member.setAvldays(membersVO.getAvldays());
        member.setStatus(StatusEnum.NORMAL);
        member.setRegtime(DateUtil.parseDateToStr("yyyy-MM-dd HH:mm:ss",new Date()));
        member.setTxt1(membersVO.getTxt1());
        member.setTxt2(membersVO.getTxt2());
        member.setTxt3(membersVO.getTxt3());
        member.setTxt4(membersVO.getTxt4());
        member.setTxt5(membersVO.getTxt5());
        member.setTxt6(membersVO.getTxt6());
        member.setTxt7(membersVO.getTxt7());
        member.setTxt8(membersVO.getTxt8());
        member.setTxt9(membersVO.getTxt9());
        member.setTxt10(membersVO.getTxt10());
        member.setTxt11(membersVO.getTxt11());
        member.setTxt12(membersVO.getTxt12());

        return memberService.insertMember(member);
    }

    /**
     * 编辑用户信息
     * @return
     */
    @PostMapping("/editMember")
    @ResponseBody
    public JsonResult editMmember(@RequestBody MembersVO membersVO){
        Members member = new Members();
        member.setMemid(membersVO.getMemid());
        // member.setUsername(membersVO.getUsername());
        member.setPassword(membersVO.getPassword());
        member.setUsertype(membersVO.getUsertype());
        member.setCardnum(membersVO.getCardnum());
        member.setName(membersVO.getName());
        // member.setPhone(membersVO.getPhone());
        // member.setEmail(membersVO.getEmail());
        member.setCompany(membersVO.getCompany());
        member.setJobtitle(membersVO.getJobtitle());
        member.setVciguwen(membersVO.getVciguwen());
        member.setViewcount(membersVO.getViewcount());
        member.setTotaldays(membersVO.getTotaldays());
        member.setAvldays(membersVO.getAvldays());
        member.setTxt1(membersVO.getTxt1());
        member.setTxt2(membersVO.getTxt2());
        member.setTxt3(membersVO.getTxt3());
        member.setTxt4(membersVO.getTxt4());
        member.setTxt5(membersVO.getTxt5());
        member.setTxt6(membersVO.getTxt6());
        member.setTxt7(membersVO.getTxt7());
        member.setTxt8(membersVO.getTxt8());
        member.setTxt9(membersVO.getTxt9());
        member.setTxt10(membersVO.getTxt10());
        member.setTxt11(membersVO.getTxt11());
        member.setTxt12(membersVO.getTxt12());
        return memberService.updateMember(member);
    }

    @PostMapping("/editMemberStatus")
    @ResponseBody
    public JsonResult updateMemberStatus(@RequestBody MembersVO membersVO){
        Members member = new Members();
        member.setMemid(membersVO.getMemid());
        member.setStatus(membersVO.getStatus());
        return memberService.updateMember(member);
    }
}
