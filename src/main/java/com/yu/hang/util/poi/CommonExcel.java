package com.yu.hang.util.poi;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellRangeAddress;

import com.yu.hang.util.ReflectionHelper;

/**
 * 
 * @author yuhang
 * @Date 2017年6月22日
 * @desc excel导入导出基类
 */
public class CommonExcel {

	protected int colsizeN = 630;
	protected int colsizeM = 1000;

	/**
	 * 导入Excel
	 * 
	 * @param clazz
	 * @param xls
	 * @return
	 * @throws Exception
	 */
	public List<Object> importExcel(Class<?> clazz, InputStream xls) throws Exception {
		try {
			// 取得Excel
			HSSFWorkbook wb = new HSSFWorkbook(xls);
			HSSFSheet sheet = wb.getSheetAt(0);
			Field[] fields = clazz.getDeclaredFields();
			List<Field> fieldList = new ArrayList<Field>(fields.length);
			for (Field field : fields) {
				if (field.isAnnotationPresent(ModelProp.class)) {
					ModelProp modelProp = field.getAnnotation(ModelProp.class);
					if (modelProp.colIndex() != -1) {
						fieldList.add(field);
					}
				}
			}
			// 行循环
			List<Object> modelList = new ArrayList<Object>(sheet.getPhysicalNumberOfRows() * 2);
			for (int i = 2; i < sheet.getPhysicalNumberOfRows(); i++) {
				// 数据模型
				Object model = clazz.newInstance();
				for (Field field : fieldList) {
					ModelProp modelProp = field.getAnnotation(ModelProp.class);
					HSSFCell cell = sheet.getRow(i).getCell(modelProp.colIndex());
					if (cell == null)
						continue;
					// 处理导入数据
					typeChange(field, model, cell);

				}
				modelList.add(model);
			}
			return modelList;

		} finally {
			xls.close();
		}
	}

