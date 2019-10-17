package com.goodcub.vci.controller.admin;

import com.goodcub.common.page.TableDataInfo;
import com.goodcub.common.utils.JsonResult;
import com.goodcub.vci.service.admin.MemberService;
import com.goodcub.vci.service.admin.NewsService;
import com.goodcub.vci.service.admin.VendorService;
import com.goodcub.vci.service.admin.VideosService;
import com.goodcub.vci.service.admin.MainService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Luo.z.x
 * @Description: TODO
 * @Date 2019/9/19
 * @Version V1.0
 **/
@Controller
@RequestMapping("manage")
public class MainController {

    @Resource
    MemberService memberService;

    @Resource
    VendorService vendorService;

    @Resource
    VideosService videosService;

    @Resource
    NewsService newsService;

    @Resource
    MainService  mainService;

    @GetMapping({"/", "/index"})
    public String to_main(){
        return "admin/index";
    }

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
    public TableDataInfo lastNews(){
        Map<String,Object> param = new HashMap<>();
        return mainService.queryIndexNewsList(param,1,5);
    }

    // 视频5条,按观看次数降序
    @GetMapping({"/indexVideosList"})
    @ResponseBody
    public TableDataInfo lastVideos(){
        Map<String,Object> param = new HashMap<>();
        return mainService.queryIndexVideosList(param,1,5);
    }

    // 会员5条,注册时间
    @GetMapping({"/indexMemberList"})
    @ResponseBody
    public TableDataInfo lastMember(){
        Map<String,Object> param = new HashMap<>();
        return mainService.queryIndexMembersList(param,1,5);
    }

}
