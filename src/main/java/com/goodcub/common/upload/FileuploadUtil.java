package com.goodcub.common.upload;

import com.goodcub.common.utils.DateUtil;
import com.goodcub.common.utils.IdUtil;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


/**
 * @Author Luo.z.x
 * @Description: 文件上传工具类
 * @Date 2019/9/29
 * @Version V1.0
 **/
public class FileuploadUtil {
    /////////////////////////////////// 注入文件上传控制配置属性 ////////////////////////////////////////////
    //是否是调试
    public static Boolean IS_DEBUG;
    @Value("${upload.isDebug}")
    public void setIsDebug(Boolean isDebug){
        IS_DEBUG = isDebug;
    }

    //附件地址
    public static String ATTACHMENT_SERVER;
    @Value("${upload.attachmentServer}")
    public void setAttachmentServer(String attachmentServer){
        ATTACHMENT_SERVER = attachmentServer;
    }

    //服务器静态文件地址
    public static String ATTACHMENT_PATH;
    @Value("${upload.attachmentPath}")
    public void setAttachmentPath(String attachmentPath){
        ATTACHMENT_PATH = attachmentPath;
    }

    //服务器存储文件的地址
    public static String ATTACHMENT_GAIN_PATH;
    @Value("${upload.attachmentGainPath}")
    public void setAttachmentGainPath(String attachmentGainPath){
        ATTACHMENT_GAIN_PATH = attachmentGainPath;
    }

    //允许上传的文件类型
    public static String FILE_EXTENSION;
    @Value("${upload.fileExtension}")
    public void setFileExtension(String fileExtension){
        FILE_EXTENSION = fileExtension;
    }

    //允许上传的图片类型
    public static String IMAGE_EXTENSION;
    @Value("${upload.fileExtension}")
    public void setImageExtension(String imageExtension){
        IMAGE_EXTENSION = imageExtension;
    }
    /////////////////////////////////// 注入文件上传控制配置属性 完成////////////////////////////////////////////
    /////////////////////////////////// 注入文件上传控制配置属性 完成////////////////////////////////////////////

    /**
     * 方法描述：根据文件的绝对路径创建一个文件对象.
     * 创建时间：2018-10-19 09:32:34
     *
     * @param filePath 文件的绝对路径
     * @return 返回创建的这个文件对象
     * @author "lixingwu"
     */
    public static File createFile(String filePath) throws IOException {
        // 获取文件的完整目录
        String fileDir = FilenameUtils.getFullPath(filePath);
        // 判断目录是否存在，不存在就创建一个目录
        File file = new File(fileDir);
        if (!file.isDirectory()) {
            //创建失败返回null
            if (!file.mkdirs()) {
                throw new IOException("文件目录创建失败...");
            }
        }
        // 判断这个文件是否存在，不存在就创建
        file = new File(filePath);
        if (!file.exists()) {
            if (!file.createNewFile()) {
                throw new IOException("目标文件创建失败...");
            }
        }
        return file;
    }

    /**
     * 方法描述：判断extension中是否存在extName
     * 创建时间：2018-10-20 20:46:18
     *
     * @param extension 使用逗号隔开的字符串，精确匹配例如：txt,jpg,png,zip
     * @param extName   文件的后缀名
     * @author "lixingwu"
     */
    private static void isContains(String extension, String extName) {
        if (StringUtils.isNotEmpty(extension)) {
            // 切割文件扩展名
            String[] exts = StringUtils.split(extension, ",");
            if (ArrayUtils.isNotEmpty(exts)) {
                assert exts != null;
                List<String> extList = Arrays.asList(exts);
                //判断
                if (!extList.contains(extName)) {
                    throw new RuntimeException("上传文件的类型只能是：" + extension);
                }
            } else {
                // 判断文件的后缀名是否为extension
                if (!extension.equalsIgnoreCase(extName)) {
                    throw new RuntimeException("上传文件的类型只能是：" + extension);
                }
            }
        }
    }

