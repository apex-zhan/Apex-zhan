package com.itheima.controller;


import com.itheima.anno.Log;
import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class DeptController {
//    日志记录对象
//    private static Logger log = LoggerFactory.getLogger(DeptController.class);等于@Slf4j
//    @RequestMapping(value = "/depts" ,method = RequestMethod.GET)//请求路经
//    上下相等
    @Autowired
    private DeptService deptService;
    @GetMapping("/depts")//简写
    //定义一个方法查询部门信息
    public Result list() {
        //调用log方法
        log.info("查询部门信息");
        //调用service方法查询部数据
       List<Dept> deptList = deptService.list();
        return Result.success(deptList);



    }
    @Log
    @DeleteMapping("/depts/{id}")
    public Result delete(@PathVariable Integer id) throws Exception {

        log.info("删除部门信息",id);
        //调用service方法查询部数据
        deptService.delete(id);
        return Result.success();
    }
    //新增部门
    @Log
    @PostMapping("/depts")
    public Result add(@RequestBody Dept dept) {

        log.info("新增部门信息",dept);
        //调用service方法新增部们数据
        deptService.add(dept);
        return Result.success();
    }

    @GetMapping("health")
    public String getHealth() {
        return "ok";
    }

}