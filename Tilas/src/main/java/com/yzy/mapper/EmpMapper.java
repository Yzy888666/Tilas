package com.yzy.mapper;

import com.yzy.pojo.Emp;
import com.yzy.pojo.LoginInfo;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;


@Mapper
public interface EmpMapper {

    List<Emp> list(String name, Integer gender, LocalDate begin, LocalDate end);

    @Options(useGeneratedKeys = true, keyProperty = "id")//获取生成的主键 -- mybatis中的主键返回
    @Insert({
        "INSERT INTO emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time)"+
        "VALUES(#{username}, #{name}, #{gender}, #{phone}, #{job}, #{salary}, #{image}, #{entryDate}, #{deptId}, #{createTime}, #{updateTime})"
    })


    void addEmp(Emp emp);



    void deleteByIds(List<Long> ids);


    //手动封装查询结果，因为查询结果有多条数据，不能用@ResultMap
    Emp getInfo(Integer id);


    void update(Emp emp);

    @MapKey("job")
    List<Map<String, Object>> getEmpJobData();

    @MapKey("gender")
    List<Map<String, Object>> getEmpGenderData();

    @Select("select * from emp")
    List<Emp> findAll();

    @Select("select id,name,password from emp where username=#{username} and password=#{password}")
    LoginInfo getEmpByUsernameAndPassword(Emp emp);
}
