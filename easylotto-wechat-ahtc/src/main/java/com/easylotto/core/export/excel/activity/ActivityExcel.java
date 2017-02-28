//package com.easylotto.core.export.excel.activity;
///**
// * 
// */
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.apache.commons.lang.StringUtils;
//
//import com.easylotto.core.export.excel.IExcel;
//import com.easylotto.core.export.excel.IExcelCell;
//import com.easylotto.core.export.excel.IExcelCellFormat;
//import com.easylotto.core.util.DateTimeUtil;
//
//import jxl.format.Alignment;
//import jxl.format.BorderLineStyle;
//import jxl.format.Colour;
//import jxl.format.VerticalAlignment;
//import jxl.write.WritableCellFormat;
//
///**
// *
// * @author chenrixiang
// * @version 1.0
// * @create_date 2015年7月10日 上午9:32:02
// *
// */
//public class ActivityExcel extends IExcel{
//	private final static String EXCEL_FONT_XXMT = "華康新儷粗黑";
//
//	private final static String SHEET_NAME = "AHB";
////												   1             5              10              15                  20                  25         
//	 private final static Integer[] CELL_WIDTHS = {20, 25, 25, 25, 25, 25, 25};//TODO
//	
//	/**
//	 * 设置 标题栏
//	 * @param excel
//	 * @param row
//	 */
//	public static void setTitleBar(IExcel excel, int row){
//		WritableCellFormat wcf = IExcelCellFormat.cellFormat(11, Colour.BLACK, Alignment.CENTRE, Colour.WHITE, Colour.BLACK, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, VerticalAlignment.CENTRE);
//		
//		excel.add(new IExcelCell(wcf, 0, row, "序号", -1, 460));
//		
//		excel.add(new IExcelCell(wcf, 1, row, "姓名"));
//		
//		excel.add(new IExcelCell(wcf, 2, row, "手机"));
//		
//		excel.add(new IExcelCell(wcf, 3, row, "报名时间"));
//	}
//	
//	/**
//	 * 设置 标题栏
//	 * @param excel
//	 * @param row
//	 */
//	public static void setTitleBar1(IExcel excel, int row){
//		WritableCellFormat wcf = IExcelCellFormat.cellFormat(11, Colour.BLACK, Alignment.CENTRE, Colour.WHITE, Colour.BLACK, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, VerticalAlignment.CENTRE);
//		
//		excel.add(new IExcelCell(wcf, 0, row, "序号", -1, 460));
//		excel.add(new IExcelCell(wcf, 1, row, "用户名"));
//		excel.add(new IExcelCell(wcf, 2, row, "手机号"));
//		excel.add(new IExcelCell(wcf, 3, row, "机号"));
//		excel.add(new IExcelCell(wcf, 4, row, "销售期"));
//		excel.add(new IExcelCell(wcf, 5, row, "流水号"));
//		excel.add(new IExcelCell(wcf, 6, row, "报名时间"));
//	}
//	
//	/**
//	 * 设置 标题栏
//	 * @param excel
//	 * @param row
//	 */
//	public static void setTitleBar2(IExcel excel, int row){
//		WritableCellFormat wcf = IExcelCellFormat.cellFormat(11, Colour.BLACK, Alignment.CENTRE, Colour.WHITE, Colour.BLACK, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, VerticalAlignment.CENTRE);
//		
//		excel.add(new IExcelCell(wcf, 0, row, "序号", -1, 460));
//		excel.add(new IExcelCell(wcf, 1, row, "用户名"));
//		excel.add(new IExcelCell(wcf, 2, row, "手机号"));
//		excel.add(new IExcelCell(wcf, 3, row, "物流码"));
//		excel.add(new IExcelCell(wcf, 4, row, "报名时间"));
//		
//	}
//	
//	public static String toString(String obj){
//		if(StringUtils.isNotBlank(obj)) return obj;
//		return "";
//	}
//	
//	/**
//	 *  设置 赛事
//	 * @param excel
//	 * @param row
//	 */
//	public static void setRowByMatch(IExcel excel, int row, List<ActivityLog> list){
//		WritableCellFormat wcf = IExcelCellFormat.cellFormat(11, Colour.BLACK, Alignment.CENTRE, Colour.WHITE, Colour.BLACK, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, VerticalAlignment.CENTRE);
//		int i = 0;
//		for(ActivityLog bean : list){
//			excel.add(new IExcelCell(wcf, 0, row, ""+ (++i), -1, 460));
//			
//			excel.add(new IExcelCell(wcf, 1, row, bean.getVc_name()));
//			
//			excel.add(new IExcelCell(wcf, 2, row, bean.getVc_phone()));
//			
//			excel.add(new IExcelCell(wcf, 3, row, DateTimeUtil.toString(DateTimeUtil.YYYY_MM_DD_HH_MM_SS, bean.getDt_entry_time())));
//			row ++;
//		}
//	}
//	
//	/**
//	 *  设置 赛事
//	 * @param excel
//	 * @param row
//	 */
//	public static void setRowByMatch1(IExcel excel, int row, List<ActivityLog> list){
//		WritableCellFormat wcf = IExcelCellFormat.cellFormat(11, Colour.BLACK, Alignment.CENTRE, Colour.WHITE, Colour.BLACK, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, VerticalAlignment.CENTRE);
//		int i = 0;
//		for(ActivityLog bean : list){
//			excel.add(new IExcelCell(wcf, 0, row, ""+ (++i), -1, 460));
//			
//			excel.add(new IExcelCell(wcf, 1, row, bean.getVc_name()));
//			excel.add(new IExcelCell(wcf, 2, row, bean.getVc_phone()));
//			excel.add(new IExcelCell(wcf, 3, row, bean.getVc_machine_number()));
//			excel.add(new IExcelCell(wcf, 4, row, bean.getVc_sales_period()));
//			excel.add(new IExcelCell(wcf, 5, row, bean.getVc_serial_number()));
//			excel.add(new IExcelCell(wcf, 6, row, DateTimeUtil.toString(DateTimeUtil.YYYY_MM_DD_HH_MM_SS, bean.getDt_entry_time())));
//			row ++;
//		}
//	}
//	
//	/**
//	 *  设置 赛事
//	 * @param excel
//	 * @param row
//	 */
//	public static void setRowByMatch2(IExcel excel, int row, List<ActivityLog> list){
//		WritableCellFormat wcf = IExcelCellFormat.cellFormat(11, Colour.BLACK, Alignment.CENTRE, Colour.WHITE, Colour.BLACK, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, VerticalAlignment.CENTRE);
//		int i = 0;
//		for(ActivityLog bean : list){
//			excel.add(new IExcelCell(wcf, 0, row, ""+ (++i), -1, 460));
//			
//			excel.add(new IExcelCell(wcf, 1, row, bean.getVc_name()));
//			
//			excel.add(new IExcelCell(wcf, 2, row, bean.getVc_phone()));
//			excel.add(new IExcelCell(wcf, 3, row, bean.getVc_machine_number()));
//			
//			excel.add(new IExcelCell(wcf, 4, row, DateTimeUtil.toString(DateTimeUtil.YYYY_MM_DD_HH_MM_SS, bean.getDt_entry_time())));
//			row ++;
//		}
//	}
//	
//	public static void export(IExcel excel, List<ActivityLog> list){
//		try {
//			// 创建工作薄 // 创建第一个工作表
//			excel.createSheet("走近双色球 免费游北京", 0);
//			excel.setExcelCells(new ArrayList<IExcelCell>(0));
//			
//			int row = 0; 
//			excel.setWidth(CELL_WIDTHS, row);
//			setTitleBar(excel, row);
//			row +=1;
//			setRowByMatch(excel, row, list);
//			excel.addCell();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	
//	public static void export1(IExcel excel, List<ActivityLog> list){
//		try {
//			// 创建工作薄 // 创建第一个工作表
//			excel.createSheet("快3加奖", 0);
//			excel.setExcelCells(new ArrayList<IExcelCell>(0));
//			
//			int row = 0; 
//			excel.setWidth(CELL_WIDTHS, row);
//			setTitleBar1(excel, row);
//			row +=1;
//			setRowByMatch1(excel, row, list);
//			excel.addCell();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public static void export2(IExcel excel, List<ActivityLog> list){
//		try {
//			// 创建工作薄 // 创建第一个工作表
//			excel.createSheet("红蓝宝石", 0);
//			excel.setExcelCells(new ArrayList<IExcelCell>(0));
//			
//			int row = 0; 
//			excel.setWidth(CELL_WIDTHS, row);
//			setTitleBar2(excel, row);
//			row +=1;
//			setRowByMatch2(excel, row, list);
//			excel.addCell();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//
//}
