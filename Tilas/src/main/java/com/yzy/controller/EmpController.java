package com.yzy.controller;

import com.yzy.pojo.*;
import com.yzy.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.event.ItemEvent;
import java.lang.reflect.Array;
import java.util.List;

@Slf4j
@RequestMapping("/emps")
@RestController
public class EmpController {
    @Autowired
    EmpService empService;

    /**
     * 分页查询员工信息
     * @param param 查询参数对象
     * @return 分页结果
     */
    @GetMapping
    public Result page(EmpQueryParam param) {
        log.info("分页查询员工,参数:{}", param);
        PageResult<Emp> pageResult = empService.page(param);
        return Result.success(pageResult);
    }

        @PostMapping
    public Result addEmp(@RequestBody Emp emp){
        log.info("添加员工,参数:{}", emp);
        empService.addEmp(emp);
        return Result.success();
    }

    @DeleteMapping
    public Result deleteEmp(@RequestParam("ids") List<Long> ids) {
        log.info("删除员工,参数:{}", ids);
        empService.deleteEmp(ids); // 需要在 service 中实现删除逻辑
        return Result.success();
    }


    @GetMapping("/{id}")
    public Result getInfo(@PathVariable("id") Integer id){
        log.info("查询员工信息,参数:{}", id);
        Emp emp = empService.getInfo(id);
        return Result.success(emp);
    }

    @PutMapping
    public Result update(@RequestBody Emp emp){
        log.info("更新员工信息,参数:{}", emp);
        empService.update(emp);
        return Result.success();
    }

    @GetMapping("/list")
    public Result findAll(){
        log.info("查询所有员工信息");
        List<Emp> empList = empService.findAll();
        return Result.success(empList);
    }
}

