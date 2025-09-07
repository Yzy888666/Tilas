package com.yzy.service.impl;

import com.yzy.mapper.EmpMapper;
import com.yzy.mapper.StudentMapper;
import com.yzy.pojo.ClazzCountOption;
import com.yzy.pojo.DegreeOption;
import com.yzy.pojo.JobOption;
import com.yzy.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public JobOption getEmpJobData() {
        // 调用 mapper 获取员工职位统计数据
        List<Map<String, Object>> mapList = empMapper.getEmpJobData();

        // 创建两个列表分别存储职位名称和对应的人数
        List<String> jobList = new ArrayList<>();
        List<Integer> dataList = new ArrayList<>();

        // 遍历查询结果，分别提取职位名称和人数
        for (Map<String, Object> map : mapList) {
            String pos = (String) map.get("pos");      // 职位名称
            Long num = (Long) map.get("num");          // 人数（MySQL COUNT返回Long类型）

            jobList.add(pos);
            dataList.add(num.intValue());  // 转换为Integer
        }

        // 封装到 JobOption 对象中并返回
        return new JobOption(jobList, dataList);
    }

    @Override
    public List<Map<String, Object>> getEmpGenderData() {
        // 调用 mapper 获取员工性别统计数据
        return empMapper.getEmpGenderData();
    }

    @Override
    public List<Map<String, Object>> getStudentDegreeData() {
        // 调用 mapper 获取学生学历统计数据
        List<Map<String, Object>> mapList = studentMapper.getStudentDegreeData();

        // 定义学历编码与名称的映射关系
        Map<Integer, String> degreeMap = new HashMap<>();
        degreeMap.put(1, "初中");
        degreeMap.put(2, "高中");
        degreeMap.put(3, "大专");
        degreeMap.put(4, "本科");
        degreeMap.put(5, "硕士");
        degreeMap.put(6, "博士");

        // 创建结果列表，格式：[{"name": "初中", "value": 5}, ...]
        List<Map<String, Object>> resultList = new ArrayList<>();

        // 遍历查询结果，转换为接口文档要求的格式
        for (Map<String, Object> map : mapList) {
            Integer degree = (Integer) map.get("degree");           // 学历编码
            Long degreeCount = (Long) map.get("degreeCount");       // 人数（MySQL COUNT返回Long类型）

            // 根据学历编码获取学历名称
            String degreeName = degreeMap.get(degree);
            if (degreeName != null) {  // 防止数据库中有未定义的学历编码
                Map<String, Object> resultMap = new HashMap<>();
                resultMap.put("name", degreeName);                  // 学历名称
                resultMap.put("value", degreeCount.intValue());     // 人数
                resultList.add(resultMap);
            }
        }

        return resultList;
    }

    @Override
    public ClazzCountOption getStudentCountData() {
        // 调用 mapper 获取班级人数统计数据
        List<Map<String, Object>> mapList = studentMapper.getStudentCountData();

        // 创建两个列表分别存储班级名称和对应的学生人数
        List<String> clazzList = new ArrayList<>();
        List<Integer> dataList = new ArrayList<>();

        // 遍历查询结果，分别提取班级名称和学生人数
        for (Map<String, Object> map : mapList) {
            String clazzName = (String) map.get("clazzName");       // 班级名称
            Long studentCount = (Long) map.get("studentCount");     // 学生人数（MySQL COUNT返回Long类型）

            clazzList.add(clazzName);
            dataList.add(studentCount.intValue());  // 转换为Integer
        }

        // 封装到 ClazzCountOption 对象中并返回
        return new ClazzCountOption(clazzList, dataList);
    }
}
