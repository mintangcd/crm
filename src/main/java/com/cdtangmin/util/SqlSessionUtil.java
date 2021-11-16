package com.cdtangmin.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class SqlSessionUtil {
    //封装获取SqlSession对象的工具类
    private static SqlSessionFactory factory = null;

    private SqlSessionUtil(){}

    static {
        try {
            //读取主配置文件
            InputStream in = Resources.getResourceAsStream("mybatis.xml");
            //创建SqlSessionFactoryBuilder对象

            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();

            //创建SqlSessionFactory对象
            factory = builder.build(in);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //创建线程容器将SqlSession对象纳入，避免出现线程安全问题
    private static ThreadLocal<SqlSession> threadLocal = new ThreadLocal<>();

    public static SqlSession getSqlSession() {
        SqlSession sqlSession = threadLocal.get();
        if (factory != null && sqlSession == null) {
            //[重要]获取SqlSession对象，从SqlSessionFactory中获取SqlSession
            sqlSession = factory.openSession();//无参表示获取的是非自动提交事务的SqlSession
            threadLocal.set(sqlSession);
        }
        return sqlSession;
    }

    public static void closeSession(SqlSession sqlSession) {
        if (sqlSession != null) {
            sqlSession.close();
            threadLocal.remove();//清空线程容器中的sqlSession
        }
    }
}
