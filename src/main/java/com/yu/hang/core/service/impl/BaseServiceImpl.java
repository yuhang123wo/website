package com.yu.hang.core.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

	// @Override
	// public void delete(JSonDtoModel in, JSonDtoModel out) throws Exception {
	// Long id = in.getLongByKey(Constant.ID);
	// Assert.isTrue(StringHelper.isNotEmpty(id), "id为空");
	//
	// int rows = mapper.delete(id);
	// Assert.isTrue(rows > 0, "删除失败");
	// out.put(Constant.ROWS, rows);
	// }
	//
	// @Override
	// public void update(JSonDtoModel in, JSonDtoModel out) throws Exception {
	// Map<String, Object> props = in.getObjectByKey(Constant.MAP, Map.class);
	// Long id = in.getLongByKey(Constant.ID);
	// Assert.isTrue(StringHelper.isNotEmpty(id), "id不为空");
	//
	// if (StringHelper.isEmpty(props)) {
	// props = new HashMap<>();
	// }
	// if (props.size() == 0) {
	// props.put("id", id);
	// }
	//
	// int rows = mapper.update(props, id);
	//
	// Assert.isTrue(rows > 0, "修改失败");
	// out.put(Constant.ROWS, rows);
	// }
	//
	// @Override
	// public void saveBatch(JSonDtoModel in, JSonDtoModel out) throws Exception
	// {
	// List<T> list = in.getArrayByKey(Constant.LIST, clazz);
	// Assert.isTrue(StringHelper.isNotEmpty(list) && list.size() > 0,
	// "list为空");
	//
	// int rows = mapper.saveBatch(list);
	// Assert.isTrue(rows > 0, "批量添加失败");
	// out.put(Constant.ROWS, rows);
	// }
	//
	// @Override
	// public void updateBatch(JSonDtoModel in, JSonDtoModel out) throws
	// Exception {
	//
	// Map<String, Object> props = in.getObjectByKey(Constant.MAP, Map.class);
	// List<Long> ids = in.getArrayByKey(Constant.IDS, Long.class);
	//
	// Assert.isTrue(StringHelper.isNotEmpty(props), "props为空");
	// Assert.isTrue(StringHelper.isNotEmpty(ids) && ids.size() > 0, "ids为空");
	//
	// int rows = mapper.updateBatch(props, ids);
	//
	// Assert.isTrue(rows > 0, "批量修改失败");
	// out.put(Constant.ROWS, rows);
	// }
	//
	// @Override
	// public void queryById(JSonDtoModel in, JSonDtoModel out) throws Exception
	// {
	// Long id = in.getLongByKey(Constant.ID);
	// Assert.isTrue(StringHelper.isNotEmpty(id), "主键为空");
	//
	// T model = mapper.queryById(id);
	//
	// out.put(Constant.MODEL, model);
	// }
	//
	// @Override
	// public void queryForList(JSonDtoModel in, JSonDtoModel out) throws
	// Exception {
	// Map<String, Object> props = in.getObjectByKey(Constant.MAP, Map.class);
	//
	// List<T> list = mapper.queryForList(props);
	//
	// out.put(Constant.LIST, list);
	// }
	//
	// @Override
	// public void queryForListExt(JSonDtoModel in, JSonDtoModel out) throws
	// Exception {
	// Map<String, Object> props = in.getObjectByKey(Constant.MAP, Map.class);
	// Map<String, Object> ext = in.getObjectByKey(Constant.EXT, Map.class);
	//
	// List<T> list = mapper.queryForListExt(props, ext);
	//
	// out.put(Constant.LIST, list);
	// }
	//
	// @Override
	// public void deleteBatch(JSonDtoModel in, JSonDtoModel out) throws
	// Exception {
	// List<Long> ids = in.getArrayByKey(Constant.IDS, Long.class);
	// Assert.isTrue(StringHelper.isNotEmpty(ids) && ids.size() > 0,
	// "主键列表为空或无数据");
	//
	// int rows = mapper.deleteBatch(ids);
	//
	// Assert.isTrue(rows > 0, "批量删除失败");
	// out.put(Constant.ROWS, rows);
	// }
	//
	// @Override
	// public void selectById(JSonDtoModel in, JSonDtoModel out) throws
	// Exception {
	// Long id = in.getLongByKey(Constant.ID);
	// Assert.isTrue(StringHelper.isNotEmpty(id), "主键为空");
	//
	// Map<String, Object> map = mapper.selectById(id);
	//
	// out.put(Constant.MAP, map);
	// }
	//
	// @Override
	// public void selectList(JSonDtoModel in, JSonDtoModel out) throws
	// Exception {
	// Map<String, Object> props = in.getObjectByKey(Constant.MAP, Map.class);
	//
	// List<Map<String, Object>> list = mapper.selectList(props);
	// out.put(Constant.LIST, list);
	// }
	//
	// @Override
	// public void pagination(JSonDtoModel in, JSonDtoModel out) throws
	// Exception {
	//
	// Pager pager = in.getObjectByKey(Constant.PAGER, Pager.class);
	// Assert.isTrue(StringHelper.isNotEmpty(pager), "pager为空");
	//
	// Map<String, Object> props = new HashMap<String, Object>();
	// Map<String, Object> params = pager.getProps();
	//
	// Set<String> keys = params.keySet();
	// for (Iterator<String> it = keys.iterator(); it.hasNext();) {
	// try {
	// String key = it.next();
	// Object value = params.get(key);
	//
	// if (StringHelper.isNotEmpty(value)) {
	// props.put(key, value);
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	//
	// Integer size = pager.getLimit();
	// Integer start = pager.getOffset();
	// String orderBy = pager.getOrderBy();
	// props.put(Constant.START, start);
	// props.put(Constant.SIZE, size);
	// props.put(Constant.ORDER_BY, orderBy);
	// // 查询总数
	// Integer total = mapper.selectCount(props);
	// // 查询显示行
	// List<Map<String, Object>> list = mapper.pageList(props);
	//
	// Pager res = new Pager();
	// res.setTotal(total);
	// res.setList(list);
	//
	// out.put(Constant.TOTAL, res.getTotal());
	// out.put(Constant.ROWS, res.getList());
	// }
	//
	// @Override
	// public void checkRepeated(JSonDtoModel in, JSonDtoModel out) throws
	// Exception {
	// Map<String, Object> params = in.getObjectByKey(Constant.MAP, Map.class);
	// Map<String, Object> props = new HashMap<String, Object>();
	//
	// Set<String> keys = params.keySet();
	// for (Iterator<String> it = keys.iterator(); it.hasNext();) {
	// try {
	// String key = it.next();
	// Object value = params.get(key);
	//
	// if (StringHelper.isNotEmpty(value)) {
	// props.put(key, value);
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	//
	// Long id = in.getLongByKey(Constant.ID);
	//
	// boolean flag = false;
	// flag = mapper.checkRepeated(props, id) > 0 ? false : true;
	// out.put(Constant.REPEAT, flag);
	// }

}
