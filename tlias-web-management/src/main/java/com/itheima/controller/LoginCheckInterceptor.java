package com.itheima.controller;

import com.alibaba.fastjson.JSONObject;
import com.itheima.pojo.Result;
import com.itheima.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {
    //目标资源方法执行之前运行，返回值为true表示继续执行，返回值为false表示中断执行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //登入检验
        //获取请求url
        String url = request.getRequestURI().toString();
        log.info("请求的url是：{}", url);

        //判断url中是否包含login，如果包含login，则直接放行
        if (url.contains("login")) {
            log.info("放行");
            return true;
        }
        //获取jwt令牌
        String jwt = request.getHeader("token");
        //判断令牌是否存在，如果不存在，返回错误结果
        if (!StringUtils.hasLength(jwt)) {
            log.info("令牌不存在，返回未登入信息");
            Result error = Result.error("Not_Login");
            //手动将对象转换成json数据的格式（借助阿里巴巴fastJSON工具包）
            String notLogin = JSONObject.toJSONString(error);
            response.getWriter().write(notLogin);
            return false;
        }
        //解析token,如果失败返回错误结果
        try {
            JwtUtils.parseJWT(jwt);
        } catch (Exception e) {//jwt
            e.printStackTrace();
            log.info("令牌解析失败，返回未登入错误结果");
            Result error = Result.error("Not_Login");
            //手动将对象转换成json数据的格式（借助阿里巴巴fastJSON工具包）
            String notLogin = JSONObject.toJSONString(error);
            response.getWriter().write(notLogin);
            return false;

        }
        //放行
        log.info("令牌验证通过，放行");
        return true;
        //System.out.println("LoginCheckInterceptor的preHandle方法执行了");
//        return true;
    }

    @Override//目标资源方法执行之后运行
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("LoginCheckInterceptor的postHandle方法执行了");

    }

    @Override//视图渲染后运行
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("LoginCheckInterceptor的afterCompletion方法执行了");
    }
}