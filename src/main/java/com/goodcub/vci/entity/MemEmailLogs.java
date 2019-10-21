package com.goodcub.vci.entity;

import com.goodcub.common.enums.StatusEnum;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class MemEmailLogs implements Serializable {
	protected Long melid;
	protected Long memid;
	protected String email;
	protected String code;
    /**邮件发送类型：注册,找回密码**/
	protected Integer stype;
	protected StatusEnum state;
	protected Date addtime;
}
