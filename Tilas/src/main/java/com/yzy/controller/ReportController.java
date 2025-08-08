package com.yzy.controller;

import com.yzy.pojo.Result;
import com.yzy.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/report")
public class ReportController {
    @Autowired
    ReportService reportService;
    @GetMapping("/empJobData")
    public Result getEmpJobData(){
        log.info("查询员工职位数据");
        return Result.success(reportService.getEmpJobData());

    }

    @GetMapping("/empGenderData")
    public Result getEmpGenderData(){
        log.info("查询员工性别数据");
        return Result.success(reportService.getEmpGenderData());
    }
}
