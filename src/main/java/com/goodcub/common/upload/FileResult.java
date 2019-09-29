package com.goodcub.common.upload;

import lombok.Data;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @Author Luo.z.x
 * @Description: 文件上传返回的数据实体
 * @Date 2019/9/29
 * @Version V1.0
 **/
@Data
public class FileResult {
    // 文件名
    private String fileName;
    // 扩展名
    private String extName;
    // 文件大小，字节
    private Long fileSize;
    // 文件存储在服务器的相对地址
    private String serverPath;
}
