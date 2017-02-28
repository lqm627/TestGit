package com.easylotto.core.export.excel;

import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import jxl.format.Alignment;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.NumberFormat;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;

import org.apache.commons.lang.StringUtils;

/**
 * Open Tools Excel 
 *
 * @author chenrixiang
 * @version 1.0
 * @create_date 2015年6月15日 上午9:19:56
 * @update_date 
 *
 */
public class IExcelOpenTools {

		private final static String EXCEL_FONT_XXMT = "華康新儷粗黑";

		private final static String SHEET_NAME = "賽程格式表";
//		  											  1           5               10              15             20             25          29         6  7  8  9  10 
		private final static Integer[] CELL_WIDTHS = {5, 5, 6, 6, 6, 5, 7, 4, 16, 5, 4, 16, 5, 5, 7, 7, 7, 5, 6, 5, 5, 3, 5, 5, 5, 7, 7, 6, 6};//TODO
		
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
			excel.add(new IExcelCell(wcf, 30, row, " AB~AC 检测数值计算正确后隐藏", 20));
		}
		
		/**
		 * 设置 标题栏
		 * @param excel
		 * @param row
		 */
		public static void setTitleBar(IExcel excel, int row){
			WritableCellFormat wcf12 = IExcelCellFormat.cellFormat(12);
			WritableCellFormat wcf9 = IExcelCellFormat.cellFormat(9, Colour.GRAY_50, Alignment.CENTRE, Colour.GRAY_50, VerticalAlignment.BOTTOM);
			
			excel.add(new IExcelCell(wcf12, 0, row, "新", -1, 600));
			excel.add(new IExcelCell(wcf12, 1, row, "賽事"));
			excel.add(new IExcelCell(wcf9, 2, row, "標準"));
			excel.add(new IExcelCell(wcf9, 3, row, "亞洲"));
			excel.add(new IExcelCell(wcf9, 4, row, "上下"));
			
			WritableCellFormat wcf = IExcelCellFormat.cellFormat(12, BorderLineStyle.MEDIUM, BorderLineStyle.THIN, Colour.BLACK);
			
			excel.add(new IExcelCell(IExcelCellFormat.cellFormat(12, Colour.BLACK, BorderLineStyle.MEDIUM, BorderLineStyle.THIN), 5, row, "時間"));
			excel.add(new IExcelCell(wcf, 6, row, "主", 5));
			excel.add(new IExcelCell(wcf, 7, row, 1, 0, "主隊"));
			excel.add(new IExcelCell(wcf, 9, row, "和", 5));
			excel.add(new IExcelCell(wcf, 10, row, 1, 0, "客隊"));
			excel.add(new IExcelCell(wcf, 12, row, "客", 5));
			excel.add(new IExcelCell(wcf, 13, row, 3, 0, "亞洲讓球盤"));
			excel.add(new IExcelCell(wcf, 17, row, "註"));
			excel.add(new IExcelCell(wcf, 18, row, "中位"));
			excel.add(new IExcelCell(wcf, 19, row, "上", 5));
			excel.add(new IExcelCell(IExcelCellFormat.cellFormat(12, BorderLineStyle.MEDIUM, Colour.BLACK, BorderLineStyle.THIN), 20, row, "下"));
			excel.add(new IExcelCell(wcf12, 21, row, ""));
			
			wcf = IExcelCellFormat.cellFormat(EXCEL_FONT_XXMT, 10, BorderLineStyle.MEDIUM, BorderLineStyle.THIN, Colour.BLACK);
			
			excel.add(new IExcelCell(IExcelCellFormat.cellFormat(10, Colour.BLACK, BorderLineStyle.MEDIUM, BorderLineStyle.THIN), 22, row, "A+"));
			excel.add(new IExcelCell(wcf, 23, row, "B+"));
			excel.add(new IExcelCell(wcf, 24, row, "A+B"));
			excel.add(new IExcelCell(wcf, 25, row, "HOME"));
			excel.add(new IExcelCell(IExcelCellFormat.cellFormat(10, BorderLineStyle.MEDIUM, Colour.BLACK, BorderLineStyle.THIN), 26, row, "AWAY"));
		}
		
		/**
		 *  设置 时间分组
		 * @param excel
		 * @param row
		 */
		public static void setRowByDateByMatch(IExcel excel, int row, String content){
			WritableCellFormat wcf12 = IExcelCellFormat.cellFormat(12);
			excel.add(new IExcelCell(wcf12, 0, row, ""));
			excel.add(new IExcelCell(wcf12, 1, row, ""));
			excel.add(new IExcelCell(IExcelCellFormat.cellFormat(EXCEL_FONT_XXMT, 10, Colour.BLACK, Colour.BLACK,BorderLineStyle.THIN, BorderLineStyle.MEDIUM, VerticalAlignment.CENTRE), 5, row, 15, 0, content));
		}
		
		/**
		 * 设置 活动中的赛事
		 * @param excel
		 * @param row
		 */
		public static void setRowByActivity(IExcel excel, int row){
			String formula = "";
			WritableCellFormat wcf12 = IExcelCellFormat.cellFormat(12);
			int i = row + 1;
			excel.add(new IExcelCell(wcf12, 0, row, ""));
			excel.add(new IExcelCell(wcf12, 1, row, ""));
			
			formula = StringUtils.replace("((1/G|#|+1/J|#|+1/M|#|)-1)/(1/G|#|+1/J|#|+1/M|#|)", "|#|", ""+i);
			excel.add(new IExcelCell(IExcelCellFormat.cellFormat(9, Colour.GRAY_50, Alignment.CENTRE, Colour.GRAY_50, VerticalAlignment.BOTTOM, new NumberFormat("##0.00%")), 2, row, formula, true));
			
			formula = StringUtils.replace("(4-(P|#|+Q|#|))", "|#|", ""+i);
			excel.add(new IExcelCell(IExcelCellFormat.cellFormat(9, Colour.GRAY_50, Colour.YELLOW, Alignment.CENTRE, Colour.GRAY_50, VerticalAlignment.BOTTOM, new NumberFormat("##0.0#%")), 3, row, formula, true));
			
			formula = StringUtils.replace("SUM(T|#|+U|#|)", "|#|", ""+i);
			excel.add(new IExcelCell(IExcelCellFormat.cellFormat(9, Colour.GRAY_50, Colour.YELLOW, Alignment.CENTRE, Colour.GRAY_50, VerticalAlignment.BOTTOM, new NumberFormat("##0.0#")), 4, row, formula, true));
			
			excel.add(new IExcelCell(IExcelCellFormat.cellFormat(10, Colour.BLACK, Alignment.CENTRE, Colour.GRAY_25, Colour.BLACK, Colour.BLACK, Colour.BLACK, Colour.BLACK, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.MEDIUM, VerticalAlignment.CENTRE), 5, row, "13:00"));
			excel.add(new IExcelCell(IExcelCellFormat.cellFormat(10, Colour.BLACK, Alignment.CENTRE, Colour.GRAY_25, Colour.BLACK, Colour.GRAY_25, Colour.BLACK, Colour.BLACK, BorderLineStyle.THIN, BorderLineStyle.NONE, BorderLineStyle.THIN, BorderLineStyle.THIN, VerticalAlignment.CENTRE), 6, row, "2"));
			excel.add(new IExcelCell(IExcelCellFormat.cellFormat(8, Colour.RED, Alignment.CENTRE, Colour.GRAY_25, Colour.BLACK, Colour.GRAY_25, Colour.BLACK, Colour.GRAY_25, BorderLineStyle.THIN, BorderLineStyle.NONE, BorderLineStyle.THIN, BorderLineStyle.NONE, VerticalAlignment.BOTTOM), 7, row, "3"));
			excel.add(new IExcelCell(IExcelCellFormat.cellFormat(10, Colour.BLACK, Alignment.CENTRE, Colour.GRAY_25, Colour.BLACK, Colour.GRAY_25, Colour.BLACK, Colour.GRAY_25, BorderLineStyle.THIN, BorderLineStyle.NONE, BorderLineStyle.THIN, BorderLineStyle.NONE, VerticalAlignment.CENTRE), 8, row, "磐田山葉"));//
			excel.add(new IExcelCell(IExcelCellFormat.cellFormat(10, Colour.BLACK, Alignment.CENTRE, Colour.GRAY_25, Colour.BLACK, Colour.GRAY_25, Colour.BLACK, Colour.GRAY_25, BorderLineStyle.THIN, BorderLineStyle.NONE, BorderLineStyle.THIN, BorderLineStyle.NONE, VerticalAlignment.CENTRE), 9, row, "3.2"));
			excel.add(new IExcelCell(IExcelCellFormat.cellFormat(8, Colour.RED, Alignment.CENTRE, Colour.GRAY_25, Colour.BLACK, Colour.GRAY_25, Colour.BLACK, Colour.GRAY_25, BorderLineStyle.THIN, BorderLineStyle.NONE, BorderLineStyle.THIN, BorderLineStyle.NONE, VerticalAlignment.BOTTOM), 10, row, "2"));
			excel.add(new IExcelCell(IExcelCellFormat.cellFormat(10, Colour.BLACK, Alignment.CENTRE, Colour.GRAY_25, Colour.BLACK, Colour.GRAY_25, Colour.BLACK, Colour.GRAY_25, BorderLineStyle.THIN, BorderLineStyle.NONE, BorderLineStyle.THIN, BorderLineStyle.NONE, VerticalAlignment.CENTRE), 11, row, "金澤薩維根"));
			excel.add(new IExcelCell(IExcelCellFormat.cellFormat(10, Colour.BLACK, Alignment.CENTRE, Colour.GRAY_25, Colour.BLACK, Colour.BLACK, Colour.BLACK, Colour.GRAY_25, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.NONE, VerticalAlignment.CENTRE), 12, row, "3.2"));
			excel.add(new IExcelCell(IExcelCellFormat.cellFormat(10, Colour.BLACK, Alignment.LEFT, Colour.GRAY_25, Colour.BLACK, Colour.GRAY_25, Colour.BLACK, Colour.BLACK, BorderLineStyle.THIN, BorderLineStyle.NONE, BorderLineStyle.THIN, BorderLineStyle.THIN, VerticalAlignment.CENTRE), 13, row, "磐田山葉"));
			excel.add(new IExcelCell(IExcelCellFormat.cellFormat(10, Colour.BLACK, Alignment.CENTRE, Colour.GRAY_25, Colour.BLACK, Colour.GRAY_25, Colour.BLACK, Colour.GRAY_25, BorderLineStyle.THIN, BorderLineStyle.NONE, BorderLineStyle.THIN, BorderLineStyle.NONE, VerticalAlignment.CENTRE), 14, row, "0/0.5"));
			excel.add(new IExcelCell(IExcelCellFormat.cellFormat(10, Colour.BLACK, Alignment.CENTRE, Colour.GRAY_25, Colour.BLACK, Colour.GRAY_25, Colour.BLACK, Colour.GRAY_25, BorderLineStyle.THIN, BorderLineStyle.NONE, BorderLineStyle.THIN, BorderLineStyle.NONE, VerticalAlignment.CENTRE), 15, row, "1.8"));
			excel.add(new IExcelCell(IExcelCellFormat.cellFormat(10, Colour.BLACK, Alignment.CENTRE, Colour.GRAY_25, Colour.BLACK, Colour.BLACK, Colour.BLACK, Colour.GRAY_25, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.NONE, VerticalAlignment.CENTRE), 16, row, "2"));
			excel.add(new IExcelCell(IExcelCellFormat.cellFormat(10, Colour.BLACK, Alignment.CENTRE, Colour.GRAY_25, Colour.BLACK, Colour.BLACK, Colour.BLACK, Colour.BLACK, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, VerticalAlignment.CENTRE), 17, row, ""));
			excel.add(new IExcelCell(IExcelCellFormat.cellFormat(10, Colour.BLACK, Alignment.CENTRE, Colour.GRAY_25, Colour.BLACK, Colour.GRAY_25, Colour.BLACK, Colour.BLACK, BorderLineStyle.THIN, BorderLineStyle.NONE, BorderLineStyle.THIN, BorderLineStyle.THIN, VerticalAlignment.CENTRE), 18, row, "2.5"));
			excel.add(new IExcelCell(IExcelCellFormat.cellFormat(10, Colour.BLACK, Alignment.CENTRE, Colour.GRAY_25, Colour.BLACK, Colour.GRAY_25, Colour.BLACK, Colour.GRAY_25, BorderLineStyle.THIN, BorderLineStyle.NONE, BorderLineStyle.THIN, BorderLineStyle.NONE, VerticalAlignment.CENTRE), 19, row, "1.85"));
			excel.add(new IExcelCell(IExcelCellFormat.cellFormat(10, Colour.BLACK, Alignment.CENTRE, Colour.GRAY_25, Colour.BLACK, Colour.BLACK, Colour.BLACK, Colour.GRAY_25, BorderLineStyle.THIN, BorderLineStyle.MEDIUM, BorderLineStyle.THIN, BorderLineStyle.NONE, VerticalAlignment.CENTRE), 20, row, "1.85"));
			
			
			formula = StringUtils.replace("IF(OR(G|#|<>0,M|#|<>0),IF(M|#|=G|#|,0.125,IF(N|#|=I|#|,0.13,IF(N|#|=L|#|,0.12))))", "|#|", ""+i);
			excel.add(new IExcelCell(IExcelCellFormat.cellFormat(10, Colour.BLACK, Alignment.CENTRE, Colour.GRAY_25,VerticalAlignment.CENTRE), 22, row, formula, true));
			
			formula = StringUtils.replace("IF(OR(G|#|<>0,M|#|<>0),IF(M|#|=G|#|,0.125,IF(N|#|=L|#|,0.13,IF(N|#|=I|#|,0.12))))", "|#|", ""+i);
			excel.add(new IExcelCell(IExcelCellFormat.cellFormat(10, Colour.BLACK, Alignment.CENTRE, Colour.GRAY_25,VerticalAlignment.CENTRE), 23, row, formula, true));
			
			formula = StringUtils.replace("X|#|+W|#|", "|#|", ""+i);
			excel.add(new IExcelCell(IExcelCellFormat.cellFormat(10, Colour.BLACK, Alignment.CENTRE, Colour.GRAY_25,VerticalAlignment.CENTRE), 24, row, formula, true));
			
			formula = StringUtils.replace("IF(OR(N|#|=I|#|,N|#|=L|#|),IF(AB|#|<0,(AC|#|+ABS(AB|#|))/2,(AC|#|-ABS(AB|#|))/2),FALSE)", "|#|", ""+i);
			excel.add(new IExcelCell(IExcelCellFormat.cellFormat(10, Colour.BLACK, Alignment.CENTRE, Colour.GRAY_25,VerticalAlignment.CENTRE, NumberFormats.FLOAT), 25, row, formula, true));
			
			formula = StringUtils.replace("IF(OR(N|#|=I|#|,N|#|=L|#|),IF(AB|#|<0,(AC|#|-ABS(AB|#|))/2,(AC|#|+ABS(AB|#|))/2),FALSE)", "|#|", ""+i);
			excel.add(new IExcelCell(IExcelCellFormat.cellFormat(10, Colour.BLACK, Alignment.CENTRE, Colour.GRAY_25,VerticalAlignment.CENTRE, NumberFormats.FLOAT), 26, row, formula, true));
			
			formula = StringUtils.replace("IF(OR(N5=I|#|,N|#|=L|#|),((IF(N|#|=I|#|,-1,1))*IF(OR(LEN(O|#|)=1,LEN(O|#|)=3)=TRUE,O|#|,LEFT(O|#|,FIND(\"/\",O|#|)-1)+0.25)+5/3-1/0.3/(1+IF(N|#|=I|#|,P|#|/Q|#|,IF(N|#|=L|#|,Q|#|/P|#|)))),FALSE)", "|#|", ""+i);
			excel.add(new IExcelCell(IExcelCellFormat.cellFormat(10, Colour.BLACK, Alignment.CENTRE, Colour.GRAY_25,VerticalAlignment.CENTRE, NumberFormats.DEFAULT), 27, row, formula, true));
			
			formula = StringUtils.replace("IF(OR(LEN(S|#|)=1,LEN(S|#|)=3)=TRUE,S|#|,LEFT(S|#|,FIND(\"/\",S|#|)-1)+0.25)-(T|#|-U|#|)/U|#|/0.3*0.25", "|#|", ""+i);
			excel.add(new IExcelCell(IExcelCellFormat.cellFormat(10, Colour.BLACK, Alignment.CENTRE, Colour.GRAY_25,VerticalAlignment.CENTRE, NumberFormats.DEFAULT), 28, row, formula, true));
		}
		
		
		/**
		 * 设置 未开盘的赛事
		 * @param excel
		 * @param row
		 */
		public static void setRowByMatch(IExcel excel, int row){
			String formula = "";
			WritableCellFormat wcf12 = IExcelCellFormat.cellFormat(12);
			int i = row + 1;
			excel.add(new IExcelCell(wcf12, 0, row, ""));
			excel.add(new IExcelCell(wcf12, 1, row, ""));
			
			formula = StringUtils.replace("((1/G|#|+1/J|#|+1/M|#|)-1)/(1/G|#|+1/J|#|+1/M|#|)", "|#|", ""+i);
			excel.add(new IExcelCell(IExcelCellFormat.cellFormat(9, Colour.GRAY_50, Alignment.CENTRE, Colour.GRAY_50, VerticalAlignment.BOTTOM, new NumberFormat("##0.00%")), 2, row, formula, true));
			
			formula = StringUtils.replace("(4-(P|#|+Q|#|))", "|#|", ""+i);
			excel.add(new IExcelCell(IExcelCellFormat.cellFormat(9, Colour.GRAY_50, Colour.YELLOW, Alignment.CENTRE, Colour.GRAY_50, VerticalAlignment.BOTTOM, new NumberFormat("##0.0#%")), 3, row, formula, true));
			
			formula = StringUtils.replace("SUM(T|#|+U|#|)", "|#|", ""+i);
			excel.add(new IExcelCell(IExcelCellFormat.cellFormat(9, Colour.GRAY_50, Colour.WHITE, Alignment.CENTRE, Colour.GRAY_50, VerticalAlignment.BOTTOM, new NumberFormat("##0.0#")), 4, row, formula, true));
			
			excel.add(new IExcelCell(IExcelCellFormat.cellFormat(10, Colour.BLACK, Alignment.CENTRE, Colour.WHITE, Colour.BLACK, Colour.BLACK, Colour.BLACK, Colour.BLACK, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.MEDIUM, VerticalAlignment.CENTRE), 5, row, "13:00"));
			excel.add(new IExcelCell(IExcelCellFormat.cellFormat(10, Colour.BLACK, Alignment.CENTRE, Colour.WHITE, Colour.BLACK, Colour.GRAY_25, Colour.BLACK, Colour.BLACK, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, VerticalAlignment.CENTRE), 6, row, ""));
			excel.add(new IExcelCell(IExcelCellFormat.cellFormat(8, Colour.RED, Alignment.CENTRE, Colour.WHITE, Colour.BLACK, Colour.GRAY_25, Colour.BLACK, Colour.GRAY_25, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, VerticalAlignment.BOTTOM), 7, row, "1"));
			excel.add(new IExcelCell(IExcelCellFormat.cellFormat(10, Colour.BLACK, Alignment.CENTRE, Colour.WHITE, Colour.BLACK, Colour.GRAY_25, Colour.BLACK, Colour.GRAY_25, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, VerticalAlignment.CENTRE), 8, row, "全北汽車"));//
			excel.add(new IExcelCell(IExcelCellFormat.cellFormat(10, Colour.BLACK, Alignment.CENTRE, Colour.WHITE, Colour.BLACK, Colour.GRAY_25, Colour.BLACK, Colour.GRAY_25, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, VerticalAlignment.CENTRE), 9, row, ""));
			excel.add(new IExcelCell(IExcelCellFormat.cellFormat(8, Colour.RED, Alignment.CENTRE, Colour.WHITE, Colour.BLACK, Colour.GRAY_25, Colour.BLACK, Colour.GRAY_25, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, VerticalAlignment.BOTTOM), 10, row, "6"));
			excel.add(new IExcelCell(IExcelCellFormat.cellFormat(10, Colour.BLACK, Alignment.CENTRE, Colour.WHITE, Colour.BLACK, Colour.GRAY_25, Colour.BLACK, Colour.GRAY_25, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, VerticalAlignment.CENTRE), 11, row, "FC首爾"));
			excel.add(new IExcelCell(IExcelCellFormat.cellFormat(10, Colour.BLACK, Alignment.CENTRE, Colour.WHITE, Colour.BLACK, Colour.BLACK, Colour.BLACK, Colour.GRAY_25, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, VerticalAlignment.CENTRE), 12, row, ""));
			excel.add(new IExcelCell(IExcelCellFormat.cellFormat(10, Colour.BLACK, Alignment.LEFT, Colour.WHITE, Colour.BLACK, Colour.GRAY_25, Colour.BLACK, Colour.BLACK, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, VerticalAlignment.CENTRE), 13, row, ""));
			excel.add(new IExcelCell(IExcelCellFormat.cellFormat(10, Colour.BLACK, Alignment.CENTRE, Colour.WHITE, Colour.BLACK, Colour.GRAY_25, Colour.BLACK, Colour.GRAY_25, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, VerticalAlignment.CENTRE), 14, row, ""));
			excel.add(new IExcelCell(IExcelCellFormat.cellFormat(10, Colour.BLACK, Alignment.CENTRE, Colour.WHITE, Colour.BLACK, Colour.GRAY_25, Colour.BLACK, Colour.GRAY_25, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, VerticalAlignment.CENTRE), 15, row, ""));
			excel.add(new IExcelCell(IExcelCellFormat.cellFormat(10, Colour.BLACK, Alignment.CENTRE, Colour.WHITE, Colour.BLACK, Colour.BLACK, Colour.BLACK, Colour.GRAY_25, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, VerticalAlignment.CENTRE), 16, row, ""));
			excel.add(new IExcelCell(IExcelCellFormat.cellFormat(10, Colour.BLACK, Alignment.CENTRE, Colour.WHITE, Colour.BLACK, Colour.BLACK, Colour.BLACK, Colour.BLACK, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, VerticalAlignment.CENTRE), 17, row, ""));
			excel.add(new IExcelCell(IExcelCellFormat.cellFormat(10, Colour.BLACK, Alignment.CENTRE, Colour.WHITE, Colour.BLACK, Colour.GRAY_25, Colour.BLACK, Colour.BLACK, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, VerticalAlignment.CENTRE), 18, row, ""));
			excel.add(new IExcelCell(IExcelCellFormat.cellFormat(10, Colour.BLACK, Alignment.CENTRE, Colour.WHITE, Colour.BLACK, Colour.GRAY_25, Colour.BLACK, Colour.GRAY_25, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, VerticalAlignment.CENTRE), 19, row, ""));
			excel.add(new IExcelCell(IExcelCellFormat.cellFormat(10, Colour.BLACK, Alignment.CENTRE, Colour.WHITE, Colour.BLACK, Colour.BLACK, Colour.BLACK, Colour.GRAY_25, BorderLineStyle.THIN, BorderLineStyle.MEDIUM, BorderLineStyle.THIN, BorderLineStyle.THIN, VerticalAlignment.CENTRE), 20, row, ""));
			
			formula = StringUtils.replace("IF(OR(G|#|<>0,M|#|<>0),IF(M|#|=G|#|,0.125,IF(N|#|=I|#|,0.13,IF(N|#|=L|#|,0.12))))", "|#|", ""+i);
			excel.add(new IExcelCell(IExcelCellFormat.cellFormat(10, Colour.BLACK, Alignment.CENTRE, Colour.GRAY_25,VerticalAlignment.CENTRE), 22, row, formula, true));
			
			formula = StringUtils.replace("IF(OR(G|#|<>0,M|#|<>0),IF(M|#|=G|#|,0.125,IF(N|#|=L|#|,0.13,IF(N|#|=I|#|,0.12))))", "|#|", ""+i);
			excel.add(new IExcelCell(IExcelCellFormat.cellFormat(10, Colour.BLACK, Alignment.CENTRE, Colour.GRAY_25,VerticalAlignment.CENTRE), 23, row, formula, true));
			
			formula = StringUtils.replace("X|#|+W|#|", "|#|", ""+i);
			excel.add(new IExcelCell(IExcelCellFormat.cellFormat(10, Colour.BLACK, Alignment.CENTRE, Colour.GRAY_25,VerticalAlignment.CENTRE), 24, row, formula, true));
			
			formula = StringUtils.replace("IF(OR(N|#|=I|#|,N|#|=L|#|),IF(AB|#|<0,(AC|#|+ABS(AB|#|))/2,(AC|#|-ABS(AB|#|))/2),FALSE)", "|#|", ""+i);
			excel.add(new IExcelCell(IExcelCellFormat.cellFormat(10, Colour.BLACK, Alignment.CENTRE, Colour.GRAY_25,VerticalAlignment.CENTRE, NumberFormats.FLOAT), 25, row, formula, true));
			
			formula = StringUtils.replace("IF(OR(N|#|=I|#|,N|#|=L|#|),IF(AB|#|<0,(AC|#|-ABS(AB|#|))/2,(AC|#|+ABS(AB|#|))/2),FALSE)", "|#|", ""+i);
			excel.add(new IExcelCell(IExcelCellFormat.cellFormat(10, Colour.BLACK, Alignment.CENTRE, Colour.GRAY_25,VerticalAlignment.CENTRE, NumberFormats.FLOAT), 26, row, formula, true));
			
			formula = StringUtils.replace("IF(OR(N5=I|#|,N|#|=L|#|),((IF(N|#|=I|#|,-1,1))*IF(OR(LEN(O|#|)=1,LEN(O|#|)=3)=TRUE,O|#|,LEFT(O|#|,FIND(\"/\",O|#|)-1)+0.25)+5/3-1/0.3/(1+IF(N|#|=I|#|,P|#|/Q|#|,IF(N|#|=L|#|,Q|#|/P|#|)))),FALSE)", "|#|", ""+i);
			excel.add(new IExcelCell(IExcelCellFormat.cellFormat(10, Colour.BLACK, Alignment.CENTRE, Colour.GRAY_25,VerticalAlignment.CENTRE, NumberFormats.DEFAULT), 27, row, formula, true));
			
			formula = StringUtils.replace("IF(OR(LEN(S|#|)=1,LEN(S|#|)=3)=TRUE,S|#|,LEFT(S|#|,FIND(\"/\",S|#|)-1)+0.25)-(T|#|-U|#|)/U|#|/0.3*0.25", "|#|", ""+i);
			excel.add(new IExcelCell(IExcelCellFormat.cellFormat(10, Colour.BLACK, Alignment.CENTRE, Colour.GRAY_25,VerticalAlignment.CENTRE, NumberFormats.DEFAULT), 28, row, formula, true));
		}
		
		
		


