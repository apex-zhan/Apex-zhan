package com.itheima.service;

import com.itheima.pojo.Dept;

import java.util.List;

/**
 * 部门管理
 */
public interface DeptService {

    List<Dept> list();
//    查询全部部门数据


    //删除部门
    void delete(Integer id) throws Exception;


//    新增部门
    void add(Dept dept);
}
