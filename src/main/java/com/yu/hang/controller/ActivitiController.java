package com.yu.hang.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yu.hang.core.domain.LeaveFlow;
import com.yu.hang.core.domain.WorkFlow;
import com.yu.hang.core.service.LeaveService;
import com.yu.hang.core.service.WorkflowService;
import com.yu.hang.core.validate.ValidateUtil;
import com.yu.hang.util.ResultMsg;
import com.yu.hang.util.UserUtil;

/**
 * 流程管理控制器
 *
 */
@Controller
@RequestMapping("workFlow")
public class ActivitiController {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	private WorkflowService workflowService;
	@Resource
	private LeaveService leaveService;

	/**
	 * 部署一个流程
	 * 
	 * @param resourceId
	 *            void
	 */
	@RequestMapping("deploy")
	@ResponseBody
	public ResultMsg deploy(long resourceId) {
		workflowService.processDeployWorkFlow(resourceId);
		return new ResultMsg().success();
	}

	/**
	 * 
	 * @param taskId
	 * @return ResultMsg
	 */
	@RequestMapping("complete")
	@ResponseBody
	public ResultMsg updateTask(String taskId) {
		workflowService.updateTask(taskId);
		return new ResultMsg().success();
	}

	/**
	 * 
	 * @param model
	 * @return String
	 */
	@RequestMapping("listWorkFlow")
	public String listWorkFlow(Model model, HttpServletRequest request) {
		int pageNo = ServletRequestUtils.getIntParameter(request, "pageNo", 1);
		int pageSize = ServletRequestUtils.getIntParameter(request, "pageSize", 20);
		Page<WorkFlow> page = workflowService.queryPageByParmas(new HashMap<String, Object>(),
				pageNo, pageSize);
		model.addAttribute("list", page);
		return "sys.flow.index";
	}

	/**
	 * 请假列表
	 * 
	 * @param model
	 * @param request
	 * @return String
	 */
	@RequestMapping("leave/index")
	public String leaveIndex(Model model, HttpServletRequest request) {
		int pageNo = ServletRequestUtils.getIntParameter(request, "pageNo", 1);
		int pageSize = ServletRequestUtils.getIntParameter(request, "pageSize", 20);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", UserUtil.getUser().getId());
		Page<LeaveFlow> page = leaveService.queryPageByParmas(map, pageNo, pageSize);
		model.addAttribute("list", page);
		return "leave.index";
	}

	/**
	 * 请假视图
	 * 
	 * @return String
	 */
	@RequestMapping("leave/addView")
	public String leaveAddView() {
		return "leave.add";
	}

	/**
	 * 请假
	 * 
	 * @return String
	 */
	@RequestMapping("leave/add")
	@ResponseBody
	public ResultMsg leaveAdd(LeaveFlow leaveFlow) {
		ValidateUtil.validate(leaveFlow);
		leaveFlow.setUserId(UserUtil.getUser().getId());
		leaveFlow.setUsername(UserUtil.getUser().getUsername());
		leaveService.addLeave(leaveFlow);
		return new ResultMsg().success();
	}

	/**
	 * 获取待办事项
	 * 
	 * @param userId
	 * @param request
	 * @return ResultMsg
	 */
	@RequestMapping("waiting/index")
	public String waiting(HttpServletRequest request, Model model) {
		int pageNo = ServletRequestUtils.getIntParameter(request, "pageNo", 1);
		int pageSize = ServletRequestUtils.getIntParameter(request, "pageSize", 20);
		Page<LeaveFlow> list = leaveService.queryTask(2, pageNo, pageSize);
		model.addAttribute("list", list);
		return "waiting.index";
	}

}