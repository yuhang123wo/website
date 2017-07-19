package com.yu.hang.util;

import java.io.File;
import java.util.Hashtable;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.yu.hang.controller.MatrixToImageWriter;

/**
 * 
 * @author yuhang
 * @Date 2017年7月19日
 * @desc 二维码工类
 */
public class BitMatrixUtil {

	public static String generateQRCode(String text, int width, int height, String format)
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
}
