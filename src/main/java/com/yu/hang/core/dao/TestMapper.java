package com.yu.hang.core.dao;

import com.yu.hang.core.base.BaseMapper;
import com.yu.hang.core.domain.Test;
import com.yu.hang.core.domain.note.TestNode;

public interface TestMapper extends BaseMapper<Test> {

	TestNode queryNode();
}
