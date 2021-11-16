package com.cdtangmin.entity;

import com.cdtangmin.util.SqlSessionUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyApp2 {
    public static void main(String[] args) throws IOException {
        //使用封装的工具类获取SqlSession对象
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();

        //[重要]指定要执行的sql语句的标识，即sql映射文件中的namespace+"."+标签的id值
        String sqlId = "com.cdtangmin.dao.StudentDao" + "." + "selectStudents";

        //执行sql语句，通过sqlId找到语句
        List<Student> studentList = sqlSession.selectList(sqlId);

        //输出结果
        studentList.forEach(student -> System.out.println(student));//Lamda表达式

        //关闭SqlSession对象
        //sqlSession.close();
        SqlSessionUtil.closeSession(sqlSession);
    }
}
