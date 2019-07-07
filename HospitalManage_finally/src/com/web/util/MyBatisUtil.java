package com.web.util;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtil {

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
    
    /**
     * 关闭
     */
    public static void close(){
    	if(sqlSession != null){
    		sqlSession.close();
    	}
    }
}
