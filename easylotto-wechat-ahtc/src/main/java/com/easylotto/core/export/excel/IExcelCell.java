package com.easylotto.core.export.excel;

import org.apache.commons.lang.StringUtils;

import jxl.write.WritableCellFormat;

public class IExcelCell {

	private jxl.write.WritableCellFormat wcf;
	private int column_x;// 列
	private int row_y;// 行
	private int merge_x;// 合并列
	private int merge_y;// 合并行

	private String content;
	private int width;
	private int height;
	private boolean sign;//是否公式

	public IExcelCell() {
	}
	
	public IExcelCell(WritableCellFormat wcf, int column_x, int row_y,
			int merge_x, int merge_y, String content, int width, int height, boolean sign) {
		this.wcf = wcf;
		this.column_x = column_x;
		this.row_y = row_y;
		this.merge_x = merge_x;
		this.merge_y = merge_y;
		this.content = StringUtils.isNotBlank(content)? content : "";
		this.width = width;
		this.height = height;
		this.sign = sign;
	}
	
	public IExcelCell(WritableCellFormat wcf, int column_x, int row_y,
			int merge_x, int merge_y, String content, boolean sign) {
		this.wcf = wcf;
		this.column_x = column_x;
		this.row_y = row_y;
		this.merge_x = merge_x;
		this.merge_y = merge_y;
		this.content = StringUtils.isNotBlank(content)? content : "";
		this.width = -1;
		this.height = -1;
		this.sign = sign;
	}
	
	public IExcelCell(WritableCellFormat wcf, int column_x, int row_y, String content, int width, int height, boolean sign) {
		this.wcf = wcf;
		this.column_x = column_x;
		this.row_y = row_y;
		this.merge_x = 0;
		this.merge_y = 0;
		this.content = StringUtils.isNotBlank(content)? content : "";
		this.width = width;
		this.height = height;
		this.sign = sign;
	}
	
	public IExcelCell(WritableCellFormat wcf, int column_x, int row_y, String content, boolean sign) {
		this.wcf = wcf;
		this.column_x = column_x;
		this.row_y = row_y;
		this.merge_x = 0;
		this.merge_y = 0;
		this.content = StringUtils.isNotBlank(content)? content : "";
		this.width = -1;
		this.height = -1;
		this.sign = sign;
	}
	public IExcelCell(WritableCellFormat wcf, int column_x, int row_y, String content, int width, boolean sign) {
		this.wcf = wcf;
		this.column_x = column_x;
		this.row_y = row_y;
		this.merge_x = 0;
		this.merge_y = 0;
		this.content = StringUtils.isNotBlank(content)? content : "";
		this.width = width;
		this.height = -1;
		this.sign = sign;
	}

	public IExcelCell(WritableCellFormat wcf, int column_x, int row_y,
			int merge_x, int merge_y, String content, int width, int height) {
		this.wcf = wcf;
		this.column_x = column_x;
		this.row_y = row_y;
		this.merge_x = merge_x;
		this.merge_y = merge_y;
		this.content = StringUtils.isNotBlank(content)? content : "";
		this.width = width;
		this.height = height;
		this.sign = false;
	}
	
	public IExcelCell(WritableCellFormat wcf, int column_x, int row_y,
			int merge_x, int merge_y, String content) {
		this.wcf = wcf;
		this.column_x = column_x;
		this.row_y = row_y;
		this.merge_x = merge_x;
		this.merge_y = merge_y;
		this.content = StringUtils.isNotBlank(content)? content : "";
		this.width = -1;
		this.height = -1;
		this.sign = false;
	}
	
	public IExcelCell(WritableCellFormat wcf, int column_x, int row_y, String content, int width, int height) {
		this.wcf = wcf;
		this.column_x = column_x;
		this.row_y = row_y;
		this.merge_x = 0;
		this.merge_y = 0;
		this.content = StringUtils.isNotBlank(content)? content : "";
		this.width = width;
		this.height = height;
		this.sign = false;
	}
	
