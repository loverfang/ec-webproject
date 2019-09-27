package com.goodcub.auth.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.goodcub.auth.exception.TokenException;
import com.goodcub.auth.exception.TokenExceptionCodeEnum;
import com.goodcub.common.annotation.PassToken;
import com.goodcub.common.annotation.UserLoginToken;
import com.goodcub.common.jwt.JwtHelper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;


/**
 * @Author Luo.z.x
 * @Description:  获取token并验证token
 * @Date 2019/7/31
 * @Version V1.0
 **/
public class AuthenticationInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationInterceptor.class);

    //@Autowired
    //private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {

        logger.info("......token拦截器开始拦截......");
        // 从 http 请求头中取出 token
        String token = httpServletRequest.getHeader("User-Token");

        // 如果不是映射到方法直接通过
        if(!(object instanceof HandlerMethod)){
            return true;
        }

        HandlerMethod handlerMethod=(HandlerMethod)object;
        Method method=handlerMethod.getMethod();

        // 检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }

        // 检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(UserLoginToken.class)) {

            // 首先从请求头中获取jwt串,与页面约定好存放jwt值的请求头属性名为EC-Token
            String jwt = httpServletRequest.getHeader("EC-Token");
            logger.info("[登录校验拦截器]-从header中获取的jwt为:{}", jwt);

            // 判断jwt是否有效
            if(StringUtils.isNotBlank(jwt)){

                // 校验jwt是否有效,有效则返回json信息,无效则返回空
                String retJson = JwtHelper.validateLogin(jwt);
                logger.info("[登录校验拦截器]-校验JWT有效性返回结果:{}", retJson);

                // retJSON为空则说明jwt超时或非法
                if(StringUtils.isNotBlank(retJson)){
                    JSONObject jsonObject = JSONObject.parseObject(retJson);
                    //校验客户端信息
                    String userAgent = httpServletRequest.getHeader("User-Agent");
                    if (userAgent.equals(jsonObject.getString("userAgent"))) {
                        // 获取刷新后的jwt值,设置到响应头中
                        httpServletResponse.setHeader("User-Token", jsonObject.getString("freshToken"));

                        // 将客户编号设置到session中
                        // httpServletRequest.getSession().setAttribute(GlobalConstant.SESSION_CUSTOMER_NO_KEY, jsonObject.getString("userId"));
                        return true;
                    }else{
                        logger.warn("[登录校验拦截器]-客户端浏览器信息与JWT中存的浏览器信息不一致，重新登录。当前浏览器信息:{}", userAgent);
                        throw new TokenException(TokenExceptionCodeEnum.TOKEN_NOT_lOGGED);
                    }
                }else {
                    logger.warn("[登录校验拦截器]-JWT非法或已超时,重新登录!");
                    throw new TokenException(TokenExceptionCodeEnum.TOKEN_NOT_EXPIRED);
                }

            }else{
                if (token == null) {
                    throw new TokenException(TokenExceptionCodeEnum.TOKEN_IS_NULL);
                }
            }
        }

        logger.info("......token拦截器拦截完了......");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
