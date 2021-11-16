package com.cdtangmin;

import com.cdtangmin.entity.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestApp {
    @Test
    public static void main(String[] args) throws IOException {
    //访问mybatis读取t_student数据
    //1.定义mybatis主配置文件的名称，从类路径的根开始，即target/classes下面的路径
    String config = "mybatis.xml";

    //2.读取主配置文件config
    InputStream in = Resources.getResourceAsStream(config);

    //3.创建SqlSessionFactoryBuilder对象
    SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();

    //4.创建SqlSessionFactory对象
    SqlSessionFactory factory = builder.build(in);

    //5.[重要]获取SqlSession对象，从SqlSessionFactory中获取SqlSession
    SqlSession sqlSession = factory.openSession();

    //6.[重要]指定要执行的sql语句的标识，即sql映射文件中的namespace+"."+标签的id值
    String sqlId = "com.cdtangmin.dao.StudentDao" + "." + "insertStudent";

    //7.执行sql语句，通过sqlId找到语句
        Student student = new Student();
        student.setId(5);
        student.setName("云韵");
        student.setEmail("yunyun@126.com");
        student.setAge(28);
        int nums = sqlSession.insert(sqlId,student);
        sqlSession.commit();

    //8.输出结果
        System.out.println("成功更新" + nums + "条数据");

    //9.关闭SqlSession对象
    sqlSession.close();
    }
}
