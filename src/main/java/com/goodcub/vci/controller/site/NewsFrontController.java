package com.goodcub.vci.controller.site;

import com.goodcub.common.page.TableDataInfo;
import com.goodcub.vci.service.site.NewsFrontService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName NewsFrontController
 * @Description TODO
 * @Author Luo.z.x
 * @Date 2019/10/118:11
 * @Version 1.0
 */
@Controller
public class NewsFrontController {

    @Resource
    NewsFrontService newsFrontService;

    /**
     * Aboutus
     * @return
     */
    @RequestMapping("/aboutus")
    public String aboutus(HttpServletRequest request){
        // 加载数据
        request.setAttribute("article",newsFrontService.queryArticleInfo("ABOUTUS"));
        request.setAttribute("ntype", "aboutus");
        return "site/aboutus";
    }

    /**
     * Ourservices
     * @return
     */
    @RequestMapping("/ourservices")
    public String ourservices(HttpServletRequest request){
        // 加载数据
        request.setAttribute("article",newsFrontService.queryArticleInfo("OURSERVICES"));
        request.setAttribute("ntype", "ourservices");
        return "site/ourservices";
    }

    /**
     * Ourcilents
     * @return
     */
    @RequestMapping("/ourcilents")
    public String ourcilents(HttpServletRequest request){
        // 加载数据
        request.setAttribute("article",newsFrontService.queryArticleInfo("OURCILENTS"));
        request.setAttribute("ntype", "ourcilents");
        return "site/ourcilents";
    }

    /**
     * Contactus
     * @return
     */
    @RequestMapping("/contactus")
    public String contactus(HttpServletRequest request){
        // 加载数据
        request.setAttribute("article",newsFrontService.queryArticleInfo("CONTACTUS"));
        request.setAttribute("ntype", "contactus");
        return "site/contactus";
    }

    /**
     * Marketingwithus
     * @return
     */
    @RequestMapping("/marketingwithus")
    public String marketingwithus(HttpServletRequest request){
        // 加载数据
        request.setAttribute("article",newsFrontService.queryArticleInfo("MARKETINGWITHUS"));
        request.setAttribute("ntype", "marketingwithus");
        return "site/marketingwithus";
    }

    /**
     * Partners 列表
     * @return
     */
    @RequestMapping("/partners")
    public String partners( HttpServletRequest request,
            @RequestParam(value = "page", required = false)Integer page,
            @RequestParam(value = "limit", required = false)Integer limit){

        // 初始化分页默认数据
        if(page == null || "".equals(page)){
            page = 1;
        }

        if(limit == null || "".equals(limit)){
            limit = 10;
        }

        Map<String,Object> params = new HashMap<>();
        params.put("ntype","PARTNERS");

        TableDataInfo tableDataInfo = newsFrontService.queryPartenerFrontList(params, page, limit);

        request.setAttribute("data", tableDataInfo);
        request.setAttribute("ntype", "partners");
        return "site/partners";
    }

    /**
     * Insights 列表
     * @return
     */
    public String insights(){
        return "site/insightslist";
    }

    /**
     * Insights 详情
     * @return
     */
    public String insightsDetail(){
        return "site/insights_content";
    }

    /**
     * Events 列表
     * @return
     */
    public String events(){
        return null;
    }

    /**
     * Events 详情
     * @return
     */
    public String eventsDetail(){
        return null;
    }

    /**
     * Stories 列表
     * @return
     */
    public String stories(){
        return null;
    }

    /**
     * Stories 详情
     * @return
     */
    public String storiesDetail(){
        return null;
    }


}
