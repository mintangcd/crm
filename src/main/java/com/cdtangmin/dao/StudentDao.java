package com.cdtangmin.dao;

import com.cdtangmin.entity.Student;
import org.apache.ibatis.annotations.Param;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface StudentDao {
    //查询student表的所有数据
    public List<Student> selectStudents();

    //方法中有多个参数使用@Param自定义参数名
    public List<Student> selectMultiParam(@Param("myName") String name,@Param("myAge") Integer age);

    //插入数据
    public int insertStudent(Student student);

    //法中有多个参数使用Map传递
    public List<Student> selectMultiByMap(Map<String,Object> map);
}
