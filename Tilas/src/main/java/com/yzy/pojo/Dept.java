package com.yzy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dept {
    private Integer id;
    private String name;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

//    /* ========= 无参构造 ========= */
//    public Dept() {
//    }
//
//    /* ========= 全参构造 ========= */
//    public Dept(Integer id, String name, LocalDateTime createTime, LocalDateTime updateTime) {
//        this.id = id;
//        this.name = name;
//        this.createTime = createTime;
//        this.updateTime = updateTime;
//    }
//
//    /* ========= Getter & Setter ========= */
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public LocalDateTime getCreateTime() {
//        return createTime;
//    }
//
//    public void setCreateTime(LocalDateTime createTime) {
//        this.createTime = createTime;
//    }
//
//    public LocalDateTime getUpdateTime() {
//        return updateTime;
//    }
//
//    public void setUpdateTime(LocalDateTime updateTime) {
//        this.updateTime = updateTime;
//    }
//
//    /* ========= toString ========= */
//    @Override
//    public String toString() {
//        return "Dept{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", createTime=" + createTime +
//                ", updateTime=" + updateTime +
//                '}';
//    }
//
//    /* ========= equals & hashCode ========= */
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Dept dept = (Dept) o;
//        return Objects.equals(id, dept.id) &&
//                Objects.equals(name, dept.name) &&
//                Objects.equals(createTime, dept.createTime) &&
//                Objects.equals(updateTime, dept.updateTime);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, name, createTime, updateTime);
//    }
}