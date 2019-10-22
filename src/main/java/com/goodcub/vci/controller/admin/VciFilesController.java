package com.goodcub.vci.controller.admin;

import com.goodcub.common.enums.StatusEnum;
import com.goodcub.common.upload.FileResult;
import com.goodcub.common.utils.DateUtil;
import com.goodcub.common.utils.IdWorker;
import com.goodcub.common.utils.JsonResult;
import com.goodcub.vci.entity.VciFiles;
import com.goodcub.vci.service.admin.VciFilesService;
import com.goodcub.vci.vo.admin.VciFileSaveVO;
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
public class VciFilesController {

    @Resource
    VciFilesService vciFilesService;

    // 加载所有文件信息
    @GetMapping("/fileList")
    @ResponseBody
    public JsonResult photoList(
            @RequestParam(value = "name", required = false)String name,
            @RequestParam(value = "page", required = false)Integer page,
            @RequestParam(value = "limit", required = false)Integer limit){

        Map<String,Object> param = new HashMap<>();
        param.put("name",name);
        return JsonResult.success(vciFilesService.queryVciFilesList(param, page, limit));
    }

    @PostMapping("/saveFiles")
    @ResponseBody
    public JsonResult savePhotoList(@RequestBody VciFileSaveVO vciFileSaveVO){

        if(vciFileSaveVO==null){
            return JsonResult.error("请选择文件再上传");
        }

        if(vciFileSaveVO.getFileList() == null || vciFileSaveVO.getFileList().size() <= 0){
            return JsonResult.error("请选择文件再上传");
        }

        List<VciFiles> vciFileSaveList = new ArrayList<>();

        for (FileResult result: vciFileSaveVO.getFileList()){
            VciFiles vciFiles = new VciFiles();
            vciFiles.setFid(new IdWorker().nextId());
            vciFiles.setName(result.getFileName());
            vciFiles.setFilePath(result.getServerPath());
            vciFiles.setPsize(result.getFileSize());
            vciFiles.setExtName(result.getExtName());
            vciFiles.setStatus(StatusEnum.NORMAL);
            vciFiles.setDownCount(0);
            vciFiles.setUptime(DateUtil.parseDateToStr("yyyy-MM-dd HH:mm:ss",new Date()));

            vciFileSaveList.add(vciFiles);
        }
        vciFilesService.saveBatchVciFiles(vciFileSaveList);
        return JsonResult.success();
    }

    @PostMapping("/deleteVciFiles")
    @ResponseBody
    public JsonResult deleteFiles(@RequestBody Map<String,Object> data){
        // 获取传回来的id字符串
        String ids_str = data.get("fids").toString();
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
        vciFilesService.deleteVciFilesByFid(idList);
        return JsonResult.success();
    }

}
