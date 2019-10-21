package com.goodcub.vci.mapper;

import com.goodcub.vci.entity.NewsPhoto;
import com.goodcub.vci.entity.VciFiles;
import com.goodcub.vci.vo.admin.VciFileListVO;
import com.goodcub.vci.vo.site.VciFileFrontVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author Luo.z.x
 * @Description: TODO
 * @Date 2019/10/21
 * @Version V1.0
 **/
public interface VciFilesMapper {

    List<VciFileListVO> queryVciFilesList(Map<String,Object> param);

    Integer saveVciFilesBatch(@Param("vciFilesList") List<VciFiles> vciFilesList);

    Integer deleteVciFilesById(@Param("idList") List<Long> idList);

    VciFileFrontVO queryVciFileByFid(Long fid);
}
