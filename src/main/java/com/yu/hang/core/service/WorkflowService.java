package com.yu.hang.core.service;

import java.util.List;

import org.activiti.engine.task.Task;

public interface WorkflowService {

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
	 *            void
	 */
	void processStartInstance(String key);

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

}
