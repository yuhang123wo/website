package com.yu.hang.core.service;

import java.util.List;
import java.util.Map;

import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.data.domain.Page;

import com.yu.hang.core.base.BaseService;
import com.yu.hang.core.domain.WorkFlow;

public interface WorkflowService extends BaseService<WorkFlow> {

	/**
	 * 部署流程
	 * 
	 * @param resourceId
	 *            void
	 */
	void processDeployWorkFlow(long resourceId);

	/**
	 * 启动一个流程
	 * 
	 * @param key
	 * @param authUser
	 * @param variables
	 * @return ProcessInstance
	 */
	ProcessInstance processStartInstance(String key, long authUser, Map<String, Object> variables);

	/**
	 * 查询任务
	 * 
	 * @param userId
	 * @return List<Task>
	 */
	List<Task> getTask(long userId);

	/**
	 * 
	 * @param taskId
	 *            void
	 */
	void updateTask(String taskId);

	/**
	 * 
	 * @param userId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * Page<Task>
	 */
	Page<Task> queryTask(long userId, int pageNo, int pageSize);
	
	/**
	 * 
	 * @param processInstanceId
	 * @return
	 * byte[]
	 */
	byte[] generateImage(String processInstanceId);

}
