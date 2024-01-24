package com.itheima.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private Integer code;//编码：1表示成功，0表示失败
    private String msg;//提示信息,描述字符串
    private Object data;//返回的数据

    public static Result success() {
        return new Result(1, "处理成功", null);

    }
    //查询成功响应
    public static Result success(Object data) {
        return new Result(1, "处理成功", data);
    }
    //失败响应
    public static Result fail(String msg) {
        return new Result(0, msg, null);
    }


    public static Result error(String msg) {
        return new Result(0, msg, null);
    }
}
