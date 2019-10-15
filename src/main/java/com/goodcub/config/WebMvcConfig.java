package com.goodcub.config;

import com.goodcub.auth.interceptor.AuthenticationInterceptor;
import com.goodcub.common.upload.PropertiesUtils;
import com.goodcub.interceptor.BasePathInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * @ClassName MyWebMvcConfig
 * @Description TODO
 * @Author Luo.z.x
 * @Date 2019/9/1923:29
 * @Version 1.0
 */
@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setCache(false);  //开发完成后将其设置为true
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".html");
        resolver.setContentType("text/html; charset=UTF-8");
        registry.viewResolver(resolver);
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 设置文件上传的文件不拦截
        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:/home/quting7qnuntrienmg/wwwroot/upload/");

        // addResourceHandler表示页面中使用的路径，addResourceLocations实际存在的文件夹路径/注意区分window与linux
        //指定了静态资源文件的位置
        registry.addResourceHandler("/res/**")
                .addResourceLocations("classpath:/static/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(basePathInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(authenticationInterceptor()).addPathPatterns("/**");    // 拦截所有请求,通过判断是否有 @LoginRequired 注解 决定是否需要登录
    }

    @Bean
    public BasePathInterceptor basePathInterceptor() {
        return new BasePathInterceptor();
    }

    @Bean
    public AuthenticationInterceptor authenticationInterceptor() {
        return new AuthenticationInterceptor();
    }

}
