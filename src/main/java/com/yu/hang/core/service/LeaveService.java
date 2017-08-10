package com.yu.hang.core.service;

import org.springframework.data.domain.Page;

import com.yu.hang.core.base.BaseService;
import com.yu.hang.core.domain.LeaveFlow;

/**
 * @类说明：
 * 
 * @创建时间：2017-8-9 14:27:19
 */
public interface LeaveService extends BaseService<LeaveFlow> {

	/**
	 * 新增请假
	 * 
	 * @param leaveFlow
	 *            void
	 */
	void addLeave(LeaveFlow leaveFlow);

	/**
	 * 查询请假流程
	 * 
	 * @param userId
	 * @param pageNo
	 * @param pageSize
	 * @return Page<LeaveFlow>
	 */
	Page<LeaveFlow> queryTask(long userId, int pageNo, int pageSize);

}