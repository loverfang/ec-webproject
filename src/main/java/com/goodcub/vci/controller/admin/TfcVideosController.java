package com.goodcub.vci.controller.admin;

import com.goodcub.common.utils.DateUtil;
import com.goodcub.common.utils.JsonResult;
import com.goodcub.vci.entity.TfcVideos;
import com.goodcub.vci.entity.Videos;
import com.goodcub.vci.service.admin.TfcVideosService;
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
public class TfcVideosController {

    @Resource
    TfcVideosService tfcVideosService;

    @GetMapping("/tfcVideoList")
    @ResponseBody
    public JsonResult videoList(
        @RequestParam(value = "typeId", required = false)Integer typeId,
        @RequestParam(value = "title", required = false)String title,
        @RequestParam(value = "page", required = false)Integer page,
        @RequestParam(value = "limit", required = false)Integer limit){

        Map<String,Object> params = new HashMap<>();
        params.put("title",title);
        params.put("typeId",typeId);
        return JsonResult.success(tfcVideosService.queryTfcVideosList(params,page,limit));
    }

    @PostMapping("/saveTfcVideo")
    @ResponseBody
    public JsonResult saveVideo(@RequestBody TfcVideos tfcVideosVo){
        TfcVideos tfcVideos = new TfcVideos();
        tfcVideos.setTypeId(tfcVideosVo.getTypeId());
        tfcVideos.setTitle(tfcVideosVo.getTitle());
        tfcVideos.setVurl(tfcVideosVo.getVurl());
        tfcVideos.setNeedcount(tfcVideosVo.getNeedcount());
        tfcVideos.setMemo(tfcVideosVo.getMemo());
        tfcVideos.setCoverImg(tfcVideosVo.getCoverImg());

        tfcVideos.setSindex(1);
        tfcVideos.setViewcount(0);
        tfcVideos.setAddtime(DateUtil.parseDateToStr("yyyy-MM-dd HH:mm:ss",new Date()));
        tfcVideosService.saveTfcVideos(tfcVideos);
        return JsonResult.success();
    }

    @PostMapping("/updateTfcVideo")
    @ResponseBody
    public JsonResult updateVideo(@RequestBody TfcVideos tfcVideosVo){
        TfcVideos tfcvideos = new TfcVideos();
        tfcvideos.setVid(tfcVideosVo.getVid());
        tfcvideos.setTitle(tfcVideosVo.getTitle());
        tfcvideos.setVurl(tfcVideosVo.getVurl());
        tfcvideos.setNeedcount(tfcVideosVo.getNeedcount());
        tfcvideos.setMemo(tfcVideosVo.getMemo());
        tfcvideos.setCoverImg(tfcVideosVo.getCoverImg());
        tfcVideosService.updateTfcVideos(tfcvideos);
        return JsonResult.success();
    }

    @PostMapping("/updateTfcVideoSindex")
    @ResponseBody
    public JsonResult updateVideoSindex(@RequestBody TfcVideos tfcVideosVo){
        TfcVideos tfcvideos = new TfcVideos();
        tfcvideos.setVid(tfcVideosVo.getVid());
        tfcvideos.setSindex(tfcVideosVo.getSindex());
        tfcVideosService.updateTfcVideos(tfcvideos);
        return JsonResult.success();
    }

    @PostMapping("/deleteTfcVideos")
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

        tfcVideosService.deleteTfcVideos(idList);
        return JsonResult.success();
    }
}
