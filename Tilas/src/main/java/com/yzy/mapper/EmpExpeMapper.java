package com.yzy.mapper;

import com.yzy.pojo.Emp;
import com.yzy.pojo.EmpExpr;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;


@Mapper
public interface EmpExpeMapper {

    /**
     * 批量插入员工工作经历
     * @param empExprList 员工工作经历列表
     */
    void addBatch(List<EmpExpr> empExprList);

    void deleteEmpExprByEmpIds(List< Long> ids);

    void updateEmpExpr(Emp emp);



}
