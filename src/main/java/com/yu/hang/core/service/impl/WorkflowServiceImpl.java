package com.yu.hang.core.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.yu.hang.core.base.BaseDao;
import com.yu.hang.core.base.BaseServiceImpl;
import com.yu.hang.core.dao.WorkFlowDao;
import com.yu.hang.core.domain.WorkFlow;
import com.yu.hang.core.exception.ValiAutoHandedException;
import com.yu.hang.core.service.WorkflowService;
import com.yu.hang.util.Constant;
import com.yu.hang.vo.ValidateMsgVO;

@Service("workFlowService")
public class WorkflowServiceImpl extends BaseServiceImpl<WorkFlow> implements WorkflowService {

	// private static Logger logger =
	// LoggerFactory.getLogger(WorkflowServiceImpl.class);
	@Resource
	private RepositoryService repositoryService;
	@Resource
	private RuntimeService runtimeService;
	@Resource
	private TaskService taskService;
	@Resource
	private HistoryService historyService;
	@Resource
	private IdentityService identityService;
	@Resource
	private WorkFlowDao workFlowDao;

	@Override
	public void processDeployWorkFlow(long resourceId) {
		WorkFlow f = workFlowDao.queryById(resourceId);
		if (f == null)
			throw new ValiAutoHandedException(Constant.VALIDATE_ERROR, new ValidateMsgVO(
					"resourceId", "流程不存在", "workFlow"));
		repositoryService.createDeployment().name(f.getName())
				.addClasspathResource(Constant.DEPLOY_PATH + f.getSource())
				.addClasspathResource(Constant.DEPLOY_PATH + f.getSourceImg()).deploy();
	}

	@Override
	public ProcessInstance processStartInstance(String key, long authUser,
			Map<String, Object> variables) {
		ProcessInstance processInstance = null;
		try {
			// 用来设置启动流程的人员ID，引擎会自动把用户ID保存到activiti:initiator中
			identityService.setAuthenticatedUserId(String.valueOf(authUser));
			processInstance = runtimeService.startProcessInstanceByKey(key, variables);
		} finally {
			identityService.setAuthenticatedUserId(null);
		}
		return processInstance;
	}

	@Override
	public List<Task> getTask(long userId) {
		List<Task> tasks = taskService.createTaskQuery()// 创建一个任务查询对象
				.taskCandidateOrAssigned(String.valueOf(userId)).list();
		return tasks;
	}

	@Override
	public void updateTask(String taskId) {
		taskService.complete(taskId);
	}

	@Override
	public BaseDao<WorkFlow> getDao() {
		return workFlowDao;
	}

	@Override
	public Page<Task> queryTask(long userId, int pageNo, int pageSize) {
		PageRequest pageRequest = new PageRequest(pageNo - 1, pageSize);
		long count = taskService.createTaskQuery().taskCandidateOrAssigned(String.valueOf(userId))
				.count();
		List<Task> list = taskService
				.createTaskQuery()
				.taskCandidateOrAssigned(String.valueOf(userId))
				.listPage((pageNo - 1) * pageSize,
						pageNo * pageSize > count ? (int) count : pageNo * pageSize);
		return new PageImpl<Task>(list, pageRequest, count);
	}
}