package com.yzy.service;

import com.yzy.pojo.ClazzCountOption;
import com.yzy.pojo.DegreeOption;
import com.yzy.pojo.JobOption;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface ReportService {
    public JobOption getEmpJobData();
    public List<Map<String, Object>> getEmpGenderData();

    /**
     * 获取学生学历统计数据
     * @return 学历统计数据，格式：[{"name": "初中", "value": 5}, ...]
     */
    List<Map<String, Object>> getStudentDegreeData();

    /**
     * 获取班级人数统计数据
     * @return 班级人数统计数据，包含班级名称列表和对应人数列表
     */
    ClazzCountOption getStudentCountData();
}
