package com.goodcub.common.enums;

/**
 * @ClassName NewsTypeEnum
 * @Description TODO
 * @Author Luo.z.x
 * @Date 2019/9/277:39
 * @Version 1.0
 */
public enum NewsTypeEnum {
    /**单页发布系统**/
    SIGNSYS("单页发布系统"),

        ABOUTUS("About Us"),

        OURSERVICES("Our Services"),

        OURCILENTS("Our Clients"),

        MARKETINGWITHUS("Marketing With Us"),

        CONTACTUS("Contact Us"),

    /**文章发布系统**/
    NEWSSYS("文章发布系统"),

        PARTNERS("Partners"),

        INSIGHTS("VCI Insights"),

        EVENTS("Events"),

        STORIES("Success Stories"),


    VENDORS("供应商管理"),

        CATEGORY("Category"),

        VENDOR("供应商");


    private final String value;
    NewsTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static NewsTypeEnum parse(String value) {
        for (NewsTypeEnum ntyp : NewsTypeEnum.values()) {
            if (ntyp.getValue().equals(value)) {
                return ntyp;
            }
        }
        throw new IllegalArgumentException("不存在[" + value + "]对应的枚举值");
    }

}
