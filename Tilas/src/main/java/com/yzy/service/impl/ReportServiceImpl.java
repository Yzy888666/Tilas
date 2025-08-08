package com.yzy.service.impl;

import com.yzy.mapper.EmpMapper;
import com.yzy.pojo.JobOption;
import com.yzy.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private EmpMapper empMapper;

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
}
