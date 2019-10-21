package com.goodcub.vci.vo.site;

import com.goodcub.common.enums.StatusEnum;
import lombok.Data;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @Author Luo.z.x
 * @Description: TODO
 * @Date 2019/10/12
 * @Version V1.0
 **/
@Data
public class MemberFrontVO {
    private Long memid;
    private String username;
    private String password;
    private String usertype;
    private String cardnum;
    private String name;
    private String phone;
    private String email;
    private String company;
    private String jobtitle;
    private String vciguwen;
    private Integer viewcount;
    private Integer totaldays;
    private Integer avldays;
    private StatusEnum status;
    private String regtime;

    private String txt1;
    private String txt2;
    private String txt3;
    private String txt4;
    private String txt5;
    private String txt6;
    private String txt7;
    private String txt8;
    private String txt9;
    private String txt10;
    private String txt11;
    private String txt12;

    private String vcode;
}
