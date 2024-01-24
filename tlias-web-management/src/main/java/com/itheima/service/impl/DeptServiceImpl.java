package com.itheima.service.impl;

import com.itheima.mapper.DeptMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Dept;
import com.itheima.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    // 自动注入
    // 自动注入的类型是接口，那么会自动查找接口的实现类
    private DeptMapper deptMapper;

    @Autowired
    private EmpMapper empMapper;



    @Override
    public List<Dept> list() {
        List<Dept> deptList = deptMapper.list();
        return deptMapper.list();
    }
    @Transactional(rollbackFor = Exception.class)
    //事务管理aop
    @Override
    public void delete(Integer id) throws Exception {
        deptMapper.delete(id);//根据id删除部门

//        int i = 1/0;
        if (true)
            throw new Exception("自定义异常");


        empMapper.deleteById(id);//根据部门id删除该部门下的员工数据

    }

    @Override
    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.inster(dept);

    }
}
