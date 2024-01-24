package com.itheima.filter;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.util.StringUtil;
import com.itheima.pojo.Result;
import com.itheima.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        //获取请求url
        String url = request.getRequestURI().toString();
        log.info("请求的url是：{}", url);

        //判断url中是否包含login，如果包含login，则直接放行
        if (url.contains("login")) {
            log.info("放行");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        //获取jwt令牌
        String jwt = request.getHeader("token");
        //判断令牌是否存在，如果不存在，返回错误结果
        if (!StringUtils.hasLength(jwt)) {
            log.info("令牌不存在，返回未登入信息");
            Result error = Result.error("Not_Login");
            //手动将对象转换成json数据的格式（借助阿里巴巴fastJSON工具包）
            String notLogin = JSONObject.toJSONString(error);
            resp.getWriter().write(notLogin);
            return;
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
            servletResponse.getWriter().write(notLogin);
            return;

        }
        //放行
        log.info("令牌验证通过，放行");
        filterChain.doFilter(servletRequest, servletResponse);

    }
}
