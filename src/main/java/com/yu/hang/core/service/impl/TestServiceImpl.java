package com.yu.hang.core.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yu.hang.core.dao.TestMapper;
import com.yu.hang.core.domain.Test;
import com.yu.hang.core.domain.note.TestNode;
import com.yu.hang.core.service.TestService;

@Service("testService")
public class TestServiceImpl extends BaseServiceImpl<Test> implements TestService {

	@Resource
	public void setMapper(TestMapper mapper) {
		super.setMapper(mapper);
	}

	@Override
	public TestNode queryNode() {
		return ((TestMapper) mapper).queryNode();
	}
}
