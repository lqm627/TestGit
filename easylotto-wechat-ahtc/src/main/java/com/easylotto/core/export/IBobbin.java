package com.easylotto.core.export;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.format.PageOrder;
import jxl.format.PaperSize;


/**
 * 盘纸文件
 *
 * @author chenrixiang
 * @version 1.0
 * @create_date 2015年7月10日 上午9:20:06
 *
 */
public class IBobbin{
	
	private String type;
	
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	
	/**
	 * 盘纸格式
	 */
	public void format(){}
	
	/**
	 * 开盘工具
	 */
	public void tools(){}
	
	/**
	 * 首名入球
	 */
	public void firstgoal(){}
	
	/** bo作实 首名列印 **/
	public void confirmfirstgoal(){}
	
	/**
	 * Excel 转为 Pdf 文件
	 */
	public void excelToPdf(){}
	
	/**
	 * Excel 文件
	 */
	public void excel(){}
	
	/**
	 * 导出文件
	 */
	public void export(){ }
	
	/**
	 * 保存文件
	 */
	public void storage(){}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	/**
	 * 打印设置  A4 从上往下 
	 * @param firstCol
	 * @param firstRow
	 * @param lastCol
	 * @param lastRow
	 * @param scaleFactor 打印调整比例
	 */
	public void setSettings(jxl.write.WritableSheet sheet, int firstCol, int firstRow, int lastCol, int lastRow, int scaleFactor){
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
	
	
}
