package com.goodcub.vci.controller.site;

import com.goodcub.common.enums.NewsTypeEnum;
import com.goodcub.common.page.TableDataInfo;
import com.goodcub.vci.service.site.NewsAdFrontService;
import com.goodcub.vci.service.site.NewsFrontService;
import com.goodcub.vci.service.site.NewsPdfFrontService;
import com.goodcub.vci.service.site.NewsPhotoFrontService;
import com.goodcub.vci.vo.site.NewsAdListFrontVO;
import com.goodcub.vci.vo.site.NewsFrontVO;
import com.goodcub.vci.vo.site.NewsPhotoListFrontVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
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

    @Resource
    NewsPdfFrontService newsPdfFrontService;

    @Resource
    NewsPhotoFrontService newsPhotoFrontService;

    @Resource
    NewsAdFrontService newsAdFrontService;

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
            limit = 6;
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
    @RequestMapping("/insights")
    public String insights(HttpServletRequest request,
            @RequestParam(value = "page", required = false)Integer page,
            @RequestParam(value = "limit", required = false)Integer limit){
        // 初始化分页默认数据
        if(page == null || "".equals(page)){
            page = 1;
        }

        if(limit == null || "".equals(limit)){
            limit = 6;
        }

        Map<String,Object> params = new HashMap<>();
        params.put("ntype", "INSIGHTS");
        TableDataInfo tableDataInfo = newsFrontService.queryNewsFrontList(params, page, limit);

        request.setAttribute("data", tableDataInfo);
        request.setAttribute("ntype", "insights");
        return "site/insightslist";
    }

    /**
     * Events 列表
     * @return
     */
    @RequestMapping("/events")
    public String events(HttpServletRequest request,
       @RequestParam(value = "page", required = false)Integer page,
       @RequestParam(value = "limit", required = false)Integer limit){

        // 初始化分页默认数据
        if(page == null || "".equals(page)){
            page = 1;
        }

        if(limit == null || "".equals(limit)){
            limit = 6;
        }

        Map<String,Object> params = new HashMap<>();
        params.put("ntype", "EVENTS");
        TableDataInfo tableDataInfo = newsFrontService.queryNewsFrontList(params, page, limit);

        request.setAttribute("data", tableDataInfo);
        request.setAttribute("ntype", "events");
        return "site/eventslist";
    }

    /**
     * Stories 列表
     * @return
     */
    @RequestMapping("/stories")
    public String stories(HttpServletRequest request,
       @RequestParam(value = "page", required = false)Integer page,
       @RequestParam(value = "limit", required = false)Integer limit){

        // 初始化分页默认数据
        if(page == null || "".equals(page)){
        page = 1;
        }

        if(limit == null || "".equals(limit)){
        limit = 6;
        }

        Map<String,Object> params = new HashMap<>();
        params.put("ntype", "STORIES");
        TableDataInfo tableDataInfo = newsFrontService.queryNewsFrontList(params, page, limit);

        request.setAttribute("data", tableDataInfo);
        request.setAttribute("ntype", "stories");
        return "site/storieslist";
    }

    /**
     * Insights 详情
     * @return
     */
    @GetMapping("/insights/{nid}.html")
    public String insightsDetail(HttpServletRequest request, @PathVariable("nid") Long nid){

        // 查询详情
        NewsFrontVO newsDetail = newsFrontService.queryNewsFrontInfo(nid);

        // 更新阅读次数
        newsFrontService.updateViewCount(nid);

        // 获得新闻对应的PDF文件信息,只取得一条
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("nid", newsDetail.getNid());
        param.put("source", "news");
        TableDataInfo pdfTableDataInfo = newsPdfFrontService.queryNewsPdfFrontList(param, 1 ,1);

        request.setAttribute("pdfList", pdfTableDataInfo.getItems());
        request.setAttribute("newsDetail", newsDetail);
        request.setAttribute("newstype", NewsTypeEnum.INSIGHTS);
        request.setAttribute("ntype","insights");

        return "site/insights_content";
    }

    /**
     * Events 详情
     * @return
     */
    @GetMapping("/events/{nid}.html")
    public String eventsDetail(HttpServletRequest request, @PathVariable("nid") Long nid){

        // 查询详情
        NewsFrontVO newsDetail = newsFrontService.queryNewsFrontInfo(nid);

        // 更新阅读次数
        newsFrontService.updateViewCount(nid);

        // 获得新闻对应的PDF文件信息,只取得一条
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("nid", newsDetail.getNid());
        param.put("source", "news");
        TableDataInfo pdfTableDataInfo = newsPdfFrontService.queryNewsPdfFrontList(param, 1 ,1);

        request.setAttribute("pdfList", pdfTableDataInfo.getItems());
        request.setAttribute("newsDetail", newsDetail);
        request.setAttribute("newstype", NewsTypeEnum.EVENTS);
        request.setAttribute("ntype","events");

        // 图片列表(全部图片)
        List<NewsPhotoListFrontVO> photoList = newsPhotoFrontService.queryNewsPhotoFrontList(nid);
        request.setAttribute("photos", photoList);

        // 广告列表(全部广告)
        List<NewsAdListFrontVO> adList = newsAdFrontService.queryNewsAdFrontList( nid );
        request.setAttribute("advertise", adList);

        return "site/events_content";
    }

    /**
     * Stories 详情
     * @return
     */
    @GetMapping("/stories/{nid}.html")
    public String storiesDetail(HttpServletRequest request, @PathVariable("nid") Long nid){

        // 查询详情
        NewsFrontVO newsDetail = newsFrontService.queryNewsFrontInfo(nid);

        // 更新阅读次数
        newsFrontService.updateViewCount(nid);

        // 获得新闻对应的PDF文件信息,只取得一条
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("nid", newsDetail.getNid());
        param.put("source", "news");
        TableDataInfo pdfTableDataInfo = newsPdfFrontService.queryNewsPdfFrontList(param, 1 ,1);

        request.setAttribute("pdfList", pdfTableDataInfo.getItems());
        request.setAttribute("newsDetail", newsDetail);
        request.setAttribute("newstype", NewsTypeEnum.INSIGHTS);
        request.setAttribute("ntype","insights");

        // 图片列表
        List<NewsPhotoListFrontVO> photoList = newsPhotoFrontService.queryNewsPhotoFrontList(nid);
        request.setAttribute("photos", photoList);

        return "site/stories_content";
    }

}
