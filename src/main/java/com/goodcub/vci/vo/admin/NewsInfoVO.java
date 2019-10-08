package com.goodcub.vci.vo.admin;

import com.goodcub.vci.entity.News;
import lombok.Data;

/**
 * @ClassName NewsInfoVO
 * @Description TODO
 * @Author Luo.z.x
 * @Date 2019/10/211:14
 * @Version 1.0
 */
@Data
public class NewsInfoVO extends News {
    // 扩展信息
    private String nlable;//新闻类别或新闻标签
    private String ndigest;//新闻摘要
    private String endate;//英文格式的日期
    private String province;//
    private String city;//
    private String video;//
    private String txt1;
    private String txt2;
    private String txt3;
    private String txt4;
    private String txt5;
    private String txt6;

    //其它信息...
}
