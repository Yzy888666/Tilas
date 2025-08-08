package com.yzy.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * 员工查询参数封装类
 */
@Data
public class EmpQueryParam {
    
    /**
     * 当前页码
     */
    private Integer page = 1;
    
    /**
     * 每页显示数量
     */
    private Integer pageSize = 10;
    
    /**
     * 员工姓名（模糊查询）
     */
    private String name;
    
    /**
     * 性别（1:男, 2:女）
     */
    private Integer gender;
    
    /**
     * 入职开始日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate begin;
    
    /**
     * 入职结束日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate end;
} 