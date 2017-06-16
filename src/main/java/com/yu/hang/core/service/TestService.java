package com.yu.hang.core.service;

import com.yu.hang.core.domain.Test;
import com.yu.hang.core.domain.note.TestNode;

public interface TestService extends BaseService<Test> {
	TestNode queryNode();
}
