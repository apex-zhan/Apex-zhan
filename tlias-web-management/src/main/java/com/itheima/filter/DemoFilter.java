package com.itheima.filter;

import org.springframework.boot.web.servlet.ServletComponentScan;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
@ServletComponentScan//使用javaweb三大组件
//@WebFilter(urlPatterns = "/*")//拦截所以请求
public class DemoFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        System.out.println("过滤器初始化执行了");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("拦截到了请求。。。放行前逻辑");
        //放行
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("拦截到了请求。。。放行后逻辑");
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
        System.out.println("过滤器销毁执行了");
    }
}
