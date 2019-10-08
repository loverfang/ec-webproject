package com.goodcub.vci.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.goodcub.common.enums.StatusEnum;
import com.goodcub.common.upload.FileResult;
import com.goodcub.common.utils.DateUtil;
import com.goodcub.common.utils.JsonResult;
import com.goodcub.vci.entity.NewsPdf;
import com.goodcub.vci.entity.NewsPhoto;
import com.goodcub.vci.service.admin.NewsPdfService;
import com.goodcub.vci.service.admin.NewsPhotoService;
import com.goodcub.vci.vo.admin.NewsPdfVO;
import com.goodcub.vci.vo.admin.NewsPhotoSaveVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

/**
 * @ClassName NewsPdfController
 * @Description TODO
 * @Author Luo.z.x
 * @Date 2019/10/616:21
 * @Version 1.0
 */
@Controller
@RequestMapping("api/manage")
public class NewsPhotoController {

    @Resource
    NewsPhotoService newsPhotoService;

    // 加载nid下的所有图片信息
    @GetMapping("/photoList")
    @ResponseBody
    public JsonResult queryPdfList(
            @RequestParam(value = "nid", required = false)String nid,
            @RequestParam(value = "ptitle", required = false)String ptitle,
            @RequestParam(value = "page", required = false)Integer page,
            @RequestParam(value = "limit", required = false)Integer limit){

        Map<String,Object> param = new HashMap<>();
        param.put("nid",nid);
        param.put("ptitle",ptitle);
        return JsonResult.success(newsPhotoService.queryNewsPhotoList(param, page, limit));
    }

    @PostMapping("/savePhotos")
    @ResponseBody
    public JsonResult savePhotoList(@RequestBody NewsPhotoSaveVO newsPhotoSaveVO){

        List<NewsPhoto> photoList = new ArrayList<>();
        if(newsPhotoSaveVO==null){
            return JsonResult.error("请选择图片文件再上传");
        }
        if(newsPhotoSaveVO.getPhotoList() == null || newsPhotoSaveVO.getPhotoList().size() <= 0){
            return JsonResult.error("请选择图片文件再上传");
        }

        for (FileResult result: newsPhotoSaveVO.getPhotoList()){
            NewsPhoto photo = new NewsPhoto();
            photo.setImgPath(result.getServerPath());
            photo.setDowncount(0);
            photo.setPsize(result.getFileSize());
            photo.setSindex(1);
            photo.setUptime(DateUtil.parseDateToStr("yyyy-MM-dd HH:mm:ss",new Date()));
            photo.setNid(newsPhotoSaveVO.getNid());
            photo.setPtitle(result.getFileName());

            photoList.add(photo);
        }

        newsPhotoService.saveBatchNewsPhoto( photoList );
        return JsonResult.success();
    }

//    @PostMapping("/updatePdfSindex")
//    @ResponseBody
//    public JsonResult updatePdfSindex(@RequestBody NewsPdfVO newsPdfVO){
//        NewsPdf newsPdf = new NewsPdf();
//        newsPdf.setPid(newsPdfVO.getPid());
//        newsPdf.setSindex(newsPdfVO.getSindex());
//        newsPdfService.updateNewsPdf(newsPdf);
//
//        return JsonResult.success();
//    }
//
//
//    @PostMapping("/deletePdf")
//    @ResponseBody
//    public JsonResult deleteVendor(@RequestBody Map<String,Object> data){
//        // 获取传回来的id字符串
//        String ids_str = data.get("pids").toString();
//        if(ids_str == null || "".equals(ids_str)){
//            return JsonResult.error("请选中需要删除的记录");
//        }
//
//        // 通过逗号分割字符串，获得所有的id，在mapper中通过mybatis提供的动态循环遍历并删除数组中对应id的值就行
//        String[] idsArr = ids_str.split(",");
//        // 根据自己的后台逻辑，调用service的方法，我就不写了
//
//        List<Integer> idList = new ArrayList<>();
//        if(idsArr != null && idsArr.length>0){
//            for (String id: idsArr){
//                if(id!=null) {
//                    idList.add(Integer.valueOf(id));
//                }
//            }
//        }
//
//        newsPdfService.deleteNewsPdfByPid(idList);
//        return JsonResult.success();
//    }

}
