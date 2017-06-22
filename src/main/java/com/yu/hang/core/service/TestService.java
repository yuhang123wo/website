package com.yu.hang.core.service;

import java.util.List;
import java.util.Map;

import com.yu.hang.core.base.BaseService;
import com.yu.hang.core.domain.Test;
/**
 * @类说明：
 * 
 * @创建时间：2017-6-19 17:25:32
 */
public interface TestService extends BaseService<Test> {

	List<Map<String,Object>> queryByMap();
}