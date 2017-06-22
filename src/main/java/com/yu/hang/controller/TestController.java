package com.yu.hang.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yu.hang.core.domain.Test;
import com.yu.hang.core.service.TestService;
import com.yu.hang.util.poi.CommonExcel;
import com.yu.hang.util.poi.ExcelUtil;
import com.yu.hang.util.poi.ExportSetInfo;

@Controller
public class TestController {

	@Resource
	private TestService testService;

	@RequestMapping("testN")
	public void testN(HttpServletResponse response) throws IOException, IllegalArgumentException,
			IllegalAccessException {
		Test t = new Test();
		t.setName("g");
		testService.save(t);
		Test note = testService.queryById(10L);
		ExportSetInfo setInfo = new ExportSetInfo();
		List<String[]> headNames = new ArrayList<String[]>();
		headNames.add(new String[] { "用户名", "密码", "电子邮件" });
		LinkedHashMap<String, List<?>> map = new LinkedHashMap<String, List<?>>();
		map.put("xxx", testService.queryByParmas(new HashMap<String, Object>()));
		setInfo.setObjsMap(map);
		// setInfo.setFieldNames("");testService.queryByMap()
		setInfo.setTitles(new String[] { "馋八戒后台用户信息" });
		setInfo.setHeadNames(headNames);
		setInfo.setOut(response.getOutputStream());
		// 将需要导出的数据输出到baos
		ExcelUtil.export2Excel(setInfo);

	}

	@RequestMapping(value = "/downloadEmployeeModel")
	public void downloadEmployeeModel(HttpServletResponse response) {
		try {
			response.setContentType("application/xls");
			response.addHeader("Content-Disposition", "attachment;filename="
					+ new String(("eeelist").getBytes("UTF-8"), "iso-8859-1") + ".xls");
			Map<Integer, String[]> paramMap = new HashMap<Integer, String[]>();
			// excel第三行为下拉选择框
			paramMap.put(1, new String[] { "man", "women" });
			CommonExcel base = new CommonExcel();
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("start", 1);
			params.put("size", 100);
			BufferedInputStream input = new BufferedInputStream(base.excelModelbyClass(Test.class,
					testService.queryByParmas(params)));
			byte buffBytes[] = new byte[1024];
			OutputStream os = response.getOutputStream();
			int read = 0;
			while ((read = input.read(buffBytes)) != -1) {
				os.write(buffBytes, 0, read);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
