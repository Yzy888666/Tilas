package com.yzy.controller;

import com.yzy.pojo.Dept;
import com.yzy.pojo.Result;
import com.yzy.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/depts")
@RestController
public class DeptController {
    @Autowired
    private DeptService deptService;
    @GetMapping
    public Result list() {
        System.out.println("查询所有列表");
        List<Dept> depts =deptService.findAll();
        return Result.success(depts);
    }

    //前端传参方法：首先可以使用@RequestParam来获取参数，如果获取的参数和参数名一致，那么就可以省略@RequestParam
    @DeleteMapping
    public Result delete(Integer id) {
        System.out.println("删除部门:"+ id);
        deptService.delete(id);
        return Result.success();
    }

    //前端POST的传参方法：使用@RequestBody，来将前端传入的参数封装到Dept对象中，接收的参数名必须和Dept对象的属性名一致
    @PostMapping
    public Result add(@RequestBody Dept dept){
        System.out.println("添加部门:"+ dept);
        //在Service中实现对创建时间和更新时间的赋值
        deptService.insert(dept);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getInfo(@PathVariable("id") Integer deptId){ //接收路径传参

        System.out.println("查询部门信息:"+ deptId);
        Dept dept = deptService.getInfo(deptId);
        return Result.success(dept);
    }

    @PutMapping
    public Result update(@RequestBody Dept dept){
        System.out.println("更新部门信息:"+ dept);
        deptService.update(dept);
        return Result.success();
    }
}
