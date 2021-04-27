package com.util;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisCommonFactory {
	private static SqlSessionFactory sqlSessionFactory = null;
	public static SqlSessionFactory getInstance() {
		if (sqlSessionFactory == null) {
			synchronized (SqlSessionFactory.class) {
				if (sqlSessionFactory == null) {
					try {
						String resource = "com/util/MapperConfig.xml";
						Reader reader = Resources.getResourceAsReader(resource);
						sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return sqlSessionFactory;
	}
}