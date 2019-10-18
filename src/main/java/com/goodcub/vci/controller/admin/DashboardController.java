package com.goodcub.vci.controller.admin;

import com.goodcub.common.page.TableDataInfo;
import com.goodcub.common.utils.JsonResult;
import com.goodcub.vci.service.admin.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName DashboardController
 * @Description TODO
 * @Author Luo.z.x
 * @Date 2019/10/187:28
 * @Version 1.0
 */
@Controller
@RequestMapping("api/manage")
public class DashboardController {

    @Resource
    MemberService memberService;

    @Resource
    VendorService vendorService;

    @Resource
    VideosService videosService;

    @Resource
    NewsService newsService;

    @Resource
    MainService mainService;

    /**
     * 统计总数
     * @return
     */
    @GetMapping({"/groupData"})
    @ResponseBody
    public JsonResult goupData(){

        Map<String,Integer> data = new HashMap<>();
        // 会员数量
        data.put("memberCount",memberService.countTotal());
        // 供应商
        data.put("vendorCount",vendorService.countTotal());
        // 新闻总数
        data.put("newsCount",newsService.countTotal());
        // 视频总数
        data.put("videosCount",videosService.countTotal());

        return JsonResult.success(data);
    }

    // 新闻5条,按观看次数降序
    @GetMapping({"/indexNewsList"})
    @ResponseBody
    public JsonResult lastNews(){
        Map<String,Object> param = new HashMap<>();
        return JsonResult.success(mainService.queryIndexNewsList(param,1,5));
    }

    // 视频5条,按观看次数降序
    @GetMapping({"/indexVideosList"})
    @ResponseBody
    public JsonResult lastVideos(){
        Map<String,Object> param = new HashMap<>();
        return JsonResult.success(mainService.queryIndexVideosList(param,1,5));
    }

    // 会员5条,注册时间
    @GetMapping({"/indexMemberList"})
    @ResponseBody
    public JsonResult lastMember(){
        Map<String,Object> param = new HashMap<>();
        return JsonResult.success(mainService.queryIndexMembersList(param,1,5));
    }

}
