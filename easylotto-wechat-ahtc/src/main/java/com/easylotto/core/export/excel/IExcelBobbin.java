package com.easylotto.core.export.excel;

import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import jxl.format.Alignment;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.WritableCellFormat;

import com.easylotto.core.export.IBobbin;
/**
 * 盘纸格式
 * @author chenrixiang
 * @version 1.0
 * @create_date 2015年6月18日 下午2:39:55
 *
 */
public class IExcelBobbin extends IBobbin{

		private final static String EXCEL_FONT_XXMT = "華康新儷粗黑";

		private final static String SHEET_NAME = "AHB";
//													   1             5              10              15                  20                  25         
		 private final static Integer[] CELL_WIDTHS = {20, 8, 9, 15, 8, 9, 9, 9, 2, 7, 9, 9, 5, 10, 12, 10, 20, 20, 10, 10, 10, 10, 10, 10, 10, 10};//TODO
		
		/**
		 * 创建 IExcel File
		 * @param excel new IExcel
		 * @param file new File
		 */
		public static void create(IExcel excel, File file){
			excel.create(file, SHEET_NAME);
		}
		
		/**
		 * 创建 IExcel File
		 * @param excel new IExcel
		 * @param os OutputStream
		 */
		public static void create(IExcel excel, OutputStream os){
			excel.create(os, SHEET_NAME);
		}
		
		/**
		 * 设置每个单元格宽度
		 * @param excel IExcel
		 * @param row
		 */
		public static void setWidth(IExcel excel, int row){
			WritableCellFormat wcf = IExcelCellFormat.cellFormat();
			for(int i = 0, len = CELL_WIDTHS.length; i < len; i++){
				excel.add(new IExcelCell(wcf, i, row, "", CELL_WIDTHS[i]));
			}
		}
		
