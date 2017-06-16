package com.yu.hang.core.service.impl;

import java.util.List;

import org.springframework.util.Assert;

import com.yu.hang.core.base.BaseMapper;
import com.yu.hang.core.base.BaseModel;
import com.yu.hang.core.service.BaseService;
import com.yu.hang.util.ReflectionHelper;
import com.yu.hang.util.StringHelper;

public class BaseServiceImpl<T extends BaseModel> implements BaseService<T> {

	protected Class<T> clazz;

	public BaseServiceImpl() {
		clazz = ReflectionHelper.getClassGenricType(getClass());
	}

	/**
	 * dao 层实现类
	 */
	protected BaseMapper<T> mapper;

	/**
	 * 设置 dao
	 * 
	 * @param dao
	 */
	public void setMapper(BaseMapper<T> mapper) {
		this.mapper = mapper;
	}

	@Override
	public void save(T t) {
		Assert.isTrue(StringHelper.objectIsNotNull(t), "model为空");
		System.out.println(mapper==null);
		mapper.save(t);
	}

	@Override
	public int saveBatch(List<T> list) {
		Assert.isTrue(StringHelper.listIsNotNull(list), "model为空");
		return mapper.saveBatch(list);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteBatch(List<Long> ids) {
		// TODO Auto-generated method stub

	}

	@Override
	public void queryById(Long id) {
		// TODO Auto-generated method stub

	}

}