    /**
     * 方法描述：用google的Thumbnails插件压缩,图片大小,或者图片宽度等信息
     * 创建时间：2018-10-20 20:46:18
     *
     * @param serverPath 图片的绝对路径
     * @param childFile  子文件夹
     * @param extName    文件的后缀
     * @author "lixingwu"
     */
    private static String thumbnails(String serverPath, String childFile, String extName)
            throws IOException {

        // 压缩后的相对路径文件名
        String toFilePath = getDestPath(childFile, extName);

        // scale：图片缩放比例
        // outputQuality：图片压缩比例
        // toFile：图片位置
        // outputFormat：文件输出后缀名

        //获取图片后缀名,判断如果是png的话就不进行格式转换,因为Thumbnails存在转png->jpg图片变红bug
        //        if (!"png".equals(extName)) {
        //            Thumbnails.of(contextPath).size(1920, 1080).outputQuality(1f).outputFormat("jpg").toFile(contextPath);
        //        } else {
        //            Thumbnails.of(contextPath).size(1920, 1080).outputQuality(1f).toFile(contextPath);
        //        }

        /*
         * size(width,height) 若图片横比1920小，高比1080小，不变
         * 若图片横比1920小，高比1080大，高缩小到1080，图片比例不变 若图片横比1920大，高比1080小，横缩小到1920，图片比例不变
         * 若图片横比1920大，高比1080大，图片按比例缩小，横为1920或高为1080
         * 图片格式转化为jpg,质量不变
         */
        BufferedImage image = ImageIO.read(new File(serverPath));
        if (image.getHeight() > 1080 || image.getWidth() > 1920) {
            if (!"png".equals(extName)) {
                Thumbnails.of(serverPath).size(1920, 1080).outputQuality(1f).outputFormat("jpg").toFile(toFilePath);
            } else {
                Thumbnails.of(serverPath).size(1920, 1080).outputQuality(1f).toFile(toFilePath);
            }
        } else {
            if (!"png".equals(extName)) {
                Thumbnails.of(serverPath).outputQuality(1f).scale(1f).outputFormat("jpg").toFile(toFilePath);
            } else {
                Thumbnails.of(serverPath).outputQuality(1f).scale(1f).toFile(toFilePath);
            }
        }

//
//        if ("png".equalsIgnoreCase(extName)) {
//            // 由于outputFormat会自动在路径后加上后缀名，所以移除以前的后缀名
//            String removeExtensionFilePath = FilenameUtils.removeExtension(toFilePath);
//            Thumbnails.of(serverPath).scale(1f)
//                    .outputQuality(0.5f)
//                    .outputFormat("jpg")
//                    .toFile(getServerPath(removeExtensionFilePath));
//            toFilePath = removeExtensionFilePath + ".jpg";
//        } else {
//            Thumbnails.of(serverPath).scale(1f).outputQuality(0.5f).toFile(getServerPath(toFilePath));
//        }

        // 删除被压缩的文件
        FileUtils.forceDelete(new File(serverPath));

        return toFilePath;
    }

    /**
     * 方法描述：生成文件文件名
     * 创建时间：2018-10-20 20:46:18
     *
     * @param childFile 子目录
     * @param extName   后缀名
     * @author "Luo.z.x"
     */
    private static String getDestPath(String childFile, String extName) {
        //规则：  子目录/年月日_随机数.后缀名
        String sb = childFile + "/"
                + DateUtil.parseDateToStr(DateUtil.YYYYMMDD, new Date())
                + "_" + IdUtil.getUUID()
                + "." + extName;
        return sb;
    }

    /**
     * 方法描述：生成文件在的实际的路径
     * 创建时间：2018-10-20 20:46:18
     *
     * @param destPath 文件的相对路径
     * @author "lixingwu"
     */
    private static String getServerPath(String destPath) {
        // 文件分隔符转化为当前系统的格式
        return FilenameUtils.separatorsToSystem(ATTACHMENT_GAIN_PATH + destPath);
    }

