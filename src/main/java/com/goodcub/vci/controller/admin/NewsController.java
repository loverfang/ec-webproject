package com.goodcub.vci.controller.admin;

import com.goodcub.common.utils.IdWorker;
import com.goodcub.common.utils.JsonResult;
import com.goodcub.vci.entity.News;
import com.goodcub.vci.entity.NewsExt;
import com.goodcub.vci.service.admin.NewsService;
import com.goodcub.vci.vo.admin.NewsInfoVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @ClassName NewsController
 * @Description TODO
 * @Author Luo.z.x
 * @Date 2019/9/277:32
 * @Version 1.0
 */
@Controller
@RequestMapping("api/manage")
public class NewsController {

    @Resource
    NewsService newsService;

    // 通用的根据ntype加载新闻列表
    @GetMapping("/newsList")
    @ResponseBody
    public JsonResult queryNewsList(
        @RequestParam(value = "ntype", required = false)String ntype,
        @RequestParam(value = "title", required = false)String title,
        @RequestParam(value = "page", required = false)Integer page,
        @RequestParam(value = "limit", required = false)Integer limit){

        if(ntype==null || "".equals(ntype)){
            return JsonResult.error("未指定新闻类别无法获取数据");
        }

        Map<String,Object> param = new HashMap<>();
        param.put("ntype",ntype.toUpperCase());
        param.put("title",title);
        return JsonResult.success(newsService.queryNewsList(param, page, limit));
    }

    // 通用的修改新闻排序索引
    @PostMapping("/updateNewsSindex")
    @ResponseBody
    public JsonResult updateNewsSindex(@RequestBody NewsInfoVO newsInfoVO){
        News news = new News();
        news.setNid(newsInfoVO.getNid());
        news.setSindex(newsInfoVO.getSindex());

        newsService.updateNews(news);
        return JsonResult.success();
    }

    // 删除新闻信息: 删除新闻基本信息,扩展信息,图片信息,pdf信息
    @PostMapping("/deleteAllNews")
    @ResponseBody
    public JsonResult delete(@RequestBody Map<String,Object> data){
        // 获取传回来的id字符串
        String ids_str = data.get("nids").toString();
        if(ids_str == null || "".equals(ids_str)){
            return JsonResult.error("请选中需要删除的记录");
        }

        // 通过逗号分割字符串，获得所有的id，在mapper中通过mybatis提供的动态循环遍历并删除数组中对应id的值就行
        String[] idsArr = ids_str.split(",");
        // 根据自己的后台逻辑，调用service的方法，我就不写了

        List<Long> idList = new ArrayList<>();
        if(idsArr != null && idsArr.length>0){
            for (String id: idsArr){
                if(id!=null) {
                    idList.add(Long.valueOf(id));
                }
            }
        }
        newsService.deleteNews(idList);

        return JsonResult.success();
    }

    // 通用的查询新闻详情信息
    @GetMapping("/newsInfo")
    @ResponseBody
    public JsonResult queryNewsInfo(Long nid){
        return JsonResult.success(newsService.queryNewsInfoByNid(nid));
    }

    // 添加Insights
    @PostMapping("/createInsights")
    @ResponseBody
    public JsonResult createInsights(@RequestBody NewsInfoVO newsInfoVO){
        News news = new News();
        Long newId = new IdWorker().nextId();
        news.setNid(newId);
        news.setNtype(newsInfoVO.getNtype());
        news.setAuthor(newsInfoVO.getAuthor());
        news.setTitle(newsInfoVO.getTitle());
        news.setContent(newsInfoVO.getContent());
        news.setCoverImg(newsInfoVO.getCoverImg());
        news.setAuthorImg(newsInfoVO.getAuthorImg());
        news.setPtitle(newsInfoVO.getPtitle());
        news.setPkeywords(newsInfoVO.getPkeywords());
        news.setPdescription(newsInfoVO.getPdescription());

        NewsExt newsExt = new NewsExt();
        newsExt.setNid(newId);
        newsExt.setNdigest(newsInfoVO.getNdigest());
        newsExt.setNlable(newsInfoVO.getNlable());
        newsExt.setEndate(newsInfoVO.getEndate());

        newsService.insertNews(news, newsExt);
        return JsonResult.success();
    }

    // 修改Insights
    @PostMapping("/updateInsights")
    @ResponseBody
    public JsonResult updateInsights(@RequestBody NewsInfoVO newsInfoVO){
        News news = new News();
        news.setNid(newsInfoVO.getNid());
        news.setNtype(newsInfoVO.getNtype());
        news.setAuthor(newsInfoVO.getAuthor());
        news.setTitle(newsInfoVO.getTitle());
        news.setContent(newsInfoVO.getContent());
        news.setCoverImg(newsInfoVO.getCoverImg());
        news.setAuthorImg(newsInfoVO.getAuthorImg());
        news.setPtitle(newsInfoVO.getPtitle());
        news.setPkeywords(newsInfoVO.getPkeywords());
        news.setPdescription(newsInfoVO.getPdescription());

        NewsExt newsExt = new NewsExt();
        newsExt.setNid(newsInfoVO.getNid());
        newsExt.setNdigest(newsInfoVO.getNdigest());
        newsExt.setNlable(newsInfoVO.getNlable());
        newsExt.setEndate(newsInfoVO.getEndate());

        newsService.updateNews(news, newsExt);
        return JsonResult.success();
    }

