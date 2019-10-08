package com.goodcub.common.upload;

import com.goodcub.common.utils.DateUtil;
import com.goodcub.common.utils.IdUtil;
import com.goodcub.common.utils.RequestUtil;
import com.goodcub.vci.exception.UploadException;
import com.goodcub.vci.exception.UploadExceptionCodeEnum;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @Author Luo.z.x
 * @Description: TODO
 * @Date 2019/9/30
 * @Version V1.0
 **/
public class UploadHelper {

    private static final Logger logger = LoggerFactory.getLogger(UploadHelper.class);

    /**
     * Spring Boot项目中获得项目跟路径
     * //=> file:/root/tmp/demo-springboot-0.0.1-SNAPSHOT.jar!/BOOT-INF/classes!/
     *
     * @return
     * @throws FileNotFoundException
     */
    public static String getRootPath() throws FileNotFoundException {
        String path = ResourceUtils.getURL("classpath:").getPath();
        logger.info("ResourceUtils.getURL(\"classpath:\").getPath() -> "+path);

        // 创建File时会自动处理前缀和jar包路径问题  => /root/tmp
        File rootFile = new File(path);
        if(!rootFile.exists()) {
            rootFile = new File("");

        }

        logger.info("重新创建的根目录: {}", rootFile.getAbsolutePath());       //获取的字符串末尾没有分隔符 /
        return rootFile.getAbsolutePath();
    }

    /**
     * 方法描述：生成目标文件文件名
     *
     * @param extName   后缀名
     */
    public static String getDestFileName(String extName) {
        //规则：  年月日_随机数.后缀名
        String sb = DateUtil.parseDateToStr(DateUtil.YYYYMMDD, new Date())
                + "_" + IdUtil.getUUID()
                + "." + extName;
        return sb;
    }

    /**
     * 方法描述：判断extension中是否存在extName
     *
     * @param extension 使用逗号隔开的字符串，精确匹配例如：txt,jpg,png,zip
     * @param extName   文件的后缀名
     */
    public static void isContains(String extension, String extName) {
        if (StringUtils.isNotEmpty(extension)) {
            // 切割文件扩展名
            String[] exts = StringUtils.split(extension, ",");
            if (ArrayUtils.isNotEmpty(exts)) {
                assert exts != null;
                List<String> extList = Arrays.asList(exts);
                //判断
                if (!extList.contains(extName)) {
                    throw new UploadException(UploadExceptionCodeEnum.MULTI_TYPE_ERROR);
                }
            } else {
                // 判断文件的后缀名是否为extension
                if (!extension.equalsIgnoreCase(extName)) {
                    throw new UploadException(UploadExceptionCodeEnum.MULTI_TYPE_ERROR);
                }
            }
        }
    }

    /**
     * 方法描述：根据文件的绝对路径创建一个文件对象.
     *
     * @param filePath 文件的绝对路径
     * @return 返回创建的这个文件对象
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
     * 获取服务部署根路径 http:// + ip + port
     *
     * @return
     */
    public static String getServerIPPort() {
        HttpServletRequest request = RequestUtil.getRequest();
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
    }

    /**
     * 根据文件的大小(单位为btye)，得到字符串的表示形式
     * @param upfileSize
     */
    public String setFileSize(Long upfileSize) {
        DecimalFormat format = new DecimalFormat("0.00");
        if(upfileSize>=1024*1024){
            float fs1=(((float)upfileSize)/1024f/1024f);
            return format.format(fs1)+"M";
        }else if(upfileSize>11 && upfileSize<=1024*1024){
            float  fs2=((float)upfileSize)/1024;
            return format.format(fs2)+"KB";
        }else if(upfileSize>0&&upfileSize<11){
            return String.valueOf(upfileSize)+"Byte";
        }else{
            return 0+"Byte";
        }
    }
}
