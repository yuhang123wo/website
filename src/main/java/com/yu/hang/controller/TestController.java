package com.yu.hang.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
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

	@RequestMapping(value = "test/notify")
	@ResponseBody
	public String alipayNotify(HttpServletRequest request, Model model) throws IOException {
		// 获取支付宝GET过来反馈信息
		InputStream inStream = request.getInputStream();
		ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
			outSteam.write(buffer, 0, len);
		}
		outSteam.close();
		inStream.close();
		String result = new String(outSteam.toByteArray(), "utf-8");
		System.out.println(result);

		return "fail";
	}

	@RequestMapping("test/testm")
	public void testm() throws Exception {
		String text = "weixin://wxpay/bizpayurl?pr=XmlAkwW";
		generateQRCode(text, 500, 500, "png");
	}

	private static String generateQRCode(String text, int width, int height, String format)
			throws Exception {
		Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width,
				height, hints);
		String pathName = "D:/new.png";
		File outputFile = new File(pathName);
		MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);
		return pathName;
	}

	public static String formatDate(Date date, String pattern) {
		return new SimpleDateFormat(pattern).format(date);
	}

	public static long dateTimes(Date date) {
		String dateS = formatDate(date, "yyyy-MM-dd HH:mm");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date dateTime = null;
		long times = 0;
		if (date != null) {
			try {
				dateTime = sdf.parse(dateS);
				times = dateTime.getTime();
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return times;
	}

	public static void main(String[] args) throws Exception {

		String text = "weixin://wxpay/bizpayurl?pr=rkTJ3vq";
		generateQRCode(text, 200, 200, "png");
	}
}
