package com.easylotto.core.util;
/**
 * 
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jxl.format.Alignment;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.WritableCellFormat;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.easylotto.commons.util.DateTimeUtil;
import com.easylotto.core.dao.ActivityDataDao;
import com.easylotto.core.entity.ActivityLog;
import com.easylotto.core.export.excel.IExcel;
import com.easylotto.core.export.excel.IExcelCell;
import com.easylotto.core.export.excel.IExcelCellFormat;
import com.wechat.webapi.web.config.SpringContext;


/**
 *
 * @author chenrixiang
 * @version 1.0
 * @create_date 2015年7月10日 上午9:32:02
 *
 */
public class ActivityExcel extends IExcel{
	private final static String EXCEL_FONT_XXMT = "華康新儷粗黑";

	private final static String SHEET_NAME = "AHB";
//												   1             5              10              15                  20                  25         
	 private final static Integer[] CELL_WIDTHS = {20, 25, 25, 25, 25, 25, 25};//TODO
		private final static int MAX_ROWS = 60000;
		
	
	/**
	 * 设置 标题栏
	 * @param excel
	 * @param row
	 */
	public static void setTitleBar(IExcel excel, int row){
		WritableCellFormat wcf = IExcelCellFormat.cellFormat(11, Colour.BLACK, Alignment.CENTRE, Colour.WHITE, Colour.BLACK, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, VerticalAlignment.CENTRE);
		
		excel.add(new IExcelCell(wcf, 0, row, "序号", -1, 460));
		
		excel.add(new IExcelCell(wcf, 1, row, "姓名"));
		
		excel.add(new IExcelCell(wcf, 2, row, "手机"));
		
		excel.add(new IExcelCell(wcf, 3, row, "报名时间"));
	}
	
	/**
	 * 设置 标题栏
	 * @param excel
	 * @param row
	 */
	public static void setTitleBar1(IExcel excel, int row){
		WritableCellFormat wcf = IExcelCellFormat.cellFormat(11, Colour.BLACK, Alignment.CENTRE, Colour.WHITE, Colour.BLACK, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, VerticalAlignment.CENTRE);
		
		excel.add(new IExcelCell(wcf, 0, row, "序号", -1, 460));
		excel.add(new IExcelCell(wcf, 1, row, "用户名"));
		excel.add(new IExcelCell(wcf, 2, row, "手机号"));
		excel.add(new IExcelCell(wcf, 3, row, "机号"));
		excel.add(new IExcelCell(wcf, 4, row, "销售期"));
		excel.add(new IExcelCell(wcf, 5, row, "流水号"));
		excel.add(new IExcelCell(wcf, 6, row, "报名时间"));
	}
	
	
	/**
	 * 设置 标题栏
	 * @param excel
	 * @param row
	 */
	public static void setTitleBar6(IExcel excel, int row){
		WritableCellFormat wcf = IExcelCellFormat.cellFormat(11, Colour.BLACK, Alignment.CENTRE, Colour.WHITE, Colour.BLACK, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, VerticalAlignment.CENTRE);
		
		excel.add(new IExcelCell(wcf, 0, row, "序号", -1, 460));
		excel.add(new IExcelCell(wcf, 1, row, "用户名"));
		excel.add(new IExcelCell(wcf, 2, row, "手机号"));
	}
	
	/**
	 * 设置 标题栏
	 * @param excel
	 * @param row
	 */
	public static void setTitleBar2(IExcel excel, int row){
		WritableCellFormat wcf = IExcelCellFormat.cellFormat(11, Colour.BLACK, Alignment.CENTRE, Colour.WHITE, Colour.BLACK, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, VerticalAlignment.CENTRE);
		
		excel.add(new IExcelCell(wcf, 0, row, "序号", -1, 460));
		excel.add(new IExcelCell(wcf, 1, row, "用户名"));
		excel.add(new IExcelCell(wcf, 2, row, "手机号"));
		excel.add(new IExcelCell(wcf, 3, row, "物流码"));
		excel.add(new IExcelCell(wcf, 4, row, "报名时间"));
		
	}
	
	
	/**
	 * 设置 标题栏
	 * @param excel
	 * @param row
	 */
	public static void setTitleBar29(IExcel excel, int row){
		WritableCellFormat wcf = IExcelCellFormat.cellFormat(11, Colour.BLACK, Alignment.CENTRE, Colour.WHITE, Colour.BLACK, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, VerticalAlignment.CENTRE);
		
		excel.add(new IExcelCell(wcf, 0, row, "序号", -1, 460));
		excel.add(new IExcelCell(wcf, 1, row, "作品号"));
		excel.add(new IExcelCell(wcf, 2, row, "投票时间"));
	}
	
