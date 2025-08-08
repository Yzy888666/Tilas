package com.yzy.mapper;

import com.yzy.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {
    @Select("select id,name,dept.create_time,dept.update_time from dept order by update_time desc")
    List<Dept> findAll();

    @Delete("delete from dept where id=#{id}")
    void delete(Integer id);

    @Insert("insert into dept(name,create_time,update_time) values(#{name},#{createTime},#{updateTime})")
    void insert(Dept dept);

    @Select("select id,name,dept.create_time,dept.update_time from dept where id=#{deptId}")
    Dept getInfo(Integer deptId);

    @Update("update dept set name=#{name},update_time=#{updateTime} where id=#{id}")
    void update(Dept dept);
}
