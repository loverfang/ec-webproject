package com.goodcub.vci.controller.admin;

import com.goodcub.common.upload.FileuploadUtil;
import com.goodcub.common.utils.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
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
     * @param multipart
     * @return
     * @throws IOException
     */
    @PostMapping("/uploadFile")
    public JsonResult uploadFile(MultipartFile multipart) throws IOException {
        return JsonResult.success(FileuploadUtil.saveFile(multipart, FileuploadUtil.ATTACHMENT_GAIN_PATH, FileuploadUtil.FILE_EXTENSION));
    }

    /**
     * 方法描述：图片上传，只能给图片使用，其他文件调用会异常.
     * @param multipart
     * @return
     * @throws IOException
     */
    @PostMapping("/uploadImage")
    public JsonResult uploadImage( MultipartFile multipart) throws IOException {
        return JsonResult.success(FileuploadUtil.saveImage(multipart, FileuploadUtil.ATTACHMENT_GAIN_PATH, FileuploadUtil.IMAGE_EXTENSION));
    }

}
