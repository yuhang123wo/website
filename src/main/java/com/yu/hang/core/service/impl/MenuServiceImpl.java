package com.yu.hang.core.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yu.hang.core.base.BaseDao;
import com.yu.hang.core.base.BaseServiceImpl;
import com.yu.hang.core.dao.MenuDao;
import com.yu.hang.core.domain.Menu;
import com.yu.hang.core.service.MenuService;

/**
 * @类说明：
 * 
 * @创建时间：2017-6-30 15:23:59
 */
@Service("menuService")
public class MenuServiceImpl extends BaseServiceImpl<Menu> implements MenuService {
        
	@Resource
	private MenuDao menuDao;
	
	public BaseDao<Menu> getDao() {
		return menuDao;
	}
}