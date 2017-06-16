package com.yu.hang.core.base;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 
 * @author yuhang
 * @Date 2017年6月8日
 * @desc
 * @param <T>
 */
public interface BaseMapper<T extends BaseModel> {

	/**
	 * 保存业务对象
	 * 
	 * @param model
	 * @throws Exception
	 */
	void save(T model);

	/**
	 * 批量保存业务对象列表
	 * 
	 * @param list
	 * @return
	 * @throws Exception
	 */
	int saveBatch(List<T> list);

	/**
	 * 按主键删除业务对象
	 * 
	 * @param id
	 * @return
	 */
	int delete(Long id);

	/**
	 * 主键列表删除业务对象列表
	 * 
	 * @param ids
	 * @return
	 */
	int deleteBatch(List<Long> ids);

	/**
	 * 根据ID查询
	 * 
	 * @param id
	 * @return
	 */
	T queryById(@Param("id") Long id);

	/**
	 * 查询单个
	 * 
	 * @param props
	 * @return
	 */
	T queryOne(@Param("props") Map<String, Object> props);

}
