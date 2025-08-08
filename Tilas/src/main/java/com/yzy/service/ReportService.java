package com.yzy.service;

import com.yzy.pojo.JobOption;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface ReportService {
    public JobOption getEmpJobData();
    public List<Map<String, Object>> getEmpGenderData();
}
