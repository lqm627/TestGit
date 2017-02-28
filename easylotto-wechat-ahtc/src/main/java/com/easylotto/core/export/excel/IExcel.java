package com.easylotto.core.export.excel;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.PageOrder;
import jxl.format.PaperSize;
import jxl.format.VerticalAlignment;
import jxl.write.Formula;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableImage;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class IExcel {

	final static String EXCEL_FONT_XXMT = "華康新儷粗黑";

	private List<IExcelCell> excelCells;
	private WritableWorkbook work;
	private File file;
	private jxl.write.WritableSheet sheet;
	private String fileName;
	private String title;
	private String code;
	private String[] fields;
	private String[] fieldNames;
	private String sheetName;
	private Map<String, Object> defaultValue;
	private Map<String, String> defaultPlays;
	private Map<String, String> totalValue;
	private String formatFidlds;

	public WritableWorkbook getWork() {
		return work;
	}

	public void setWork(WritableWorkbook work) {
		this.work = work;
	}

	public jxl.write.WritableSheet getSheet() {
		return sheet;
	}

	public void setSheet(jxl.write.WritableSheet sheet) {
		this.sheet = sheet;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String[] getFields() {
		return fields;
	}

	public void setFields(String[] fields) {
		this.fields = fields;
	}

	public String[] getFieldNames() {
		return fieldNames;
	}

	public void setFieldNames(String[] fieldNames) {
		this.fieldNames = fieldNames;
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public Map<String, Object> getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(Map<String, Object> defaultValue) {
		this.defaultValue = defaultValue;
	}

	public Map<String, String> getDefaultPlays() {
		return defaultPlays;
	}

	public void setDefaultPlays(Map<String, String> defaultPlays) {
		this.defaultPlays = defaultPlays;
	}

	public Map<String, String> getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(Map<String, String> totalValue) {
		this.totalValue = totalValue;
	}

	public String getFormatFidlds() {
		return formatFidlds;
	}

	public void setFormatFidlds(String formatFidlds) {
		this.formatFidlds = formatFidlds;
	}

	
	public List<IExcelCell> getExcelCells() {
		return excelCells;
	}

	public void setExcelCells(List<IExcelCell> excelCells) {
		this.excelCells = excelCells;
	}
	
	public void add(IExcelCell cell){
		excelCells.add(cell);
	}

	/**
	 * 创建 Excel 文件
	 * 
	 * @param path
	 * @param sheetName
	 */
	public void create(String path) {
		create(new File(path));
	}

	/**
	 * 创建path Excel 文件 并 创建名为sheetName 的表格
	 * 
	 * @param path
	 * @param sheetName
	 */
	public void create(String path, String sheetName) {
		create(new File(path), sheetName);
	}

	/**
	 * 创建Excel 文件
	 * 
	 * @param file
	 */
	public void create(File file) {
		try {
			this.setFile(file);
			work = Workbook.createWorkbook(file);
		} catch (IOException e) {
			logger.error(" Export Excel File", e.getMessage());
		}
	}

	/**
	 * 创建Excel 文件 并 创建名为sheetName 的表格
	 * 
	 * @param file
	 * @param sheetName
	 */
	public void create(File file, String sheetName) {
		try {
			work = Workbook.createWorkbook(file);
			sheet = work.createSheet(sheetName, 0);
			setSheetName(sheetName);
		} catch (IOException e) {
			logger.error(" Export Excel File", e.getMessage());
		}
	}
	
	/**
	 * 打印设置  A4 从上往下 
	 * @param firstCol
	 * @param firstRow
	 * @param lastCol
	 * @param lastRow
	 * @param scaleFactor 打印调整比例
	 */
	public void setSettings(int firstCol, int firstRow, int lastCol, int lastRow, int scaleFactor){
		sheet.getSettings().setPaperSize(PaperSize.A4);
		sheet.getSettings().setScaleFactor(scaleFactor);
		sheet.getSettings().setPrintArea(firstCol, firstRow, lastCol, lastRow);
		sheet.getSettings().setPageOrder(PageOrder.DOWN_THEN_RIGHT); // 从上往下
		sheet.getSettings().setTopMargin(0.5);
		sheet.getSettings().setRightMargin(0.3 );
		sheet.getSettings().setBottomMargin(0.3);
		sheet.getSettings().setLeftMargin(0.3);
		sheet.getSettings().setHorizontalCentre(true);
		sheet.getSettings().setHeaderMargin(0.2);
		sheet.getSettings().setFooterMargin(0.1);
	}

	/**
	 * 创建Excel 文件
	 * 
	 * @param os
	 */
	public void create(OutputStream os) {
		try {
			work = Workbook.createWorkbook(os);
		} catch (IOException e) {
			logger.error(" Export Excel File", e.getMessage());
		}
	}

	/**
	 * 创建Excel 文件 并 创建名为sheetName 的表格
	 * 
	 * @param os
	 * @param sheetName
	 */
	public void create(OutputStream os, String sheetName) {
		try {
			work = Workbook.createWorkbook(os);
			sheet = work.createSheet(sheetName, 0);
			setSheetName(sheetName);
		} catch (IOException e) {
			logger.error(" Export Excel File", e.getMessage());
		}
	}

	/**
	 * 创建名为 sheetName 的表格
	 * 
	 * @param sheetName
	 */
	public void createSheet(String sheetName) {
		sheet = work.createSheet(sheetName, 0);
		setShowGridLines(false);
	}
	
	/**
	 * 创建名为 sheetName 的表格
	 * 
	 * @param sheetName
	 */
	public void createSheet(String sheetName, int index) {
		try {
			if(null == work) work = Workbook.createWorkbook(file);
			sheet = work.createSheet(sheetName, index);
			setShowGridLines(false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 是否保留表格网格 不设置则默认隐藏
	 * 
	 * @param isShow
	 */
	public void setShowGridLines(boolean isShow) {
		sheet.getSettings().setShowGridLines(isShow);
	}

	private static final Logger logger = LoggerFactory.getLogger(IExcel.class);

	/**
	 * 写入并关闭Excel 文件
	 */
	public void writeAndClose() {
		try {
			if (null != work) {
				work.write();
				work.close();
			}
		} catch (IOException e) {
			logger.error(" Export Excel File " + e.getMessage(), e);
		} catch (WriteException e) {
			logger.error(" Export Excel File " + e.getMessage(), e);
		}
	}

	/**
	 * 设置 单元格 内容 根据数值合并单元格
	 * 
	 * addCell(cellFormat(18), 1, 1, 15, -1, new String[]{"无销售报表", "6"});
	 * 
	 * @param wcf
	 *            单元格样式
	 * @param col
	 *            列单元格 坐标
	 * @param row
	 *            行单元格坐标
	 * @param columnWidth
	 *            列宽 -1 则不设置
	 * @param rowHeight
	 *            行高 -1 则不设置
	 * @param columnValues
	 *            单元格内容 String[]{"单元格内容", "合并列数"}
	 */
	public void addCell(WritableCellFormat wcf, int col, int row,
			int columnWidth, Integer rowHeight, String[]... columnValues) {
		// addCell(cellFormat(18), 1, 1, 15, -1, new String[]{"无销售报表", "6"});
		try {
			if (-1 != rowHeight) {
				sheet.setRowView(row, rowHeight);
			}
			int x = col;
			Integer merge = 0;
			String value = "";
			for (String[] columnValue : columnValues) {
				if (columnValue.length > 1) {
					value = columnValue[0];
					merge = Integer.valueOf(columnValue[1]);
				} else {
					value = columnValue[0];
				}
				sheet.addCell(new jxl.write.Label(x, row, value, wcf)); //
				sheet.mergeCells(x, row, (x + merge - 1), row); // 合并单元格
				x += (merge - 1);
				x++;
				merge = 0;
			}
			if (-1 != columnWidth) {
				for (int i = col; i <= x; i++)
					sheet.setColumnView(i, columnWidth);
			}
		} catch (RowsExceededException e) {
			logger.error(" Export Excel File", e.getMessage());
		} catch (WriteException e) {
			logger.error(" Export Excel File", e.getMessage());
		}
	}

	/**
	 * 设置 单元格 内容
	 */
	public void addCell(IExcelCell bean) {
		if (null == bean)
			return;
		try {
			if(bean.isSign()){
				Formula f = new Formula(bean.getColumn_x(), bean.getRow_y(), bean.getContent(), bean.getWcf());
				sheet.addCell(f);
			}else{
				sheet.addCell(new jxl.write.Label(bean.getColumn_x(), bean.getRow_y(), bean.getContent(), bean.getWcf())); //
				/*if (0 < bean.getMerge_x() || 0 < bean.getMerge_y()) {
					sheet.mergeCells(bean.getColumn_x(), bean.getRow_y(), (bean.getMerge_x() + bean.getColumn_x()), (bean.getMerge_y() + bean.getRow_y())); // 合并单元格
				}*/
			}
			if (0 < bean.getMerge_x() || 0 < bean.getMerge_y()) {
				sheet.mergeCells(bean.getColumn_x(), bean.getRow_y(), (bean.getMerge_x() + bean.getColumn_x()), (bean.getMerge_y() + bean.getRow_y())); // 合并单元格
			}
			setColumnView(bean);
			setRowView(bean);
		} catch (RowsExceededException e) {
			logger.error(" Export Excel File", e.getMessage());
			e.printStackTrace();
		} catch (WriteException e) {
			logger.error(" Export Excel File", e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * 设置行高
	 * 
	 * @param y
	 * @param merge_y
	 * @param height
	 */
	public void setRowView(int y, int merge_y, int height) {
		try {
			if (-1 != height) {
				for (int i = y; i <= y + merge_y; i++) {
					sheet.setRowView(i, height);
				}
			}
		} catch (RowsExceededException e) {
			logger.error(" Export Excel File", e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * 设置行高
	 * 
	 * @param bean
	 */
	public void setRowView(IExcelCell bean) {
		setRowView(bean.getRow_y(), bean.getMerge_y(), bean.getHeight());
	}

	/**
	 * 设置列宽
	 * 
	 * @param bean
	 */
	public void setColumnView(IExcelCell bean) {
		setColumnView(bean.getColumn_x(), bean.getMerge_x(), bean.getWidth());
	}

	/**
	 * 设置列宽
	 * 
	 * @param x
	 * @param merge_x
	 * @param width
	 */
	public void setColumnView(int x, int merge_x, int width) {
		try {
			if (-1 != width) {
				for (int i = x; i <= x + merge_x; i++) {
					sheet.setColumnView(i, width);
				}
			}
		} catch (Exception e) {
			logger.error(" Export Excel File", e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 插入图片到EXCEL
	 * @param excel
	 * @param picturePath 图片文件绝对路径
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public static void setImage(IExcel excel, String picturePath, int x, int y, int width, int height) {  
	    excel.getSheet().addImage(new WritableImage(x, y, width, height, new File(picturePath)));  
	}  
	
	/**
	 * 插入图片到EXCEL
	 * @param excel
	 * @param picturePath 图片文件绝对路径
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public static void setImage(IExcel excel, String picturePath, double x, int y, double width, double height) {  
	    excel.getSheet().addImage(new WritableImage(x, y, width, height, new File(picturePath)));  
	}  
	
	/**
	 * 插入图片到EXCEL
	 * @param excel
	 * @param picturePath 图片文件绝对路径
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public static void setImage(IExcel excel, String picturePath, double x, double y, double width, double height) {  
	    excel.getSheet().addImage(new WritableImage(x, y, width, height, new File(picturePath)));  
	} 
	
	/**
	 * 插入图片到EXCEL
	 * @param excel
	 * @param picturePath 图片文件绝对路径
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public static void setBlank(IExcel excel, int x, int y, int tox, int toy) {  
	      
	}  
	
	public void addCell(){
		for (IExcelCell bean : excelCells) {
			addCell(bean);
		}
	}
	
	/**
	 * 设置每个单元格宽度
	 * @param excel IExcel
	 * @param row
	 */
	public void setWidth(Integer[] cellWidths, int row){
		WritableCellFormat wcf = IExcelCellFormat.cellFormat(WritableFont.createFont("華康新儷粗黑"), 10, Colour.BLACK,
				Alignment.CENTRE, Colour.WHITE, Colour.GRAY_25, BorderLineStyle.NONE, VerticalAlignment.CENTRE);
		for(int i = 0, len = cellWidths.length; i < len; i++){
			addCell(new IExcelCell(wcf, i, row, "", cellWidths[i]));
		}
	}
	
	
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
	
	public static String toString(String value, String key){
		JSONObject json = JSON.parseObject(value);
		if(null != json) return json.getString(key);
		return "";
	}
	
	public static String toString(JSONObject json, String key){
		if(key.contains(".")){
			String[] keys = key.split("\\.");
			JSONObject _json = JSON.parseObject(json.getString(keys[0]));
			return _json.getString(keys[1]);
		}else return json.getString(key);
	}
	
	
	public static String toString(JSONObject json, String key, int index){
		if(key.contains(".")){
			String[] keys = key.split("\\.");
			JSONArray jsons = json.getJSONArray(keys[0]);
			JSONObject _json = (JSONObject) JSON.toJSON(jsons.get(index));
			return _json.getString(keys[1]);
		}
//		else{
//			List<Map<String, Object>> list = JSON.toJavaObject(json, List.class);
//			Map<String, Object> bean = list.get(index);
//			return bean.containsKey(key) ? bean.get(key).toString() : "";
//		}
		return "";
	}

}
