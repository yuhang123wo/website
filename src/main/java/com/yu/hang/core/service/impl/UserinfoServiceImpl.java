package com.yu.hang.core.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yu.hang.core.base.BaseDao;
import com.yu.hang.core.base.BaseServiceImpl;
import com.yu.hang.core.dao.UserinfoDao;
import com.yu.hang.core.domain.Userinfo;
import com.yu.hang.core.service.UserinfoService;

/**
 * @类说明：
 * 
 * @创建时间：2017-6-30 15:23:59
 */
@Service("userinfoService")
public class UserinfoServiceImpl extends BaseServiceImpl<Userinfo> implements UserinfoService {
        
	@Resource
	private UserinfoDao userinfoDao;
	
	public BaseDao<Userinfo> getDao() {
		return userinfoDao;
	}
}