    // 添加events
    // 修改events

    // 添加Partner
    @PostMapping("/createPartner")
    @ResponseBody
    public JsonResult createPartner(@RequestBody NewsInfoVO newsInfoVO){
        News news = new News();
        Long newId = new IdWorker().nextId();
        news.setNid(newId);
        news.setNtype(newsInfoVO.getNtype());
        news.setAuthor(newsInfoVO.getAuthor());
        news.setTitle(newsInfoVO.getTitle());
        news.setContent(newsInfoVO.getContent());
        news.setCoverImg(newsInfoVO.getCoverImg());
        news.setPtitle(newsInfoVO.getPtitle());
        news.setPkeywords(newsInfoVO.getPkeywords());
        news.setPdescription(newsInfoVO.getPdescription());

        newsService.insertNews(news, null);
        return JsonResult.success();
    }

    // 修改partner
    @PostMapping("/updatePartner")
    @ResponseBody
    public JsonResult updatePartner(@RequestBody NewsInfoVO newsInfoVO){
        News news = new News();
        news.setNid(newsInfoVO.getNid());
        news.setNtype(newsInfoVO.getNtype());
        news.setAuthor(newsInfoVO.getAuthor());
        news.setTitle(newsInfoVO.getTitle());
        news.setContent(newsInfoVO.getContent());
        news.setCoverImg(newsInfoVO.getCoverImg());
        news.setPtitle(newsInfoVO.getPtitle());
        news.setPkeywords(newsInfoVO.getPkeywords());
        news.setPdescription(newsInfoVO.getPdescription());

        newsService.updateNews(news, null);
        return JsonResult.success();
    }

    // 添加Stories
    @PostMapping("/createStories")
    @ResponseBody
    public JsonResult createStories(@RequestBody NewsInfoVO newsInfoVO){
        News news = new News();
        Long newId = new IdWorker().nextId();
        news.setNid(newId);
        news.setNtype(newsInfoVO.getNtype());
        news.setAuthor(newsInfoVO.getAuthor());
        news.setTitle(newsInfoVO.getTitle());
        news.setCoverImg(newsInfoVO.getCoverImg());

        news.setPtitle(newsInfoVO.getPtitle());
        news.setPkeywords(newsInfoVO.getPkeywords());
        news.setPdescription(newsInfoVO.getPdescription());

        NewsExt newsExt = new NewsExt();
        newsExt.setNid(newId);
        newsExt.setNdigest(newsInfoVO.getNdigest());
        newsExt.setNlable(newsInfoVO.getNlable());
        newsExt.setEndate(newsInfoVO.getEndate());
        newsExt.setProvince(newsInfoVO.getProvince());
        newsExt.setCity(newsInfoVO.getCity());
        newsExt.setVideo(newsInfoVO.getVideo());
        newsExt.setTxt1(newsInfoVO.getTxt1());
        newsExt.setTxt2(newsInfoVO.getTxt2());
        newsExt.setTxt3(newsInfoVO.getTxt3());

        newsService.insertNews(news, newsExt);
        return JsonResult.success();
    }

    // 修改Stories
    @PostMapping("/updateStories")
    @ResponseBody
    public JsonResult updateStories(@RequestBody NewsInfoVO newsInfoVO){
        News news = new News();
        news.setNid(newsInfoVO.getNid());
        news.setNtype(newsInfoVO.getNtype());
        news.setAuthor(newsInfoVO.getAuthor());
        news.setTitle(newsInfoVO.getTitle());
        news.setCoverImg(newsInfoVO.getCoverImg());

        news.setPtitle(newsInfoVO.getPtitle());
        news.setPkeywords(newsInfoVO.getPkeywords());
        news.setPdescription(newsInfoVO.getPdescription());

        NewsExt newsExt = new NewsExt();
        newsExt.setNid(newsInfoVO.getNid());
        newsExt.setNdigest(newsInfoVO.getNdigest());
        newsExt.setNlable(newsInfoVO.getNlable());
        newsExt.setEndate(newsInfoVO.getEndate());
        newsExt.setProvince(newsInfoVO.getProvince());
        newsExt.setCity(newsInfoVO.getCity());
        newsExt.setVideo(newsInfoVO.getVideo());
        newsExt.setTxt1(newsInfoVO.getTxt1());
        newsExt.setTxt2(newsInfoVO.getTxt2());
        newsExt.setTxt3(newsInfoVO.getTxt3());

        newsService.updateNews(news, newsExt);
        return JsonResult.success();
    }

}
