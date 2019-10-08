package com.goodcub.vci.vo.admin;

import com.goodcub.common.upload.FileResult;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName NewsPhotoInfoVO
 * @Description 多图片上传到某类新闻下的参数传递方式
 * @Author Luo.z.x
 * @Date 2019/10/81:20
 * @Version 1.0
 */
@Data
public class NewsPhotoSaveVO implements Serializable {
    private Long nid;
    private List<FileResult> photoList;
}
