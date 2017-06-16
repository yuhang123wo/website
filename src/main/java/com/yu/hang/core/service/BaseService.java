package com.yu.hang.core.service;

import java.util.List;

import com.yu.hang.core.base.BaseModel;

/**
 * 基础service
 * 
 * @author yuhang
 * @Date 2017年6月8日
 * @desc
 * @param <T>
 */
public interface BaseService<T extends BaseModel> {
	void save(T t);

	int saveBatch(List<T> list);

	void delete(Long id);

	void deleteBatch(List<Long> ids);

	void queryById(Long id);

}
