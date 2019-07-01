package com.web.test;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.web.dao.PositionDao;
import com.web.entity.TPosition;
import com.web.util.MyBatisUtil;

public class TestPosition {

	@Test
	public void testName() throws Exception {
		//1、读取配置文件   2、获取SqlSessionFactory  3、获取SqlSession对象  4、执行操作
		//5、 提交
		//1、获取SqlSession对象
		SqlSession sqlSession = MyBatisUtil.openSession();
		
		PositionDao pd = sqlSession.getMapper(PositionDao.class);
		
		TPosition tposition = new TPosition();
		tposition.setDeptId(10);
		tposition.setIsDelete(0);
		tposition.setPoName("前台2");
		tposition.setPoSalary(6000.0);
		
		pd.addPosition(tposition);
		
		//提交
		sqlSession.commit();
		
		System.out.println("主键:"+tposition.getPoId());
		
	}
	
	@Test
	public void testUpdate() throws Exception {
		SqlSession sqlSession = MyBatisUtil.openSession();
		PositionDao pd = sqlSession.getMapper(PositionDao.class);
		
		TPosition postion = new TPosition();
		postion.setPoId(12);
		postion.setIsDelete(0);
		postion.setDeptId(10);
		postion.setPoName("财务");
		postion.setPoSalary(5000.0);
		
		pd.updatePostion(postion);
		
		//提交
		sqlSession.commit();
	}
	
	@Test
	public void testQuery() throws Exception {
		SqlSession sqlSession = MyBatisUtil.openSession();
		PositionDao pd = sqlSession.getMapper(PositionDao.class);
		
		List<TPosition> list = pd.query();
		
		for (TPosition tPosition : list) {
			System.out.println("职位名称:"+tPosition.getPoName()+",科室名称:"+tPosition.getDepartment().getDeptName());
		}
	}
	
	@Test
	public void testQuery1() throws Exception {
		SqlSession sqlSession = MyBatisUtil.openSession();
		
		List<TPosition> list = sqlSession.selectList("com.web.dao.PositionDao.query");
		for (TPosition tPosition : list) {
			System.out.println("职位名称:"+tPosition.getPoName());
		}
	}
	
	
	@Test
	public void testQueryOne() throws Exception {
		SqlSession sqlSession = MyBatisUtil.openSession();
		
		TPosition tposition=sqlSession.selectOne("com.web.dao.PositionDao.findByID",1);
		
		System.out.println("职位名称:"+tposition.getPoName());
		
	}
	
	@Test
	public void testQueryName() throws Exception {
		SqlSession sqlSession = MyBatisUtil.openSession();
		
		List<TPosition> list =sqlSession.selectList("com.web.dao.PositionDao.queryByName","医");
		
		for (TPosition tPosition : list) {
			System.out.println("职位名称:"+tPosition.getPoName());
		}
		
	}
	
	
	@Test
	public void testDelete() throws Exception {
		SqlSession sqlSession = MyBatisUtil.openSession();
		sqlSession.delete("com.web.dao.PositionDao.delete",13);
		sqlSession.commit();
	}
	
}