	/**
	 * 设置 标题栏
	 * @param excel
	 * @param row
	 */
	public static void setTitleBar30(IExcel excel, int row){
		WritableCellFormat wcf = IExcelCellFormat.cellFormat(11, Colour.BLACK, Alignment.CENTRE, Colour.WHITE, Colour.BLACK, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, VerticalAlignment.CENTRE);
		
		excel.add(new IExcelCell(wcf, 0, row, "序号", -1, 460));
		excel.add(new IExcelCell(wcf, 1, row, "手机号"));
		excel.add(new IExcelCell(wcf, 2, row, "姓名"));
		excel.add(new IExcelCell(wcf, 3, row, "上联"));
		excel.add(new IExcelCell(wcf, 4, row, "下联"));
		excel.add(new IExcelCell(wcf, 5, row, "横批"));
		excel.add(new IExcelCell(wcf, 6, row, "录入时间"));
	}
	
	/**
	 * 设置 标题栏
	 * @param excel
	 * @param row
	 */
	public static void setTitleBar31(IExcel excel, int row){
		WritableCellFormat wcf = IExcelCellFormat.cellFormat(11, Colour.BLACK, Alignment.CENTRE, Colour.WHITE, Colour.BLACK, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, VerticalAlignment.CENTRE);
		
		excel.add(new IExcelCell(wcf, 0, row, "序号", -1, 460));
		excel.add(new IExcelCell(wcf, 1, row, "手机号"));
		excel.add(new IExcelCell(wcf, 2, row, "姓名"));
		excel.add(new IExcelCell(wcf, 3, row, "身份证"));
		excel.add(new IExcelCell(wcf, 4, row, "图片上传地址"));
		excel.add(new IExcelCell(wcf, 5, row, "彩票序列号"));
		excel.add(new IExcelCell(wcf, 6, row, "录入时间"));
	}
	
	public static String toString(String obj){
		if(StringUtils.isNotBlank(obj)) return obj;
		return "";
	}
	
	/**
	 *  设置 赛事
	 * @param excel
	 * @param row
	 */
	public static void setRowByMatch(IExcel excel, int row, List<ActivityLog> list){
		WritableCellFormat wcf = IExcelCellFormat.cellFormat(11, Colour.BLACK, Alignment.CENTRE, Colour.WHITE, Colour.BLACK, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, VerticalAlignment.CENTRE);
		int i = 0;
		for(ActivityLog bean : list){
			excel.add(new IExcelCell(wcf, 0, row, ""+ (++i), -1, 460));
			
			excel.add(new IExcelCell(wcf, 1, row, bean.getVc_name()));
			
			excel.add(new IExcelCell(wcf, 2, row, bean.getVc_phone()));
			
			excel.add(new IExcelCell(wcf, 3, row, DateTimeUtil.toString(DateTimeUtil.YYYY_MM_DD_HH_MM_SS, bean.getDt_entry_time())));
			row ++;
		}
	}
	
	/**
	 *  设置 赛事
	 * @param excel
	 * @param row
	 */
	public static void setRowByMatch1(IExcel excel, int row, List<ActivityLog> list){
		WritableCellFormat wcf = IExcelCellFormat.cellFormat(11, Colour.BLACK, Alignment.CENTRE, Colour.WHITE, Colour.BLACK, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, VerticalAlignment.CENTRE);
		int i = 0;
		for(ActivityLog bean : list){
			excel.add(new IExcelCell(wcf, 0, row, ""+ (++i), -1, 460));
			
			excel.add(new IExcelCell(wcf, 1, row, bean.getVc_name()));
			excel.add(new IExcelCell(wcf, 2, row, bean.getVc_phone()));
//			excel.add(new IExcelCell(wcf, 3, row, bean.getVc_machine_number()));
//			excel.add(new IExcelCell(wcf, 4, row, bean.getVc_sales_period()));
//			excel.add(new IExcelCell(wcf, 5, row, bean.getVc_serial_number()));
			excel.add(new IExcelCell(wcf, 6, row, DateTimeUtil.toString(DateTimeUtil.YYYY_MM_DD_HH_MM_SS, bean.getDt_entry_time())));
			row ++;
		}
	}
	
	
	/**
	 *  设置 赛事
	 * @param excel
	 * @param row
	 */
	public static void setRowByMatch6(IExcel excel, int row, List<ActivityLog> list){
		WritableCellFormat wcf = IExcelCellFormat.cellFormat(11, Colour.BLACK, Alignment.CENTRE, Colour.WHITE, Colour.BLACK, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, VerticalAlignment.CENTRE);
		int i = 0;
		for(ActivityLog bean : list){
			excel.add(new IExcelCell(wcf, 0, row, ""+ (++i), -1, 460));
			excel.add(new IExcelCell(wcf, 1, row, bean.getVc_name()));
			excel.add(new IExcelCell(wcf, 2, row, bean.getVc_phone()));
			row ++;
		}
	}
	
