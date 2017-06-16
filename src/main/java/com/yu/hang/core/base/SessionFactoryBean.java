package com.yu.hang.core.base;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;

/**
 * 
 * @author yuhang
 * @Date 2017年6月8日
 * @desc
 */
public class SessionFactoryBean extends SqlSessionFactoryBean {

	@Override
	protected SqlSessionFactory buildSqlSessionFactory() {
		SqlSessionFactory buildSqlSessionFactory = null;
		try {
			buildSqlSessionFactory = super.buildSqlSessionFactory();
		} catch (Exception e) {
			System.err.println("mybaits配置文件出错：" + e.getMessage());
			e.printStackTrace();
		}
		return buildSqlSessionFactory;
	}

}
