//package com.itheima.Aop;
//
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.springframework.stereotype.Component;
//
//@Slf4j
//@Aspect   //aop类
//@Component
//public class TimeAspect {
//    @Around("execution(* com.itheima.service.*.*(..))")  //切入点表达式
//    public Object recordTime(ProceedingJoinPoint joinPoint) throws Throwable {
//        System.out.println("记录开始时间");
//        long begin = System.currentTimeMillis();
//
//        //调用原始方法运行
//        Object result = joinPoint.proceed();
//
//        long end = System.currentTimeMillis();
//
//        log.info(joinPoint.getSignature()+"方法执行时间:{}ms", end - begin);
//        System.out.println("记录结束时间");
//        return result;
//
//    }
//
//
//}