	public IExcelCell(WritableCellFormat wcf, int column_x, int row_y, int width, int height, Object content) {
		this.wcf = wcf;
		this.column_x = column_x;
		this.row_y = row_y;
		this.merge_x = 0;
		this.merge_y = 0;
		this.content = (null != content) ? content.toString() : "";
		this.width = width;
		this.height = height;
		this.sign = false;
	}
	
	public IExcelCell(WritableCellFormat wcf, int column_x, int row_y, String content, Long width, Long height) {
		this.wcf = wcf;
		this.column_x = column_x;
		this.row_y = row_y;
		this.merge_x = 0;
		this.merge_y = 0;
		this.content = StringUtils.isNotBlank(content)? content : "";
		this.width = null == width ? -1 : width.intValue();
		this.height = null == height ? -1 : height.intValue();
		this.sign = false;
	}
	
	public IExcelCell(WritableCellFormat wcf, int column_x, int row_y, String content) {
		this.wcf = wcf;
		this.column_x = column_x;
		this.row_y = row_y;
		this.merge_x = 0;
		this.merge_y = 0;
		this.content = StringUtils.isNotBlank(content)? content : "";
		this.width = -1;
		this.height = -1;
		this.sign = false;
	}
	
	public IExcelCell(Object content, WritableCellFormat wcf, int column_x, int row_y) {
		this.wcf = wcf;
		this.column_x = column_x;
		this.row_y = row_y;
		this.merge_x = 0;
		this.merge_y = 0;
		this.content = (null != content)? content.toString() : "";
		this.width = -1;
		this.height = -1;
		this.sign = false;
	}
	
	public IExcelCell(WritableCellFormat wcf, int column_x, int row_y, Double content) {
		this.wcf = wcf;
		this.column_x = column_x;
		this.row_y = row_y;
		this.merge_x = 0;
		this.merge_y = 0;
		if(null != content){
			if(content % 1.0 == 0.0){
				int _content = content.intValue();
				this.content = _content + "";
			}else{
				this.content = content+"";
			}
		}else{
			this.content = "";
		}
		
		this.width = -1;
		this.height = -1;
		this.sign = false;
	}
	
	public IExcelCell(WritableCellFormat wcf, int column_x, int row_y, int merge_x, int merge_y, Double content) {
		this.wcf = wcf;
		this.column_x = column_x;
		this.row_y = row_y;
		this.merge_x = merge_x;
		this.merge_y = merge_y;
		if(null != content){
			if(content % 1.0 == 0.0){
				int _content = content.intValue();
				this.content = _content + "";
			}else{
				this.content = content+"";
			}
		}else{
			this.content = "";
		}
		
		this.width = -1;
		this.height = -1;
		this.sign = false;
	}
	
	public IExcelCell(WritableCellFormat wcf, int column_x, int row_y, String content, int width) {
		this.wcf = wcf;
		this.column_x = column_x;
		this.row_y = row_y;
		this.merge_x = 0;
		this.merge_y = 0;
		this.content = StringUtils.isNotBlank(content)? content : "";
		this.width = width;
		this.height = -1;
		this.sign = false;
	}

	public jxl.write.WritableCellFormat getWcf() {
		return wcf;
	}

	public void setWcf(jxl.write.WritableCellFormat wcf) {
		this.wcf = wcf;
	}

	public int getMerge_x() {
		return merge_x;
	}

	public void setMerge_x(int merge_x) {
		this.merge_x = merge_x;
	}

	public int getMerge_y() {
		return merge_y;
	}

	public void setMerge_y(int merge_y) {
		this.merge_y = merge_y;
	}

	public String getContent() {
		if(StringUtils.isNotBlank(content) && "null".equals(content)) return "";
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getColumn_x() {
		return column_x;
	}

	public void setColumn_x(int column_x) {
		this.column_x = column_x;
	}

	public int getRow_y() {
		return row_y;
	}

	public void setRow_y(int row_y) {
		this.row_y = row_y;
	}

	public boolean isSign() {
		return sign;
	}

	public void setSign(boolean sign) {
		this.sign = sign;
	}
	
	

}