    /**
     * 方法描述：上传文件.
     * 创建时间：2018-10-19 13:09:19
     *
     * @param multipartFile 上传的文件对象，必传
     * @param childFile     上传的父目录，为空直接上传到指定目录 （会在指定的目录下新建该目录，例如：/user/1023）
     * @param extension     允许上传的文件后缀名，为空不限定上传的文件类型 （使用逗号隔开的字符串，精确匹配例如：txt,jpg,png,zip）
     * @param isImage       上传的是否是图片，如果是就会进行图片压缩；如果不希望图片被压缩，则传false，让其以文件的形式来保存
     * @return the file result
     * @throws IOException 异常信息应返回
     * @author "lixingwu"
     */
    private static FileResult saveFile(MultipartFile multipartFile, String childFile, String extension, Boolean isImage) throws IOException {
        if (null == multipartFile || multipartFile.isEmpty()) {
            throw new IOException("上传的文件对象不存在...");
        }
        // 文件名
        String fileName = multipartFile.getOriginalFilename();
        // 文件后缀名
        String extName = FilenameUtils.getExtension(fileName);
        if (StringUtils.isEmpty(extName)) {
            throw new RuntimeException("文件类型未定义不能上传...");
        }
        // 判断文件的后缀名是否符合规则
        isContains(extension, extName);

        // 创建目标文件的名称,规则请看destPath方法
        String destPath = getDestPath(childFile, extName);
        // 文件的实际路径
        String serverPath = getServerPath(destPath);

        // 创建文件
        File destFile = createFile(serverPath);
        // 保存文件
        multipartFile.transferTo(destFile);

        // 拼装返回的数据
        FileResult result = new FileResult();
        // 如果是图片，就进行图片处理
        if (BooleanUtils.isTrue(isImage)) {
            // 图片处理
            String toFilePath = thumbnails(serverPath, childFile, extName);

            // 得到处理后的图片文件对象
            File file = new File(getServerPath(toFilePath));
            // 压缩后的文件后缀名
            String extExtName = FilenameUtils.getExtension(toFilePath);
            // 源文件=源文件名.压缩后的后缀名
            String extFileName = FilenameUtils.getBaseName(fileName) + "." + FilenameUtils.getExtension(toFilePath);
            result.setFileSize(file.length());
            result.setServerPath(toFilePath);
            result.setFileName(extFileName);
            result.setExtName(extExtName);
        } else {
            result.setFileSize(multipartFile.getSize());
            result.setFileName(fileName);
            result.setExtName(extName);
            result.setServerPath(destPath);
        }
        return result;
    }

    /**
     * 方法描述：上传文件.
     * 创建时间：2018-10-19 13:09:19
     *
     * @param multipartFile 上传的文件对象，必传
     * @param childFile     上传的父目录，为空直接上传到指定目录 （会在指定的目录下新建该目录，例如：/user/1023）
     * @param extension     允许上传的文件后缀名，为空不限定上传的文件类型 （使用逗号隔开的字符串，精确匹配例如：txt,jpg,png,zip）
     * @return the file result
     * @throws IOException 异常信息应返回
     * @author "lixingwu"
     */
    public static FileResult saveFile(MultipartFile multipartFile, String childFile, String extension) throws IOException {
        return saveFile(multipartFile, childFile, extension, false);
    }

    /**
     * 方法描述：上传图片.
     * 创建时间：2018-10-19 13:09:19
     *
     * @param multipartFile 上传的文件对象，必传
     * @param childFile     上传的父目录，为空直接上传到指定目录 （会在指定的目录下新建该目录，例如：/user/1023）
     * @param extension     允许上传的文件后缀名，为空不限定上传的文件类型 （使用逗号隔开的字符串，精确匹配例如：txt,jpg,png,zip）
     * @return the file result
     * @throws IOException 异常信息应返回
     * @author "lixingwu"
     */
    public static FileResult saveImage(MultipartFile multipartFile, String childFile, String extension) throws IOException {
        return saveFile(multipartFile, childFile, extension, true);
    }

}
