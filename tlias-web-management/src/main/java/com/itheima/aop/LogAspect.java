package com.itheima.aop;

import com.alibaba.fastjson.JSONObject;
import com.itheima.mapper.OperateLogMapper;
import com.itheima.pojo.OperateLog;
import com.itheima.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Component
@Aspect
public class LogAspect {
    @Autowired
    private HttpServletRequest request;   //当前请求的对象
    @Autowired
    private OperateLogMapper operateLogMapper;

    @Around("@annotation(com.itheima.anno.Log)")//环绕通知
    public Object recordLog(ProceedingJoinPoint joinPointoin) throws Throwable {
        //操作人的id - 当前员工登入的id  （从jwt令牌获取然后解析令牌）
        String jwt = request.getHeader("token");
        Claims claims = JwtUtils.parseJWT(jwt);
        Integer operateUser = (Integer) claims.get("id");//获取令牌存储的内容
        //操作时间
        LocalDateTime operateTime = LocalDateTime.now();

        //操作类名
        String className = joinPointoin.getTarget().getClass().getName();

        //操作方法名
        String methodName = joinPointoin.getSignature().getName();

        //操作方法参数
        Object[] args = joinPointoin.getArgs();
        Object methodParams = Arrays.toString(args);
        long begin = System.currentTimeMillis();
        //调用原始目标方法运行
        Object result = joinPointoin.proceed();
        long end = System.currentTimeMillis();
        //操作方法返回值
        Object returnValue = JSONObject.toJSON(result);

        //操作耗时
        long time = end - begin;
        System.out.println("耗时："+ time);

        //记录操作日志
        OperateLog operateLog = new OperateLog(null, operateUser, operateTime, className, methodName,methodParams , returnValue, time);
        operateLogMapper.insert(operateLog);//调用insert插入到数据库表中
        log.info("aop记录操作日志: {}",operateLog);
        return result;


    }

}