		/**
		 * 设置 标题栏
		 * @param excel
		 * @param row
		 */
		public static void setTitleBar(IExcel excel, int row){
			WritableCellFormat wcf = IExcelCellFormat.cellFormat(11, Colour.BLACK, Alignment.CENTRE, Colour.WHITE, Colour.BLACK, BorderLineStyle.MEDIUM, BorderLineStyle.THIN, BorderLineStyle.MEDIUM, BorderLineStyle.MEDIUM, VerticalAlignment.CENTRE);
			
			excel.add(new IExcelCell(wcf, 0, row, 0, 1, "開賽            時間", -1, 460));
			
			wcf = IExcelCellFormat.cellFormat(11, Colour.BLACK, Alignment.CENTRE, Colour.WHITE, Colour.BLACK, BorderLineStyle.MEDIUM, BorderLineStyle.THIN, BorderLineStyle.MEDIUM, BorderLineStyle.THIN, VerticalAlignment.CENTRE);
			excel.add(new IExcelCell(wcf, 1, row, 0, 1, "賽事"));
			
			excel.add(new IExcelCell(wcf, 2, row, 0, 1, "排名"));
			
			wcf = IExcelCellFormat.cellFormat(11, Colour.BLACK, Alignment.CENTRE, Colour.WHITE, Colour.BLACK, Colour.GRAY_25, Colour.BLACK, Colour.BLACK, BorderLineStyle.MEDIUM, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, VerticalAlignment.CENTRE);
			excel.add(new IExcelCell(wcf, 3, row, "主隊"));
			wcf = IExcelCellFormat.cellFormat(11, Colour.BLACK, Alignment.CENTRE, Colour.WHITE, Colour.BLACK, Colour.GRAY_25, Colour.BLACK, Colour.BLACK, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.MEDIUM, BorderLineStyle.THIN, VerticalAlignment.CENTRE);
			excel.add(new IExcelCell(wcf, 3, row+1, "客隊"));
			
			wcf = IExcelCellFormat.cellFormat(11, Colour.BLACK, Alignment.CENTRE, Colour.WHITE, Colour.BLACK, Colour.BLACK, Colour.BLACK, Colour.GRAY_25, BorderLineStyle.MEDIUM, BorderLineStyle.DOUBLE, BorderLineStyle.THIN, BorderLineStyle.THIN, VerticalAlignment.CENTRE);
			excel.add(new IExcelCell(wcf, 4, row, ""));
			wcf = IExcelCellFormat.cellFormat(11, Colour.BLACK, Alignment.CENTRE, Colour.WHITE, Colour.BLACK, Colour.BLACK, Colour.BLACK, Colour.GRAY_25, BorderLineStyle.THIN, BorderLineStyle.DOUBLE, BorderLineStyle.MEDIUM, BorderLineStyle.THIN, VerticalAlignment.CENTRE);
			excel.add(new IExcelCell(wcf, 4, row+1, ""));
			
			wcf = IExcelCellFormat.cellFormat(11, Colour.BLACK, Alignment.CENTRE, Colour.WHITE, Colour.BLACK, BorderLineStyle.MEDIUM, BorderLineStyle.DOUBLE, BorderLineStyle.THIN, BorderLineStyle.DOUBLE, VerticalAlignment.CENTRE);
			excel.add(new IExcelCell(wcf, 5, row, 1, 0, "亞洲讓球盤"));
			
			wcf = IExcelCellFormat.cellFormat(11, Colour.BLACK, Alignment.CENTRE, Colour.WHITE, Colour.BLACK, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.MEDIUM, BorderLineStyle.DOUBLE, VerticalAlignment.CENTRE);
			excel.add(new IExcelCell(wcf, 5, row+1, "讓球"));
			
			wcf = IExcelCellFormat.cellFormat(11, Colour.BLACK, Alignment.CENTRE, Colour.WHITE, Colour.BLACK, BorderLineStyle.THIN, BorderLineStyle.DOUBLE, BorderLineStyle.MEDIUM, BorderLineStyle.THIN, VerticalAlignment.CENTRE);
			excel.add(new IExcelCell(wcf, 6, row+1, "賠率"));
			
			wcf = IExcelCellFormat.cellFormat(11, Colour.BLACK, Alignment.CENTRE, Colour.WHITE, Colour.BLACK, BorderLineStyle.MEDIUM, BorderLineStyle.DOUBLE, BorderLineStyle.THIN, BorderLineStyle.DOUBLE, VerticalAlignment.CENTRE);
			excel.add(new IExcelCell(wcf, 7, row, 2, 0, "上下盤"));
			
			wcf = IExcelCellFormat.cellFormat(11, Colour.BLACK, Alignment.CENTRE, Colour.WHITE, Colour.BLACK, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.MEDIUM, BorderLineStyle.DOUBLE, VerticalAlignment.CENTRE);
			excel.add(new IExcelCell(wcf, 7, row+1, "總入球"));
			
			wcf = IExcelCellFormat.cellFormat(10, Colour.BLACK, Alignment.CENTRE, Colour.WHITE, Colour.BLACK, BorderLineStyle.THIN, BorderLineStyle.DOUBLE, BorderLineStyle.MEDIUM, BorderLineStyle.THIN, VerticalAlignment.CENTRE);
			excel.add(new IExcelCell(wcf, 8, row+1, 1, 0, "上盤\n下盤"));
			
			wcf = IExcelCellFormat.cellFormat(11, Colour.BLACK, Alignment.CENTRE, Colour.WHITE, Colour.BLACK, BorderLineStyle.MEDIUM, BorderLineStyle.MEDIUM, BorderLineStyle.MEDIUM, BorderLineStyle.DOUBLE, VerticalAlignment.CENTRE);
			excel.add(new IExcelCell(wcf, 10, row, 1, 1, "標準盤"));
			
			wcf = IExcelCellFormat.cellFormat(14, Colour.BLACK, Alignment.CENTRE, Colour.YELLOW2, Colour.BLACK, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, VerticalAlignment.CENTRE);
			excel.add(new IExcelCell(wcf, 13, row, 0, 1, "新玩法"));
			excel.add(new IExcelCell(wcf, 14, row, 0, 1, "賠率類型"));
			excel.add(new IExcelCell(wcf, 15, row, 0, 1, "AHB only"));
			
			wcf = IExcelCellFormat.cellFormat(14, Colour.BLACK, Alignment.CENTRE, Colour.LIGHT_GREEN, Colour.BLACK, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, VerticalAlignment.CENTRE);
			excel.add(new IExcelCell(wcf, 16, row, 0, 1, "已有波膽半全場"));
			excel.add(new IExcelCell(wcf, 17, row, 0, 1, "已有     新玩法"));
			
			wcf = IExcelCellFormat.cellFormat(14, Colour.BLACK, Alignment.CENTRE, Colour.GRAY_25, Colour.BLACK, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, VerticalAlignment.CENTRE);
			excel.add(new IExcelCell(wcf, 18, row, 0, 1, "不可過關"));
			excel.add(new IExcelCell(wcf, 19, row, 0, 1, "走地"));
			excel.add(new IExcelCell(wcf, 20, row, 0, 1, "中場"));
			excel.add(new IExcelCell(wcf, 21, row, 0, 1, "多元盤"));
			
			wcf = IExcelCellFormat.cellFormat(14, Colour.BLACK, Alignment.CENTRE, Colour.WHITE, Colour.BLACK, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, VerticalAlignment.CENTRE);
			excel.add(new IExcelCell(wcf, 22, row, 0, 1, "ADJ. HOM"));
			excel.add(new IExcelCell(wcf, 23, row, 0, 1, "ADJ. AWAY"));
			excel.add(new IExcelCell(wcf, 24, row, 0, 1, "CALC HOME"));
			excel.add(new IExcelCell(wcf, 25, row, 0, 1, "CALC AWAY"));
			
			
		}
		
