package com.itheima.controller;

import com.itheima.anno.Log;
import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * 员工管理Controller
 */
@Slf4j
@RestController
public class EmpController {
    @Autowired
    private EmpService empService;

    @GetMapping("/emps")
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,//@RequestParam设置默认值的
                       String name, Short gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        log.info(" 分页查询的参数", page, pageSize, name, gender, begin, end);
        //调用serice的方法分页查询
        PageBean pageBean = empService.page(page, pageSize, name, gender, begin, end);
        return Result.success(pageBean);
    }


    @Log
    @DeleteMapping("/emps/{ids}")
    public Result delete(@PathVariable List<Integer> ids) {
        log.info("删除员工，id数组：{}", ids);
        empService.delete(ids);
        return Result.success();
    }

    //    新增用户
    @Log
    @PostMapping("emps")
    public Result save(@RequestBody Emp emp) {
        log.info("新增员工信息：{}", emp);
        empService.save(emp);
        return Result.success();
    }

    //    查询回显
    @GetMapping("/emps/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("根据id查询员工信息，id：{}", id);
        Emp emp = empService.getById(id);
        return Result.success(emp);
    }

    //更新数据(修改 )
    @Log
    @PutMapping("/emps")
    public Result update(@RequestBody Emp emp) {//json格式数据接受
        log.info("更新员工信息：{}", emp);
        empService.update(emp);
        return Result.success();
    }
}
