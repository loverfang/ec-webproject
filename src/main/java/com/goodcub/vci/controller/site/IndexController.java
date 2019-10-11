package com.goodcub.vci.controller.site;

import com.goodcub.common.enums.NewsTypeEnum;
import com.goodcub.common.page.TableDataInfo;
import com.goodcub.vci.service.site.AdvertiseFrontService;
import com.goodcub.vci.service.site.NewsFrontService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Luo.z.x
 * @Description: TODO
 * @Date 2019/10/9
 * @Version V1.0
 **/
@Controller
public class IndexController {
    @Resource
    AdvertiseFrontService advertiseFrontService;

    @Resource
    NewsFrontService newsFrontService;

    /**
     * 网站首页
     * @param request
     * @return
     */
    @RequestMapping({"/","/index"})
    public String home(HttpServletRequest request){
        Map<String, Object> newsParams= new HashMap<String, Object>();
        //Insights
        newsParams.put("ntype",  NewsTypeEnum.INSIGHTS);
        TableDataInfo insightsData = newsFrontService.queryNewsFrontList(newsParams,1,3);
        if(insightsData!=null){
            request.setAttribute("insightsList", insightsData.getItems());
        }else {
            request.setAttribute("insightsList", null);
        }

        //Events
        newsParams.put("ntype", NewsTypeEnum.EVENTS);
        TableDataInfo eventData =  newsFrontService.queryNewsFrontList(newsParams,1,3);
        if(eventData!=null){
            request.setAttribute("eventsList", eventData.getItems());
        }else {
            request.setAttribute("eventsList", null);
        }

        //banner广告
        Map<String,Object> adParams = new HashMap<>();
        TableDataInfo adTableDataInfo = advertiseFrontService.queryAdvertiseFrontVOList(adParams,1,20);
        if(adTableDataInfo!=null) {
            request.setAttribute("adList", adTableDataInfo.getItems());
        }else {
            request.setAttribute("adList", null);
        }

        return "site/index";
    }

}