	/**
	 *  设置 赛事
	 * @param excel
	 * @param row
	 */
	public static void setRowByMatch2(IExcel excel, int row, List<ActivityLog> list){
		WritableCellFormat wcf = IExcelCellFormat.cellFormat(11, Colour.BLACK, Alignment.CENTRE, Colour.WHITE, Colour.BLACK, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, VerticalAlignment.CENTRE);
		int i = 0;
		for(ActivityLog bean : list){
			excel.add(new IExcelCell(wcf, 0, row, ""+ (++i), -1, 460));
			
			excel.add(new IExcelCell(wcf, 1, row, bean.getVc_name()));
			
			excel.add(new IExcelCell(wcf, 2, row, bean.getVc_phone()));
//			excel.add(new IExcelCell(wcf, 3, row, bean.getVc_machine_number()));
			
			excel.add(new IExcelCell(wcf, 4, row, DateTimeUtil.toString(DateTimeUtil.YYYY_MM_DD_HH_MM_SS, bean.getDt_entry_time())));
			row ++;
		}
	}
	
	/**
	 *  设置 赛事
	 * @param excel
	 * @param row
	 */
	public static void setRowByMatch29(IExcel excel, int row, List<ActivityLog> list){
		WritableCellFormat wcf = IExcelCellFormat.cellFormat(11, Colour.BLACK, Alignment.CENTRE, Colour.WHITE, Colour.BLACK, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, VerticalAlignment.CENTRE);
		int i = 0;
		for(ActivityLog bean : list){
			excel.add(new IExcelCell(wcf, 0, row, ""+ (++i), -1, 460));
			excel.add(new IExcelCell(wcf, 1, row, bean.getInt_vote_cumulative_id()+""));
			excel.add(new IExcelCell(wcf, 2, row, DateTimeUtil.toString(DateTimeUtil.YYYY_MM_DD_HH_MM_SS, bean.getDt_entry_time())));
			row ++;
		}
	}
	
	/**
	 *  设置 赛事
	 * @param excel
	 * @param row
	 */
	public static void setRowByMatch30(IExcel excel, int row, List<ActivityLog> list){
		WritableCellFormat wcf = IExcelCellFormat.cellFormat(11, Colour.BLACK, Alignment.CENTRE, Colour.WHITE, Colour.BLACK, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, VerticalAlignment.CENTRE);
		int i = 0;
		for(ActivityLog bean : list){
			excel.add(new IExcelCell(wcf, 0, row, ""+ (++i), -1, 460));			
			excel.add(new IExcelCell(wcf, 1, row, bean.getVc_phone()));	
			excel.add(new IExcelCell(wcf, 2, row, bean.getVc_name()));
			excel.add(new IExcelCell(wcf, 3, row, bean.getVc_upper_couplet()));
			excel.add(new IExcelCell(wcf, 4, row, bean.getVc_lower_couplet()));
			excel.add(new IExcelCell(wcf, 5, row, bean.getVc_couplet_ryhming()));			
			excel.add(new IExcelCell(wcf, 6, row, DateTimeUtil.toString(DateTimeUtil.YYYY_MM_DD_HH_MM_SS, bean.getDt_entry_time())));
			row ++;
		}
	}
	