//		public static void main(String[] args) {
//			String fileName = "/Users/chenrixiang/Desktop/temp.xls"; //
//
//			IExcel excel = new IExcel();
//			try {
//				File file = new File(fileName);
//				List<IExcelCell> excelCells = new ArrayList<IExcelCell>(0);
//				excel.setExcelCells(excelCells);
//				IExcelOpenTools.create(excel, file);
//				int row = 0;
//				IExcelOpenTools.setWidth(excel, row);
//				row++;
//				IExcelOpenTools.setTitleBar(excel, row);
//				for(int i = 0; i < 5; i++){
//					row++;
//					IExcelOpenTools.setRowByDateByMatch(excel, row, "2015 年 6 月 6 日 (星期六)");
//					row++;
//					IExcelOpenTools.setRowByDateByMatch(excel, row, "日本乙組聯賽");
//					//------------------------开盘-------------------------
//					for(int j = 0; j < 5; j++){
//						row++;
//						IExcelOpenTools.setRowByActivity(excel, row);
//					}
//					//-----------------------开盘--------------------------
//					row++;
//					IExcelOpenTools.setRowByDateByMatch(excel, row, "南韓職業聯賽");
//					//未开盘 
//					for(int j = 0; j < 5; j++){
//						row++;
//						IExcelOpenTools.setRowByMatch(excel, row);
//					}
//					// 未开盘
//					row++;
//					IExcelOpenTools.setRowByDateByMatch(excel, row, "阿根廷甲組聯賽");
//					//未开盘 
//					for(int j = 0; j < 5; j++){
//						row++;
//						IExcelOpenTools.setRowByMatch(excel, row);
//					}
//					// 未开盘
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
