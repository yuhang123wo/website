package com.yu.hang.controller;

import java.io.BufferedInputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.yu.hang.core.domain.Test;
import com.yu.hang.core.service.TestService;
import com.yu.hang.util.poi.CommonExcel;

@Controller
public class TestController {

	@Resource
	private TestService testService;

	@RequestMapping(value = "/importEmployee")
	public void uploadExcel(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		try {
			CommonExcel excel = new CommonExcel();
			List<Object> employeeDTOList = excel.importExcel(Test.class, file.getInputStream());
			// 可做持久化操作，现只打印观察
			for (Object employeeDTO : employeeDTOList) {
				System.out.println(((Test) employeeDTO).toString());
			}
		} catch (Exception e) {
		}
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
