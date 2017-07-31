package com.yu.hang.core.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.yu.hang.core.base.BaseDao;
import com.yu.hang.core.base.BaseServiceImpl;
import com.yu.hang.core.dao.UserinfoDao;
import com.yu.hang.core.domain.Menu;
import com.yu.hang.core.domain.Userinfo;
import com.yu.hang.core.domain.note.TreeNode;
import com.yu.hang.core.service.MenuService;
import com.yu.hang.core.service.RoleMenuService;
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
	@Resource
	private MenuService menuService;
	@Resource
	private RoleMenuService roleMenuService;

	public BaseDao<Userinfo> getDao() {
		return userinfoDao;
	}

	@Override
	public List<TreeNode> listAllMenu() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("parentId", Menu.isRoot);
		List<Menu> list = menuService.queryByParmas(map);
		List<TreeNode> nodes = new ArrayList<TreeNode>();
		for (int i = 0; i < list.size(); i++) {
			nodes.add(recursiveTree(list.get(i).getId()));
		}
		return nodes;
	}

	private TreeNode recursiveTree(long cid) {
		Menu m = menuService.queryById(cid);
		TreeNode node = new TreeNode();
		node.setId(cid);
		node.setText(m.getName());
		// 查询cid下的所有子节点(SELECT * FROM tb_tree t WHERE t.pid=?)
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("parentId", m.getId());
		List<Menu> childTreeNodes = menuService.queryByParmas(map);
		// 遍历子节点
		for (int i = 0; i < childTreeNodes.size(); i++) {
			TreeNode n = recursiveTree(childTreeNodes.get(i).getId()); // 递归
			node.getChildren().add(n);
		}
		return node;
	}

	@Override
	public Userinfo queryByName(String username) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("username", username);
		List<Userinfo> list = userinfoDao.queryByParmas(map);
		if (!CollectionUtils.isEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<TreeNode> listMenuByRole(long roleId) {
		List<TreeNode> list = this.listAllMenu();
		List<Long> roleList = roleMenuService.findMenuByRole(roleId);
		if (!CollectionUtils.isEmpty(list)) {
			for (int i = 0; i < list.size(); i++) {
				TreeNode node = list.get(i);
				if (roleList.contains(node.getId()))
					node.setChecked(true);
			}
		}
		return list;
	}
}