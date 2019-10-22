package com.goodcub.vci.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.goodcub.common.enums.StatusEnum;
import lombok.Data;

/**
 * @ClassName Members
 * @Description TODO
 * @Author Luo.z.x
 * @Date 2019/9/257:51
 * @Version 1.0
 */
@Data
public class Members {
    @JsonSerialize(using = ToStringSerializer.class)
    protected Long memid;
    protected String username;
    protected String password;
    protected String usertype;
    protected String cardnum;
    protected String name;
    protected String phone;
    protected String email;
    protected String company;
    protected String jobtitle;
    protected String vciguwen;
    protected Integer viewcount;
    protected Integer totaldays;
    protected Integer avldays;
    protected StatusEnum status;
    protected String regtime;

    protected String txt1;
    protected String txt2;
    protected String txt3;
    protected String txt4;
    protected String txt5;
    protected String txt6;
    protected String txt7;
    protected String txt8;
    protected String txt9;
    protected String txt10;
    protected String txt11;
    protected String txt12;
}
