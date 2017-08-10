package com.yu.hang.core.dao;

import org.apache.ibatis.annotations.Param;

import com.yu.hang.core.base.BaseDao;
import com.yu.hang.core.domain.LeaveFlow;

/**
 * @类说明：
 * 
 * @version 1.0
 * @创建时间：2017-8-9 14:27:19
 */
public interface LeaveDao extends BaseDao<LeaveFlow> {

	/**
	 * 
	 * @param instanceId
	 * @return LeaveFlow
	 */
	LeaveFlow queryByInstanceId(@Param("instanceId") String instanceId);
}