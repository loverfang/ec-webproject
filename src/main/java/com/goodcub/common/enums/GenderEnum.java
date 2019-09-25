package com.goodcub.common.enums;

/**
 * @ClassName GenderEnum
 * @Description TODO
 * @Author Luo.z.x
 * @Date 2019/9/258:07
 * @Version 1.0
 */
public enum GenderEnum {

    UNKNOW("未知"), MALE("男"), FEMALE("女");

    private final String value;
    GenderEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static GenderEnum parse(String value) {
        for (GenderEnum sex : GenderEnum.values()) {
            if (sex.getValue().equals(value)) {
                return sex;
            }
        }
        throw new IllegalArgumentException("不存在[" + value + "]对应的枚举值");
    }

}
