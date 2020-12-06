package com.sylinx.springcloud.dao;

import com.sylinx.springcloud.pojo.Dept;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DeptDao {

    public boolean addDept(Dept dept);
//    @Select("SELECT * FROM dept WHERE deptno = #{deptno}")
    public Dept queryById(Long deptno);

    public List<Dept> queryAll();

}
