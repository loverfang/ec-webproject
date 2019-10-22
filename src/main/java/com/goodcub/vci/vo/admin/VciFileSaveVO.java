package com.goodcub.vci.vo.admin;

import com.goodcub.common.upload.FileResult;
import lombok.Data;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.Serializable;
import java.util.List;

/**
 * @Author Luo.z.x
 * @Description: TODO
 * @Date 2019/10/22
 * @Version V1.0
 **/
@Data
public class VciFileSaveVO implements Serializable {
    private List<FileResult> fileList;
}
