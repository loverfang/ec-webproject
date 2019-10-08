package com.goodcub.common.upload;

/**
 * @Author Luo.z.x
 * @Description: TODO
 * @Date 2019/9/30
 * @Version V1.0
 **/
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 读取properties文件
 *
 * @author lixingwu
 */
public class PropertiesUtils {

    private Properties properties;

    private static PropertiesUtils propertiesUtils = new PropertiesUtils();

    /**
     * 私有构造，禁止直接创建
     */
    private PropertiesUtils() {
        properties = new Properties();
        InputStream in = PropertiesUtils.class.getClassLoader()
                .getResourceAsStream("config.properties");
        try {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取单例
     *
     * @return PropertiesUtils
     */
    public static PropertiesUtils getInstance() {
        if (propertiesUtils == null) {
            propertiesUtils = new PropertiesUtils();
        }
        return propertiesUtils;
    }

    /**
     * 根据属性名读取值
     *
     * @param name 名称
     */
    public Object getProperty(String name) {
        return properties.getProperty(name);
    }


    /*************************************************************************/
    /*****************************读取属性，封装字段**************************/
    /*************************************************************************/

    /**
     * 是否调试模式
     */
    public Boolean isDebug() {
        return "true".equals(properties.getProperty("isDebug"));
    }

    public String getAttachmentServer() {
        return properties.getProperty("attachmentServer");
    }

    public String getStaticAccessPath() {
        return properties.getProperty("staticAccessPath");
    }

    public String getUploadFolder() {
        return properties.getProperty("uploadFolder");
    }

    public String getFileExtension() {
        return properties.getProperty("fileExtension");
    }

    public String getImageExtension() {
        return properties.getProperty("imageExtension");
    }

    public static void main(String[] args) {
        PropertiesUtils pro = PropertiesUtils.getInstance();
        String value = String.valueOf(pro.getProperty("fileExtension").toString());
        System.out.println(value);
    }
}