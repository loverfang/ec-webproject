package com.goodcub.vci.controller.admin;

import com.goodcub.common.utils.JsonResult;
import com.goodcub.vci.service.admin.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
    @PostMapping("/saveMmember")
    public JsonResult saveMmember(){
        return JsonResult.success();
    }

    /**
     * 编辑用户信息
     * @return
     */
    @PostMapping("/editMmember")
    public JsonResult editMmember(){
        return JsonResult.success();
    }
}
