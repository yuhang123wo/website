package com.yu.hang.core.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yu.hang.core.base.BaseDao;
import com.yu.hang.core.base.BaseServiceImpl;
import com.yu.hang.core.dao.TestDao;
import com.yu.hang.core.domain.Test;
import com.yu.hang.core.service.TestService;

/**
 * @类说明：
 * 
 * @创建时间：2017-6-19 17:25:32
 */
@Service("testService")
public class TestServiceImpl extends BaseServiceImpl<Test> implements TestService {

	@Resource
	private TestDao testDao;

	public BaseDao<Test> getDao() {
		return testDao;
	}

}