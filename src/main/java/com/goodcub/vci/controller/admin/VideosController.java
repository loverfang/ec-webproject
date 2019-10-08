package com.goodcub.vci.controller.admin;

import com.goodcub.common.utils.DateUtil;
import com.goodcub.common.utils.JsonResult;
import com.goodcub.vci.entity.Videos;
import com.goodcub.vci.service.admin.VideosService;
import com.goodcub.vci.vo.admin.VideosInfoVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

/**
 * @Author Luo.z.x
 * @Description: TODO
 * @Date 2019/9/23
 * @Version V1.0
 **/
@Controller
@RequestMapping("api/manage")
public class VideosController {

    @Resource
    VideosService videosService;

    @GetMapping("/videoList")
    @ResponseBody
    public JsonResult videoList(
        @RequestParam(value = "title", required = false)String title,
        @RequestParam(value = "page", required = false)Integer page,
        @RequestParam(value = "limit", required = false)Integer limit){

        Map<String,Object> params = new HashMap<>();
        params.put("title",title);
        return JsonResult.success(videosService.queryVideosList(params,page,limit));
    }

    @PostMapping("/saveVideo")
    @ResponseBody
    public JsonResult saveVideo(@RequestBody VideosInfoVO videosInfoVO){
        Videos videos = new Videos();
        videos.setTitle(videosInfoVO.getTitle());
        videos.setVurl(videosInfoVO.getVurl());
        videos.setNeedcount(videosInfoVO.getNeedcount());
        videos.setMemo(videosInfoVO.getMemo());

        videos.setSindex(1);
        videos.setViewcount(0);
        videos.setAddtime(DateUtil.parseDateToStr("yyyy-MM-dd HH:mm:ss",new Date()));
        videosService.saveVideos(videos);
        return JsonResult.success();
    }

    @PostMapping("/updateVideo")
    @ResponseBody
    public JsonResult updateVideo(@RequestBody VideosInfoVO videosInfoVO){
        Videos videos = new Videos();
        videos.setVid(videosInfoVO.getVid());
        videos.setTitle(videosInfoVO.getTitle());
        videos.setVurl(videosInfoVO.getVurl());
        videos.setNeedcount(videosInfoVO.getNeedcount());
        videos.setMemo(videosInfoVO.getMemo());
        videos.setCoverImg(videosInfoVO.getCoverImg());
        videosService.updateVideos(videos);
        return JsonResult.success();
    }

    @PostMapping("/updateVideoSindex")
    @ResponseBody
    public JsonResult updateVideoSindex(@RequestBody VideosInfoVO videosInfoVO){
        Videos videos = new Videos();
        videos.setVid(videosInfoVO.getVid());
        videos.setSindex(videosInfoVO.getSindex());
        videosService.updateVideos(videos);
        return JsonResult.success();
    }

    @PostMapping("/deleteVideos")
    @ResponseBody
    public JsonResult deleteVideos(@RequestBody Map<String,Object> data){
        // 获取传回来的id字符串
        String ids_str = data.get("vids").toString();
        if(ids_str == null || "".equals(ids_str)){
            return JsonResult.error("请选中需要删除的记录");
        }

        // 通过逗号分割字符串，获得所有的id，在mapper中通过mybatis提供的动态循环遍历并删除数组中对应id的值就行
        String[] idsArr = ids_str.split(",");
        // 根据自己的后台逻辑，调用service的方法，我就不写了

        List<Integer> idList = new ArrayList<>();
        if(idsArr != null && idsArr.length>0){
            for (String id: idsArr){
                if(id!=null) {
                    idList.add(Integer.valueOf(id));
                }
            }
        }

        videosService.deleteVideos(idList);
        return JsonResult.success();
    }

}
