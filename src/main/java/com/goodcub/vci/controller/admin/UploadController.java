package com.goodcub.vci.controller.admin;

import com.goodcub.common.upload.FileuploadUtil;
import com.goodcub.common.upload.PropertiesUtils;
import com.goodcub.common.utils.DateUtil;
import com.goodcub.common.utils.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

/**
 * 参考方法 https://blog.csdn.net/chuoerqiao8180/article/details/100979152
 * @Author Luo.z.x
 * @Description: 文件上传公共方法
 * @Date 2019/9/29
 * @Version V1.0
 **/
@Controller
@RequestMapping("api/manage")
public class UploadController {

    /**
     * 方法描述：文件上传，图片也可以使用，但是图片不会被压缩.
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/uploadFile")
    @ResponseBody
    public JsonResult uploadFile(@RequestParam("file")MultipartFile file) throws IOException {
        return JsonResult.success(FileuploadUtil.saveFile(file, "/upload/files/" + DateUtil.parseDateToStr(DateUtil.YYYYMM, new Date()), PropertiesUtils.getInstance().getFileExtension()));
    }

    /**
     * 方法描述：图片上传，只能给图片使用，其他文件调用会异常.
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/uploadImage")
    @ResponseBody
    public JsonResult uploadImage(@RequestParam("file")MultipartFile file) throws IOException {
        return JsonResult.success(FileuploadUtil.saveImage(file, "/upload/images/" + DateUtil.parseDateToStr(DateUtil.YYYYMM, new Date()), PropertiesUtils.getInstance().getImageExtension()));
    }

    /**
     * 方法描述：图片上传，只能给图片使用，其他文件调用会异常.
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/uploadEditorImage")
    @ResponseBody
    public JsonResult uploadEditorImage(@RequestParam("file")MultipartFile file) throws IOException {
        return JsonResult.success(FileuploadUtil.saveImage(file, "/upload/editor/" + DateUtil.parseDateToStr(DateUtil.YYYYMM, new Date()), PropertiesUtils.getInstance().getImageExtension()));
    }
}
