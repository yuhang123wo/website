package com.yu.hang.core.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.yu.hang.core.base.BaseDao;
import com.yu.hang.core.base.BaseServiceImpl;
import com.yu.hang.core.dao.LeaveDao;
import com.yu.hang.core.domain.LeaveFlow;
import com.yu.hang.core.domain.Userinfo;
import com.yu.hang.core.exception.ValiAutoHandedException;
import com.yu.hang.core.service.LeaveService;
import com.yu.hang.core.service.UserinfoService;
import com.yu.hang.core.service.WorkflowService;
import com.yu.hang.util.Constant;
import com.yu.hang.util.DateHelper;
import com.yu.hang.vo.ValidateMsgVO;

/**
 * @类说明：
 * 
 * @创建时间：2017-8-9 14:27:19
 */
@Service("leaveService")
public class LeaveServiceImpl extends BaseServiceImpl<LeaveFlow> implements LeaveService {

	@Resource
	private LeaveDao leaveDao;
	@Resource
	private WorkflowService workflowService;
	@Resource
	private UserinfoService userinfoService;

	public BaseDao<LeaveFlow> getDao() {
		return leaveDao;
	}

	@Override
	public void addLeave(LeaveFlow leaveFlow) {
		Date startTime = DateHelper.parseDatePattern(leaveFlow.getStartTime(),
				DateHelper.YYYY_MM_DD_HH);
		Date endTime = DateHelper
				.parseDatePattern(leaveFlow.getEndTime(), DateHelper.YYYY_MM_DD_HH);
		int gap = DateHelper.getGap(startTime, endTime);
		if (gap <= 0)
			throw new ValiAutoHandedException(Constant.VALIDATE_ERROR, new ValidateMsgVO());
		leaveFlow.setDayCount(gap);
		leaveDao.save(leaveFlow);
		// 请假流程
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", Constant.DEPT_GRADE_M);// 查询部门主管
		List<Userinfo> list = userinfoService.queryByParmas(map);
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("inputUser", list.get(0).getId());
		ProcessInstance instance = workflowService.processStartInstance("myProcess",
				leaveFlow.getUserId(), variables);
		leaveFlow.setInstanceId(instance.getId());
		leaveDao.update(leaveFlow);
	}
	@Override
	public Page<LeaveFlow> queryTask(long userId, int pageNo, int pageSize) {
		Page<Task> tasks = workflowService.queryTask(userId, pageNo, pageSize);
		if (tasks.getTotalElements() <= 0)
			return new PageImpl<LeaveFlow>(new ArrayList<LeaveFlow>(), new PageRequest(pageNo - 1,
					pageSize), 0);
		List<Task> list = tasks.getContent();
		List<LeaveFlow> result = new ArrayList<LeaveFlow>();
		// 根据流程的业务ID查询实体并关联
		for (Task task : list) {
			String processInstanceId = task.getProcessInstanceId();
			LeaveFlow leave = leaveDao.queryByInstanceId(processInstanceId);
			leave.setTask(task);
			result.add(leave);
		}
		return new PageImpl<LeaveFlow>(result, new PageRequest(pageNo - 1, pageSize),
				tasks.getTotalElements());
	}
}