	/**
	 * 下载Excel模版
	 * 
	 * @param clazz
	 * @param map
	 * @param rowSize
	 * @return
	 * @throws Exception
	 */
	public InputStream excelModelbyClass(Class<?> clazz, List<?> dataList) throws Exception {
		if (!clazz.isAnnotationPresent(ModelTitle.class)) {
			throw new Exception("请在此类型中加上ModelTitle注解");
		}
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet();
		// 创建第一行，并在该行创建单元格，设置内容，做为标题行
		createTitleStyle(wb, sheet, clazz);
		// 创建表头样式
		HSSFCellStyle headStyle = createHeadStyle(wb);
		// 创建第二行，设置表头
		int rowSize = dataList.size();
		int colSize = createHead(clazz.getDeclaredFields(), sheet, headStyle, rowSize);
		// 创建数据列
		createDataCloumn(wb, dataList, sheet);
		// 合并列
		createMerged(sheet, colSize);
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			wb.write(os);
			byte[] b = os.toByteArray();
			ByteArrayInputStream in = new ByteArrayInputStream(b);
			return in;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * 
	 * @param cell
	 * @return
	 */
	protected String parseString(HSSFCell cell) {
		return String.valueOf(cell).trim();
	}

	/**
	 * 日期转化
	 * 
	 * @param dateString
	 * @return
	 * @throws ParseException
	 */
	protected long parseDate(String dateString) throws ParseException {
		if (dateString.indexOf("/") == 4) {
			return new SimpleDateFormat("yyyy/MM/dd").parse(dateString).getTime();
		} else if (dateString.indexOf("-") == 4) {
			return new SimpleDateFormat("yyyy-MM-dd").parse(dateString).getTime();
		} else if (dateString.indexOf("年") == 4) {
			return new SimpleDateFormat("yyyy年MM月dd").parse(dateString).getTime();
		} else if (dateString.length() == 8) {
			return new SimpleDateFormat("yyyyMMdd").parse(dateString).getTime();
		} else {
			return new Date().getTime();
		}
	}

	/**
	 * 设置标题
	 * 
	 * @param wb
	 * @param sheet
	 * @param clazz
	 */
	protected void createTitleStyle(HSSFWorkbook wb, HSSFSheet sheet, Class<?> clazz) {
		HSSFCellStyle titleStyle = wb.createCellStyle();
		titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFFont font = wb.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setFontHeight((short) 400);
		titleStyle.setFont(font);
		HSSFCell titleCell = sheet.createRow(0).createCell(0);
		ModelTitle modelTitle = clazz.getAnnotation(ModelTitle.class);
		titleCell.setCellValue(new HSSFRichTextString(modelTitle.name()));
		titleCell.setCellStyle(titleStyle);
	}

	/**
	 * 创建表头样式
	 * 
	 * @param wb
	 * @return
	 */
	protected HSSFCellStyle createHeadStyle(HSSFWorkbook wb) {
		HSSFCellStyle headStyle = wb.createCellStyle();
		headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFFont headFont = wb.createFont();
		headFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		headFont.setFontHeight((short) 100);
		headStyle.setFont(headFont);
		return headStyle;
	}

	/**
	 * 创建表头
	 * 
	 * @param fields
	 * @param sheet
	 * @param headStyle
	 * @param rowSize
	 * @return
	 */
	private int createHead(Field[] fields, HSSFSheet sheet, HSSFCellStyle headStyle, int rowSize) {
		int colSize = 0;
		if (fields.length <= 0) {
			return colSize;
		}
		HSSFRow headRow = sheet.createRow(1);
		for (Field field : fields) {
			if (field.isAnnotationPresent(ModelProp.class)) {
				ModelProp modelProp = field.getAnnotation(ModelProp.class);
				if (modelProp.colIndex() == -1)
					continue;
				HSSFCell cell = headRow.createCell(modelProp.colIndex());
				cell.setCellValue(new HSSFRichTextString(modelProp.name()));
				cell.setCellStyle(headStyle);
				sheet.autoSizeColumn((short) modelProp.colIndex());
				sheet.setColumnWidth(modelProp.colIndex(), modelProp.name().length() * colsizeN
						+ colsizeM);
				colSize++;
			}
		}
		return colSize;
	}

	/**
	 * 创建数据列style
	 * 
	 * @param wb
	 * @return
	 */
	protected HSSFCellStyle createDataCellStyle(HSSFWorkbook wb) {
		HSSFCellStyle cellStyle = wb.createCellStyle();
		HSSFDataFormat format = wb.createDataFormat();
		cellStyle.setDataFormat(format.getFormat("@"));
		return cellStyle;
	}

	/**
	 * 创建数据列
	 * 
	 * @param wb
	 * @param dataList
	 * @param sheet
	 * @param colSize
	 */
	private void createDataCloumn(HSSFWorkbook wb, List<?> dataList, HSSFSheet sheet) {
		HSSFCellStyle cellStyle = createDataCellStyle(wb);
		for (int i = 0; i < dataList.size(); i++) {
			HSSFRow row = sheet.createRow(i + 2);
			Object obj = dataList.get(i);
			Field[] fields = obj.getClass().getDeclaredFields();
			for (Field field : fields) {
				if (!field.isAnnotationPresent(ModelProp.class))
					continue;
				ModelProp modelProp = field.getAnnotation(ModelProp.class);
				HSSFCell cell = row.createCell(modelProp.colIndex());
				cell.setCellStyle(cellStyle);
				cell.setCellValue(ReflectionHelper.invokeGetterMethod(obj, field.getName())
						.toString());
			}
		}
	}

	/**
	 * 
	 * @param sheet
	 * @param colSize
	 * @param map
	 */
	private void createMerged(HSSFSheet sheet, int colSize) {
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, colSize - 1));
	}

	/**
	 * 类型转换
	 * 
	 * @param field
	 * @param model
	 * @param cell
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws ParseException
	 */
	private void typeChange(Field field, Object model, HSSFCell cell) {
		try {
			if (field.getType().equals(Date.class)) {
				if (Cell.CELL_TYPE_STRING == cell.getCellType()) {
					BeanUtils.setProperty(model, field.getName(), new Date(
							parseDate(parseString(cell))));
				} else {
					BeanUtils.setProperty(model, field.getName(), new Date(cell.getDateCellValue()
							.getTime()));

				}
			} else if (field.getType().equals(Timestamp.class)) {
				if (Cell.CELL_TYPE_STRING == cell.getCellType()) {
					BeanUtils.setProperty(model, field.getName(), new Timestamp(
							parseDate(parseString(cell))));
				} else {
					BeanUtils.setProperty(model, field.getName(), new Timestamp(cell
							.getDateCellValue().getTime()));
				}

			} else if (field.getType().equals(java.sql.Date.class)) {
				if (Cell.CELL_TYPE_STRING == cell.getCellType()) {
					BeanUtils.setProperty(model, field.getName(), new java.sql.Date(
							parseDate(parseString(cell))));
				} else {
					BeanUtils.setProperty(model, field.getName(), new java.sql.Date(cell
							.getDateCellValue().getTime()));
				}
			} else if (field.getType().equals(java.lang.Integer.class)) {
				if (Cell.CELL_TYPE_NUMERIC == cell.getCellType()) {
					BeanUtils.setProperty(model, field.getName(), (int) cell.getNumericCellValue());
				} else if (Cell.CELL_TYPE_STRING == cell.getCellType()) {
					BeanUtils.setProperty(model, field.getName(),
							Integer.parseInt(parseString(cell)));
				}
			} else if (field.getType().equals(java.math.BigDecimal.class)) {
				if (Cell.CELL_TYPE_NUMERIC == cell.getCellType()) {
					BeanUtils.setProperty(model, field.getName(),
							new BigDecimal(cell.getNumericCellValue()));
				} else if (Cell.CELL_TYPE_STRING == cell.getCellType()) {
					BeanUtils
							.setProperty(model, field.getName(), new BigDecimal(parseString(cell)));
				}
			} else {
				if (Cell.CELL_TYPE_NUMERIC == cell.getCellType()) {
					BeanUtils.setProperty(model, field.getName(),
							new BigDecimal(cell.getNumericCellValue()));
				} else if (Cell.CELL_TYPE_STRING == cell.getCellType()) {
					BeanUtils.setProperty(model, field.getName(), parseString(cell));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
