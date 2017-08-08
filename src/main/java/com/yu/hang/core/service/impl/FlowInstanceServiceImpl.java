package com.yu.hang.core.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yu.hang.core.base.BaseDao;
import com.yu.hang.core.base.BaseServiceImpl;
import com.yu.hang.core.dao.FlowInstanceDao;
import com.yu.hang.core.domain.FlowInstance;
import com.yu.hang.core.service.FlowInstanceService;

/**
 * @类说明：
 * 
 * @创建时间：2017-8-8 17:32:50
 */
@Service("flowInstanceService")
public class FlowInstanceServiceImpl extends BaseServiceImpl<FlowInstance> implements FlowInstanceService {
        
	@Resource
	private FlowInstanceDao flowInstanceDao;
	
	public BaseDao<FlowInstance> getDao() {
		return flowInstanceDao;
	}
}