		/**
		 *  设置 时间分组
		 * @param excel
		 * @param row
		 */
		public static void setRowByMatch(IExcel excel, int row){
			WritableCellFormat wcf = IExcelCellFormat.cellFormat(11, Colour.BLACK, Alignment.CENTRE, Colour.WHITE, Colour.BLACK, BorderLineStyle.MEDIUM, BorderLineStyle.THIN, BorderLineStyle.MEDIUM, BorderLineStyle.MEDIUM, VerticalAlignment.CENTRE);
			
			excel.add(new IExcelCell(wcf, 0, row, 0, 2, "30 / 01  03:00", -1, 400));
			
			wcf = IExcelCellFormat.cellFormat(11, Colour.BLACK, Alignment.CENTRE, Colour.WHITE, Colour.BLACK, BorderLineStyle.MEDIUM, BorderLineStyle.THIN, BorderLineStyle.MEDIUM, BorderLineStyle.THIN, VerticalAlignment.CENTRE);
			excel.add(new IExcelCell(wcf, 1, row, 0, 2, "賽事"));
			
			
			//2
			wcf = IExcelCellFormat.cellFormat(11, Colour.BLACK, Alignment.CENTRE, Colour.WHITE, Colour.BLACK, BorderLineStyle.MEDIUM, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, VerticalAlignment.CENTRE);
			excel.add(new IExcelCell(wcf, 2, row, "14"));
			
			wcf = IExcelCellFormat.cellFormat(11, Colour.BLACK, Alignment.CENTRE, Colour.WHITE, Colour.BLACK, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, VerticalAlignment.CENTRE);
			excel.add(new IExcelCell(wcf, 2, row + 1, "6"));
			
			wcf = IExcelCellFormat.cellFormat(11, Colour.BLACK, Alignment.CENTRE, Colour.WHITE, Colour.BLACK, Colour.GRAY_25, Colour.BLACK, Colour.BLACK, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.MEDIUM, BorderLineStyle.THIN, VerticalAlignment.CENTRE);
			excel.add(new IExcelCell(wcf, 2, row + 2, "000001"));
			
			
			
			//3 
			wcf = IExcelCellFormat.cellFormat(11, Colour.BLACK, Alignment.CENTRE, Colour.WHITE, Colour.BLACK, Colour.GRAY_25, Colour.BLACK, Colour.BLACK, BorderLineStyle.MEDIUM, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, VerticalAlignment.CENTRE);
			excel.add(new IExcelCell(wcf, 3, row, "加泰"));
			wcf = IExcelCellFormat.cellFormat(11, Colour.BLACK, Alignment.CENTRE, Colour.WHITE, Colour.BLACK, Colour.GRAY_25, Colour.BLACK, Colour.BLACK, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, VerticalAlignment.CENTRE);
			excel.add(new IExcelCell(wcf, 3, row+1, "維拉利爾"));
			wcf = IExcelCellFormat.cellFormat(11, Colour.BLACK, Alignment.CENTRE, Colour.WHITE, Colour.BLACK, Colour.GRAY_25, Colour.BLACK, Colour.GRAY_25, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.MEDIUM, BorderLineStyle.THIN, VerticalAlignment.CENTRE);
			excel.add(new IExcelCell(wcf, 3, row+2, ""));
			
			
			//4
			wcf = IExcelCellFormat.cellFormat(11, Colour.BLACK, Alignment.CENTRE, Colour.WHITE, Colour.BLACK, Colour.BLACK, Colour.BLACK, Colour.GRAY_25, BorderLineStyle.MEDIUM, BorderLineStyle.DOUBLE, BorderLineStyle.THIN, BorderLineStyle.THIN, VerticalAlignment.CENTRE);
			excel.add(new IExcelCell(wcf, 4, row, ""));
			wcf = IExcelCellFormat.cellFormat(11, Colour.BLACK, Alignment.CENTRE, Colour.WHITE, Colour.BLACK, Colour.BLACK, Colour.BLACK, Colour.GRAY_25, BorderLineStyle.THIN, BorderLineStyle.DOUBLE, BorderLineStyle.THIN, BorderLineStyle.THIN, VerticalAlignment.CENTRE);
			excel.add(new IExcelCell(wcf, 4, row+1, ""));
			wcf = IExcelCellFormat.cellFormat(11, Colour.BLACK, Alignment.CENTRE, Colour.WHITE, Colour.BLACK, Colour.GRAY_25, Colour.BLACK, Colour.GRAY_25, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.MEDIUM, BorderLineStyle.THIN, VerticalAlignment.CENTRE);
			excel.add(new IExcelCell(wcf, 4, row+2, ""));
			
			//5
			wcf = IExcelCellFormat.cellFormat(11, Colour.BLACK, Alignment.CENTRE, Colour.WHITE, Colour.BLACK, Colour.BLACK, Colour.BLACK, Colour.BLACK, BorderLineStyle.MEDIUM, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, VerticalAlignment.CENTRE);
			excel.add(new IExcelCell(wcf, 5, row, ""));
			wcf = IExcelCellFormat.cellFormat(11, Colour.BLACK, Alignment.CENTRE, Colour.WHITE, Colour.BLACK, Colour.BLACK, Colour.BLACK, Colour.BLACK, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, VerticalAlignment.CENTRE);
			excel.add(new IExcelCell(wcf, 5, row+1, ""));
			wcf = IExcelCellFormat.cellFormat(11, Colour.BLACK, Alignment.CENTRE, Colour.WHITE, Colour.BLACK, Colour.GRAY_25, Colour.BLACK, Colour.GRAY_25, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.MEDIUM, BorderLineStyle.THIN, VerticalAlignment.CENTRE);
			excel.add(new IExcelCell(wcf, 5, row+2, ""));
			
			
			//6
			wcf = IExcelCellFormat.cellFormat(11, Colour.BLACK, Alignment.CENTRE, Colour.WHITE, Colour.BLACK, Colour.BLACK, Colour.BLACK, Colour.BLACK, BorderLineStyle.MEDIUM, BorderLineStyle.DOUBLE, BorderLineStyle.THIN, BorderLineStyle.THIN, VerticalAlignment.CENTRE);
			excel.add(new IExcelCell(wcf, 6, row, ""));
			wcf = IExcelCellFormat.cellFormat(11, Colour.BLACK, Alignment.CENTRE, Colour.WHITE, Colour.BLACK, Colour.BLACK, Colour.BLACK, Colour.BLACK, BorderLineStyle.THIN, BorderLineStyle.DOUBLE, BorderLineStyle.THIN, BorderLineStyle.THIN, VerticalAlignment.CENTRE);
			excel.add(new IExcelCell(wcf, 6, row+1, ""));
			wcf = IExcelCellFormat.cellFormat(11, Colour.BLACK, Alignment.CENTRE, Colour.WHITE, Colour.BLACK, Colour.GRAY_25, Colour.BLACK, Colour.GRAY_25, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.MEDIUM, BorderLineStyle.THIN, VerticalAlignment.CENTRE);
			excel.add(new IExcelCell(wcf, 6, row+2, ""));
			
			
			
			
			
			//7
			wcf = IExcelCellFormat.cellFormat(11, Colour.BLACK, Alignment.CENTRE, Colour.WHITE, Colour.BLACK, Colour.GRAY_25, Colour.BLACK, Colour.BLACK, BorderLineStyle.MEDIUM, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, VerticalAlignment.CENTRE);
			excel.add(new IExcelCell(wcf, 7, row, ""));
			wcf = IExcelCellFormat.cellFormat(11, Colour.BLACK, Alignment.CENTRE, Colour.WHITE, Colour.BLACK, Colour.GRAY_25, Colour.BLACK, Colour.BLACK, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, VerticalAlignment.CENTRE);
			excel.add(new IExcelCell(wcf, 7, row+1, ""));
			wcf = IExcelCellFormat.cellFormat(11, Colour.BLACK, Alignment.CENTRE, Colour.WHITE, Colour.BLACK, Colour.GRAY_25, Colour.BLACK, Colour.GRAY_25, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.MEDIUM, BorderLineStyle.THIN, VerticalAlignment.CENTRE);
			excel.add(new IExcelCell(wcf, 7, row+2, ""));
			
			
			//8
			wcf = IExcelCellFormat.cellFormat(11, Colour.BLACK, Alignment.CENTRE, Colour.WHITE, Colour.BLACK, Colour.GRAY_25, Colour.BLACK, Colour.GRAY_25, BorderLineStyle.MEDIUM, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, VerticalAlignment.CENTRE);
			excel.add(new IExcelCell(wcf, 8, row, ""));
			wcf = IExcelCellFormat.cellFormat(11, Colour.BLACK, Alignment.CENTRE, Colour.WHITE, Colour.BLACK, Colour.GRAY_25, Colour.BLACK, Colour.GRAY_25, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, VerticalAlignment.CENTRE);
			excel.add(new IExcelCell(wcf, 8, row+1, ""));
			wcf = IExcelCellFormat.cellFormat(11, Colour.BLACK, Alignment.CENTRE, Colour.WHITE, Colour.BLACK, Colour.GRAY_25, Colour.BLACK, Colour.GRAY_25, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.MEDIUM, BorderLineStyle.THIN, VerticalAlignment.CENTRE);
			excel.add(new IExcelCell(wcf, 8, row+2, ""));
			
			
			//9
			wcf = IExcelCellFormat.cellFormat(11, Colour.BLACK, Alignment.CENTRE, Colour.WHITE, Colour.BLACK, Colour.BLACK, Colour.BLACK, Colour.GRAY_25, BorderLineStyle.MEDIUM, BorderLineStyle.DOUBLE, BorderLineStyle.THIN, BorderLineStyle.THIN, VerticalAlignment.CENTRE);
			excel.add(new IExcelCell(wcf, 9, row, ""));
			wcf = IExcelCellFormat.cellFormat(11, Colour.BLACK, Alignment.CENTRE, Colour.WHITE, Colour.BLACK, Colour.BLACK, Colour.BLACK, Colour.GRAY_25, BorderLineStyle.THIN, BorderLineStyle.DOUBLE, BorderLineStyle.THIN, BorderLineStyle.THIN, VerticalAlignment.CENTRE);
			excel.add(new IExcelCell(wcf, 9, row+1, ""));
			wcf = IExcelCellFormat.cellFormat(11, Colour.BLACK, Alignment.CENTRE, Colour.WHITE, Colour.BLACK, Colour.BLACK, Colour.BLACK, Colour.GRAY_25, BorderLineStyle.THIN, BorderLineStyle.DOUBLE, BorderLineStyle.MEDIUM, BorderLineStyle.THIN, VerticalAlignment.CENTRE);
			excel.add(new IExcelCell(wcf, 9, row+2, ""));
			
			
			
			//10
			wcf = IExcelCellFormat.cellFormat(11, Colour.BLACK, Alignment.CENTRE, Colour.WHITE, Colour.BLACK, BorderLineStyle.MEDIUM, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.DOUBLE, VerticalAlignment.CENTRE);
			excel.add(new IExcelCell(wcf, 10, row, ""));
			wcf = IExcelCellFormat.cellFormat(11, Colour.BLACK, Alignment.CENTRE, Colour.WHITE, Colour.BLACK, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.DOUBLE, VerticalAlignment.CENTRE);
			excel.add(new IExcelCell(wcf, 10, row+1, ""));
			wcf = IExcelCellFormat.cellFormat(11, Colour.BLACK, Alignment.CENTRE, Colour.WHITE, Colour.BLACK, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.MEDIUM, BorderLineStyle.DOUBLE, VerticalAlignment.CENTRE);
			excel.add(new IExcelCell(wcf, 10, row+2, ""));
			
			
			//11
			wcf = IExcelCellFormat.cellFormat(11, Colour.BLACK, Alignment.CENTRE, Colour.WHITE, Colour.BLACK, BorderLineStyle.MEDIUM, BorderLineStyle.MEDIUM, BorderLineStyle.THIN, BorderLineStyle.THIN, VerticalAlignment.CENTRE);
			excel.add(new IExcelCell(wcf, 11, row, "主"));
			wcf = IExcelCellFormat.cellFormat(11, Colour.BLACK, Alignment.CENTRE, Colour.WHITE, Colour.BLACK, BorderLineStyle.THIN, BorderLineStyle.MEDIUM, BorderLineStyle.THIN, BorderLineStyle.THIN, VerticalAlignment.CENTRE);
			excel.add(new IExcelCell(wcf, 11, row+1, "客"));
			wcf = IExcelCellFormat.cellFormat(11, Colour.BLACK, Alignment.CENTRE, Colour.WHITE, Colour.BLACK, BorderLineStyle.THIN, BorderLineStyle.MEDIUM, BorderLineStyle.MEDIUM, BorderLineStyle.THIN, VerticalAlignment.CENTRE);
			excel.add(new IExcelCell(wcf, 11, row+2, "和"));
			
			
			wcf = IExcelCellFormat.cellFormat(14, Colour.BLACK, Alignment.CENTRE, Colour.YELLOW2, Colour.BLACK, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, VerticalAlignment.CENTRE);
			excel.add(new IExcelCell(wcf, 13, row, 0, 2, "新玩法"));
			excel.add(new IExcelCell(wcf, 14, row, 0, 2, "賠率類型"));
			excel.add(new IExcelCell(wcf, 15, row, 0, 2, "AHB only"));
			
			wcf = IExcelCellFormat.cellFormat(14, Colour.BLACK, Alignment.CENTRE, Colour.LIGHT_GREEN, Colour.BLACK, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, VerticalAlignment.CENTRE);
			excel.add(new IExcelCell(wcf, 16, row, 0, 2, "已有波膽半全場"));
			excel.add(new IExcelCell(wcf, 17, row, 0, 2, "已有     新玩法"));
			
			wcf = IExcelCellFormat.cellFormat(14, Colour.BLACK, Alignment.CENTRE, Colour.GRAY_25, Colour.BLACK, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, VerticalAlignment.CENTRE);
			excel.add(new IExcelCell(wcf, 18, row, 0, 2, "不可過關"));
			excel.add(new IExcelCell(wcf, 19, row, 0, 2, "走地"));
			excel.add(new IExcelCell(wcf, 20, row, 0, 2, "中場"));
			excel.add(new IExcelCell(wcf, 21, row, 0, 2, "多元盤"));
			
			wcf = IExcelCellFormat.cellFormat(14, Colour.BLACK, Alignment.CENTRE, Colour.WHITE, Colour.BLACK, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, VerticalAlignment.CENTRE);
			excel.add(new IExcelCell(wcf, 22, row, 0, 2, "ADJ. HOM"));
			excel.add(new IExcelCell(wcf, 23, row, 0, 2, "ADJ. AWAY"));
			excel.add(new IExcelCell(wcf, 24, row, 0, 2, "CALC HOME"));
			excel.add(new IExcelCell(wcf, 25, row, 0, 2, "CALC AWAY"));
			
			
		}
		


//		public static void main(String[] args) {
//			String fileName = "/Users/chenrixiang/Desktop/temp.xls"; //
//
//			IExcel excel = new IExcel();
//			try {
//				File file = new File(fileName);
//				List<IExcelCell> excelCells = new ArrayList<IExcelCell>(0);
//				excel.setExcelCells(excelCells);
//				IExcelBobbin.create(excel, file);
//				int row = 0;
//				IExcelBobbin.setWidth(excel, row);
//				row++;
//				IExcelBobbin.setTitleBar(excel, row);
//				row +=2;
//				for(int i = 0; i < 5; i++){
//					IExcelBobbin.setRowByMatch(excel, row);
//					row +=3;
//				}
//				
//				for (IExcelCell bean : excelCells) {
//					excel.addCell(bean);
//				}
//				excel.writeAndClose();
//				Runtime.getRuntime().exec("open " + fileName);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//
//		}


}
