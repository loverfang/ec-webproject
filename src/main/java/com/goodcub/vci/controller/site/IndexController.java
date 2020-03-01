package com.goodcub.vci.controller.site;

import com.goodcub.common.enums.NewsTypeEnum;
import com.goodcub.common.page.TableDataInfo;
import com.goodcub.vci.service.site.AdvertiseFrontService;
import com.goodcub.vci.service.site.NewsFrontService;
import com.goodcub.vci.service.site.TfcFrontService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Luo.z.x
 * @Description: 网站首页信息
 * @Date 2019/10/9
 * @Version V1.0
 **/
@Controller
public class IndexController {

    @Resource
    AdvertiseFrontService advertiseFrontService;

    @Resource
    NewsFrontService newsFrontService;

    @Resource
    TfcFrontService tfcFrontService;

    /**
     * 网站首页
     * @param request
     * @return
     */
    @RequestMapping({"/","/index"})
    public String home(HttpServletRequest request){
        Map<String, Object> newsParams= new HashMap<String, Object>();

        // Insights
        newsParams.put("ntype",  NewsTypeEnum.INSIGHTS);
        TableDataInfo insightsData = newsFrontService.queryNewsFrontList(newsParams,1,3);
        if(insightsData!=null){
            request.setAttribute("insightsList", insightsData.getItems());
        }else {
            request.setAttribute("insightsList", null);
        }

        // Events
        newsParams.put("ntype", NewsTypeEnum.EVENTS);
        TableDataInfo eventData =  newsFrontService.queryNewsFrontList(newsParams,1,3);
        if(eventData!=null){
            request.setAttribute("eventsList", eventData.getItems());
        }else {
            request.setAttribute("eventsList", null);
        }

        // Banner广告
        Map<String,Object> adParams = new HashMap<>();
        TableDataInfo adTableDataInfo = advertiseFrontService.queryAdvertiseFrontVOList(adParams,1,20);
        if(adTableDataInfo!=null) {
            request.setAttribute("adList", adTableDataInfo.getItems());
        }else {
            request.setAttribute("adList", null);
        }

        // 2020.3.1新增加功能
        // Lives
        newsParams.put("ntype", NewsTypeEnum.LIVES);
        TableDataInfo livesData =  newsFrontService.queryNewsFrontList(newsParams,1,1000);
        if(livesData!=null){
            request.setAttribute("livesList", livesData.getItems());
        }else {
            request.setAttribute("livesList", null);
        }

        // TFC类别列表
        Map<String,Object> tfcParams = new HashMap<>();
        TableDataInfo tfcTableDataInfo = tfcFrontService.queryTfcFrontVOList(tfcParams,1,1000);
        if(tfcTableDataInfo!=null) {
            request.setAttribute("tfcList", tfcTableDataInfo.getItems());
        }else {
            request.setAttribute("tfcList", null);
        }

        return "site/index";
    }

}
