package com.web.util;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtil {
	private static ThreadLocal<SqlSession> threadLocal = new ThreadLocal<SqlSession>();
	 static SqlSessionFactory sqlSessionFactory = null;//sqlSession工厂  生成SqlSession对象
	 static SqlSession sqlSession = null;//执行CRUD操作
	
    static{
    	//1.读取配置文件
    	String resource = "SqlMapConfig.xml";
		try {
			InputStream inputStream = Resources.getResourceAsStream(resource);
			
			sqlSessionFactory = new SqlSessionFactoryBuilder()
					.build(inputStream);

		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    /**
     * 获取SqlSession
     * @return
     */
    public static  SqlSession openSession(){
    	sqlSession = sqlSessionFactory.openSession();
    	
    	return sqlSession;
    }
    public static SqlSession getSqlSession() {
        //从当前线程中获取SqlSession对象
        SqlSession sqlSession = threadLocal.get();
        //如果SqlSession对象为空
        if (sqlSession == null) {
            //在SqlSessionFactory非空的情况下，获取SqlSession对象
            sqlSession = sqlSessionFactory.openSession();
            //将SqlSession对象与当前线程绑定在一起
            threadLocal.set(sqlSession);
        }
        //返回SqlSession对象
        return sqlSession;
    }
    /**
     * 关闭
     */
    public static void close(){
    	if(sqlSession != null){
    		sqlSession.close();
    	}
    }
}
