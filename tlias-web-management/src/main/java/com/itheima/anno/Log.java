package com.itheima.anno;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)//什么时候生效
@Target(ElementType.METHOD)//目标在方法上
public @interface Log {
}
