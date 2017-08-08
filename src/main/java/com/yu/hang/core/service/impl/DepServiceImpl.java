package com.yu.hang.core.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yu.hang.core.base.BaseDao;
import com.yu.hang.core.base.BaseServiceImpl;
import com.yu.hang.core.dao.DepDao;
import com.yu.hang.core.domain.Dep;
import com.yu.hang.core.service.DepService;

/**
 * @类说明：
 * 
 * @创建时间：2017-8-7 14:55:26
 */
@Service("depService")
public class DepServiceImpl extends BaseServiceImpl<Dep> implements DepService {
        
	@Resource
	private DepDao depDao;
	
	public BaseDao<Dep> getDao() {
		return depDao;
	}
}