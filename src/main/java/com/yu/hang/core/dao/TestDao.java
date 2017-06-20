package com.yu.hang.core.dao;

import com.yu.hang.core.base.BaseDao;
import com.yu.hang.core.domain.Test;
import com.yu.hang.core.domain.note.TestNode;

public interface TestDao extends BaseDao<Test> {

	TestNode queryNode();
}