	/**
	 *  设置 赛事
	 * @param excel
	 * @param row
	 */
	public static void setRowByMatch31(IExcel excel, int row, List<ActivityLog> list){
		WritableCellFormat wcf = IExcelCellFormat.cellFormat(11, Colour.BLACK, Alignment.CENTRE, Colour.WHITE, Colour.BLACK, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, VerticalAlignment.CENTRE);
		int i = 0;
		for(ActivityLog bean : list){
			excel.add(new IExcelCell(wcf, 0, row, ""+ (++i), -1, 460));			
			excel.add(new IExcelCell(wcf, 1, row, bean.getVc_phone()));	
			excel.add(new IExcelCell(wcf, 2, row, bean.getVc_name()));
			excel.add(new IExcelCell(wcf, 3, row, bean.getVc_card_no()));
			excel.add(new IExcelCell(wcf, 4, row, bean.getVc_pic_url()));
			excel.add(new IExcelCell(wcf, 5, row, bean.getVc_serial_number()));			
			excel.add(new IExcelCell(wcf, 6, row, DateTimeUtil.toString(DateTimeUtil.YYYY_MM_DD_HH_MM_SS, bean.getDt_entry_time())));
			row ++;
		}
	}
	
	public static void export(IExcel excel, List<ActivityLog> list){
		try {
			// 创建工作薄 // 创建第一个工作表
			excel.createSheet("走近双色球 免费游北京", 0);
			excel.setExcelCells(new ArrayList<IExcelCell>(0));
			
			int row = 0; 
			excel.setWidth(CELL_WIDTHS, row);
			setTitleBar(excel, row);
			row +=1;
			setRowByMatch(excel, row, list);
			excel.addCell();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void export1(IExcel excel, List<ActivityLog> list){
		try {
			// 创建工作薄 // 创建第一个工作表
			excel.createSheet("快3加奖", 0);
			excel.setExcelCells(new ArrayList<IExcelCell>(0));
			
			int row = 0; 
			excel.setWidth(CELL_WIDTHS, row);
			setTitleBar1(excel, row);
			row +=1;
			setRowByMatch1(excel, row, list);
			excel.addCell();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void export2(IExcel excel, List<ActivityLog> list){
		try {
			// 创建工作薄 // 创建第一个工作表
			excel.createSheet("红蓝宝石", 0);
			excel.setExcelCells(new ArrayList<IExcelCell>(0));
			
			int row = 0; 
			excel.setWidth(CELL_WIDTHS, row);
			setTitleBar2(excel, row);
			row +=1;
			setRowByMatch2(excel, row, list);
			excel.addCell();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	
	
	public static void export6(IExcel excel, List<ActivityLog> list){
		try {
			// 创建工作薄 // 创建第一个工作表
			excel.createSheet("答题北京看开奖", 0);
			excel.setExcelCells(new ArrayList<IExcelCell>(0));
			
			int row = 0; 
			excel.setWidth(CELL_WIDTHS, row);
			setTitleBar6(excel, row);
			row +=1;
			setRowByMatch6(excel, row, list);
			excel.addCell();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void export29(IExcel excel, List<ActivityLog> list){
		try {
			ActivityDataDao activityDataDao = SpringContext.getBean(ActivityDataDao.class);
			// 创建工作薄 // 创建第一个工作表
			int number =(int) Math.ceil(list.size()/MAX_ROWS)+1;
			int seetNum=0;
			int startNum=0;
			for(int i=1;i<=number;i++){
				 List<ActivityLog> list1 = activityDataDao.findBy29(startNum);
				excel.createSheet("作品投票"+seetNum, seetNum);
				excel.setExcelCells(new ArrayList<IExcelCell>(0));
				
				int row = 0; 
				Integer[] CELL_WIDTHS = {20, 25, 25};
				excel.setWidth(CELL_WIDTHS, row);
				setTitleBar29(excel, row);
				row +=1;
				setRowByMatch29(excel, row, list1);
				excel.addCell();
				startNum=startNum+MAX_ROWS;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void export30(IExcel excel, List<ActivityLog> list){
		try {
			// 创建工作薄 // 创建第一个工作表
			excel.createSheet("体彩安徽春联活动", 0);
			excel.setExcelCells(new ArrayList<IExcelCell>(0));
			
			int row = 0; 
			excel.setWidth(CELL_WIDTHS, row);
			setTitleBar30(excel, row);
			row +=1;
			setRowByMatch30(excel, row, list);
			excel.addCell();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void export31(IExcel excel, List<ActivityLog> list){
		try {
			// 创建工作薄 // 创建第一个工作表
			excel.createSheet("体彩晒票有奖活动", 0);
			excel.setExcelCells(new ArrayList<IExcelCell>(0));
			
			int row = 0; 
			excel.setWidth(CELL_WIDTHS, row);
			setTitleBar31(excel, row);
			row +=1;
			setRowByMatch31(excel, row, list);
			excel.addCell();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
