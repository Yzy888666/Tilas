package com.yzy.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yzy.mapper.EmpMapper;
import com.yzy.mapper.EmpExpeMapper;
import com.yzy.pojo.*;
import com.yzy.service.EmpService;
import com.yzy.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private EmpExpeMapper empExpeMapper;

    @Override
    @Deprecated
    public PageResult<Emp> page(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end) {
        PageHelper.startPage(page,pageSize);
        List<Emp> list = empMapper.list(name,gender,begin,end);
        Page<Emp> p = (Page<Emp>) list;
        return new PageResult<>(p.getTotal(),p.getResult());
    }
    
    @Override
    public PageResult<Emp> page(EmpQueryParam param) {
//        // 设置默认值
//        if (param.getPage() == null || param.getPage() < 1) {
//            param.setPage(1);
//        }
//        if (param.getPageSize() == null || param.getPageSize() < 1) {
//            param.setPageSize(10);
//        }
        
        PageHelper.startPage(param.getPage(), param.getPageSize());
        List<Emp> list = empMapper.list(param.getName(), param.getGender(), param.getBegin(), param.getEnd());
        Page<Emp> p = (Page<Emp>) list;
        return new PageResult<>(p.getTotal(), p.getResult());
    }

    @Transactional //事务管理注解，如果这个方法运行成功则提交，如果有错误，则回滚
    @Override
    public void addEmp(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.addEmp(emp);

        log.info("员工插入后的ID: {}", emp.getId());

        List<EmpExpr> empExpr = emp.getExprList();
        log.info("工作经历列表: {}", empExpr);

        if(empExpr != null && !empExpr.isEmpty()){
            log.info("开始处理工作经历，数量: {}", empExpr.size());
            // 为每个工作经历设置员工ID
            for(EmpExpr expr : empExpr) {
                expr.setEmpId(emp.getId());
                log.info("设置工作经历的员工ID: {}", expr);
            }
            empExpeMapper.addBatch(empExpr);
            log.info("工作经历批量插入完成");
        } else {
            log.info("工作经历列表为空，跳过插入");
        }
    }

    @Transactional
    @Override
    public void deleteEmp(List<Long> ids) {
        empMapper.deleteByIds(ids);
        empExpeMapper.deleteEmpExprByEmpIds(ids);
    }

    @Override
    public Emp getInfo(Integer id) {
        return empMapper.getInfo(id);

    }

    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);

        //将id转化为List<Long>
        List<Long> Array = new ArrayList<>();
        Array.add(Long.valueOf(emp.getId()));
        empExpeMapper.deleteEmpExprByEmpIds(Array);

        List<EmpExpr> empExpr = emp.getExprList();
        if (empExpr != null && !empExpr.isEmpty()){
            empExpr.forEach(expr -> expr.setEmpId(emp.getId()));
            empExpeMapper.addBatch(empExpr);
        }

    }

    @Override
    public List<Emp> findAll() {
        return empMapper.findAll();
    }

    @Override
    public LoginInfo login(Emp emp) {
        LoginInfo logininfo=empMapper.getEmpByUsernameAndPassword(emp);
        log.info("员工登录,参数:{}", emp);


        Map<String, Object> claims = new HashMap<>();
        claims.put("id", emp.getId());
        claims.put("username", emp.getUsername());

        if (logininfo != null){
            String jwt = JwtUtils.generateJwt(claims);
            logininfo.setToken(jwt);
            return logininfo;
        }
        return null;
    }
}
