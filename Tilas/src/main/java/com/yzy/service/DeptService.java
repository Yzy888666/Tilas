package com.yzy.service;

import com.yzy.pojo.Dept;

import java.util.List;

public interface DeptService {
    List<Dept> findAll();

    void delete(Integer id);

    void insert(Dept dept);

    Dept getInfo(Integer deptId);

    void update(Dept dept);
}
