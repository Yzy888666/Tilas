package com.yzy.service;

import com.yzy.pojo.Emp;
import com.yzy.pojo.EmpQueryParam;
import com.yzy.pojo.LoginInfo;
import com.yzy.pojo.PageResult;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


public interface EmpService {

    /**
     * 分页查询员工（使用多个参数）
     * @deprecated 建议使用 page(EmpQueryParam param) 方法
     */
    @Deprecated
    PageResult<Emp> page(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end);
    
    /**
     * 分页查询员工（使用参数对象）
     */
    PageResult<Emp> page(EmpQueryParam param);

    void addEmp(Emp emp);

    void deleteEmp(List<Long> ids);

    Emp getInfo(Integer id);

    void update(Emp emp);

    List<Emp> findAll();

    LoginInfo login(Emp emp);
}
