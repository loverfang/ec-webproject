package com.goodcub.vci.service.admin;

import com.goodcub.common.page.TableDataInfo;
import com.goodcub.vci.entity.VciFiles;

import java.util.List;
import java.util.Map;

/**
 * @ClassName VciFilesService
 * @Description TODO
 * @Author Luo.z.x
 * @Date 2019/10/616:27
 * @Version 1.0
 */
public interface VciFilesService {

    /**
     * 通用VciFiles文件列表
     * @param params
     * @param pageNum
     * @param pageSize
     * @return
     */
    TableDataInfo queryVciFilesList(Map<String, Object> params, int pageNum, int pageSize);

    /**
     * 批量保存VciFiles信息
     * @param vciFilesList
     * @return
     */
    Integer saveBatchVciFiles(List<VciFiles> vciFilesList);

    /**
     * 根据VciFiles的idList直接删除选中的VciFiles文件信息
     * @param idList
     * @return
     */
    Integer deleteVciFilesByFid(List<Long> idList);

}
