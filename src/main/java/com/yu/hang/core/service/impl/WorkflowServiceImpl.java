package com.yu.hang.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Service;

import com.yu.hang.core.domain.Flow;
import com.yu.hang.core.service.WorkflowService;

@Service("workFlowService")
public class WorkflowServiceImpl implements WorkflowService {

	//private static Logger logger = LoggerFactory.getLogger(WorkflowServiceImpl.class);
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

	@Override
	public void processDeployWorkFlow(long resourceId) {
		Flow f = new Flow();
		f.setName("leave");
		f.setSource("deployments/leave.bpmn");
		f.setSourceImg("deployments/leave.png");
		repositoryService.createDeployment().name(f.getName()).addClasspathResource(f.getSource())
				.addClasspathResource(f.getSourceImg()).deploy();
	}

	@Override
	public void processStartInstance(String key) {
		ProcessInstance instance = runtimeService.startProcessInstanceByKey("LeaveBill");
		System.out.println(instance.getProcessDefinitionName());
	}

	@Override
	public List<Task> getTask(long userId) {
		List<Task> tasks = taskService.createTaskQuery()// 创建一个任务查询对象
				.taskAssignee(String.valueOf(userId)).list();
		return tasks;
	}

	@Override
	public void updateTask(String taskId) {
		taskService.complete(taskId);
	}
}