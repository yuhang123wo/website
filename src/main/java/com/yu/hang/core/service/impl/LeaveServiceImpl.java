package com.yu.hang.core.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yu.hang.core.base.BaseDao;
import com.yu.hang.core.base.BaseServiceImpl;
import com.yu.hang.core.dao.LeaveDao;
import com.yu.hang.core.domain.LeaveFlow;
import com.yu.hang.core.exception.ValiAutoHandedException;
import com.yu.hang.core.service.LeaveService;
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
	}
}