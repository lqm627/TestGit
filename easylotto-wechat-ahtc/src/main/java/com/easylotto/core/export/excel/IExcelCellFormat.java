package com.easylotto.core.export.excel;

import jxl.biff.DisplayFormat;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableFont.FontName;
import jxl.write.WriteException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IExcelCellFormat {

	private static final Logger logger = LoggerFactory.getLogger( IExcelCellFormat.class );
	
	
	/**
	 * 设置单元格
	 * @param fn
	 * @param fontSize
	 * @param fontColour
	 * @param align
	 * @param background
	 * @param verticalAlignment
	 * @return
	 */
	public static WritableCellFormat cellFormat(FontName fn, int fontSize,
			Colour fontColour, jxl.format.Alignment align, Colour background,
			 VerticalAlignment verticalAlignment) {
		WritableFont wf = new WritableFont(fn, fontSize, WritableFont.NO_BOLD,
				false, UnderlineStyle.NO_UNDERLINE, fontColour);// 定义格式 字体 下划线 斜体 粗体 颜色
		WritableCellFormat wcf = new WritableCellFormat(wf); // 单元格定义
		try {
			wcf.setBackground(null == background ? jxl.format.Colour.WHITE : background); // 设置单元格的背景颜色
			wcf.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.GREY_25_PERCENT);
			wcf.setAlignment(align); // 设置对齐方式 平行显示方式
			wcf.setVerticalAlignment(verticalAlignment);// 水平显示方式
			// wcf.setWrap(true);// 自动换行
		} catch (WriteException e) {
			logger.error(" Export Excel File", e.getMessage());
		}
		return wcf;
	}
	
	/**
	 * 设置单元格
	 * @param fn
	 * @param fontSize
	 * @param fontColour
	 * @param align
	 * @param background
	 * @param verticalAlignment
	 * @param format
	 * @return
	 */
	public static WritableCellFormat cellFormat(FontName fn, int fontSize,
			Colour fontColour, jxl.format.Alignment align, Colour background,
			 VerticalAlignment verticalAlignment, DisplayFormat format) {
		WritableFont wf = new WritableFont(fn, fontSize, WritableFont.NO_BOLD,
				false, UnderlineStyle.NO_UNDERLINE, fontColour);// 定义格式 字体 下划线 斜体 粗体 颜色
		WritableCellFormat wcf = new WritableCellFormat(wf, format); // 单元格定义
		try {
			wcf.setBackground(null == background ? jxl.format.Colour.WHITE : background); // 设置单元格的背景颜色
			wcf.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.GREY_25_PERCENT);
			wcf.setAlignment(align); // 设置对齐方式 平行显示方式
			wcf.setVerticalAlignment(verticalAlignment);// 水平显示方式
			// wcf.setWrap(true);// 自动换行
		} catch (WriteException e) {
			logger.error(" Export Excel File", e.getMessage());
		}
		return wcf;
	}
	
	/**
	 * 设置单元格
	 * @param fn 字体
	 * @param fontSize 字号
	 * @param fontColour 字体颜色
	 * @param align 字体居中方式
	 * @param background 单元格背景
	 * @param topColour 单元格上边线颜色
	 * @param rigthColour单元格右边线颜色
	 * @param bottomColour单元格下边线颜色
	 * @param leftColour单元格左边线颜色
	 * @param verticalAlignment 水平格式 
	 * @return
	 */
	public static WritableCellFormat cellFormat(FontName fn, int fontSize,
			Colour fontColour, jxl.format.Alignment align, Colour background,
			Colour topColour, Colour rigthColour, Colour bottomColour,
			Colour leftColour, VerticalAlignment verticalAlignment) {
		WritableFont wf = new WritableFont(fn, fontSize, WritableFont.NO_BOLD,
				false, UnderlineStyle.NO_UNDERLINE, fontColour);// 定义格式 字体 下划线 斜体 粗体 颜色
		WritableCellFormat wcf = new WritableCellFormat(wf); // 单元格定义
		try {
			wcf.setBackground(null == background ? jxl.format.Colour.WHITE : background); // 设置单元格的背景颜色
			wcf.setBorder(Border.TOP, BorderLineStyle.THIN, topColour); // 表格线
			wcf.setBorder(Border.RIGHT, BorderLineStyle.THIN, rigthColour); // 表格线
			wcf.setBorder(Border.BOTTOM, BorderLineStyle.THIN, bottomColour); // 表格线
			wcf.setBorder(Border.LEFT, BorderLineStyle.THIN, leftColour); // 表格线
			wcf.setAlignment(align); // 设置对齐方式 平行显示方式
			wcf.setVerticalAlignment(verticalAlignment);// 水平显示方式
			// wcf.setWrap(true);// 自动换行
		} catch (WriteException e) {
			logger.error(" Export Excel File", e.getMessage());
			e.printStackTrace();
		}
		return wcf;
	}
	
	
	/**
	 * 设置单元格
	 * @param fn
	 * @param fontSize
	 * @param fontColour
	 * @param align
	 * @param background
	 * @param topColour
	 * @param rigthColour
	 * @param bottomColour
	 * @param leftColour
	 * @param topBorderLineStyle
	 * @param rigthBorderLineStyle
	 * @param bottomBorderLineStyle
	 * @param leftBorderLineStyle
	 * @param verticalAlignment
	 * @return
	 */
	public static WritableCellFormat cellFormat(FontName fn, int fontSize,
			Colour fontColour, jxl.format.Alignment align, Colour background, Colour topColour, Colour rigthColour, Colour bottomColour,
			Colour leftColour,
			BorderLineStyle topBorderLineStyle, BorderLineStyle rigthBorderLineStyle, BorderLineStyle bottomBorderLineStyle,
			BorderLineStyle leftBorderLineStyle, VerticalAlignment verticalAlignment) {
		WritableFont wf = new WritableFont(fn, fontSize, WritableFont.NO_BOLD,
				false, UnderlineStyle.NO_UNDERLINE, fontColour);// 定义格式 字体 下划线 斜体 粗体 颜色
		WritableCellFormat wcf = new WritableCellFormat(wf); // 单元格定义
		try {
			wcf.setBackground(null == background ? jxl.format.Colour.WHITE : background); // 设置单元格的背景颜色
			wcf.setBorder(Border.TOP, topBorderLineStyle, topColour); // 表格线
			wcf.setBorder(Border.RIGHT, rigthBorderLineStyle, rigthColour); // 表格线
			wcf.setBorder(Border.BOTTOM, bottomBorderLineStyle, bottomColour); // 表格线
			wcf.setBorder(Border.LEFT, leftBorderLineStyle, leftColour); // 表格线
			wcf.setAlignment(align); // 设置对齐方式 平行显示方式
			wcf.setVerticalAlignment(verticalAlignment);// 水平显示方式
//			wcf.setWrap(true);// 自动换行
		} catch (WriteException e) {
			logger.error(" Export Excel File", e.getMessage());
		}
		return wcf;
	}
	
	/**
	 * 设置单元格
	 * @param fn
	 * @param fontSize
	 * @param fontColour
	 * @param align
	 * @param background
	 * @param topColour
	 * @param rigthColour
	 * @param bottomColour
	 * @param leftColour
	 * @param topBorderLineStyle
	 * @param rigthBorderLineStyle
	 * @param bottomBorderLineStyle
	 * @param leftBorderLineStyle
	 * @param verticalAlignment
	 * @return
	 */
	public static WritableCellFormat cellFormat(FontName fn, int fontSize,
			Colour fontColour, jxl.format.Alignment align, Colour background, Colour topColour, Colour rigthColour, Colour bottomColour,
			Colour leftColour,
			BorderLineStyle topBorderLineStyle, BorderLineStyle rigthBorderLineStyle, BorderLineStyle bottomBorderLineStyle,
			BorderLineStyle leftBorderLineStyle, VerticalAlignment verticalAlignment, boolean wrap) {
		WritableFont wf = new WritableFont(fn, fontSize, WritableFont.NO_BOLD,
				false, UnderlineStyle.NO_UNDERLINE, fontColour);// 定义格式 字体 下划线 斜体 粗体 颜色
		WritableCellFormat wcf = new WritableCellFormat(wf); // 单元格定义
		try {
			wcf.setBackground(null == background ? jxl.format.Colour.WHITE : background); // 设置单元格的背景颜色
			wcf.setBorder(Border.TOP, topBorderLineStyle, topColour); // 表格线
			wcf.setBorder(Border.RIGHT, rigthBorderLineStyle, rigthColour); // 表格线
			wcf.setBorder(Border.BOTTOM, bottomBorderLineStyle, bottomColour); // 表格线
			wcf.setBorder(Border.LEFT, leftBorderLineStyle, leftColour); // 表格线
			wcf.setAlignment(align); // 设置对齐方式 平行显示方式
			wcf.setVerticalAlignment(verticalAlignment);// 水平显示方式
			wcf.setWrap(wrap);// 自动换行
		} catch (WriteException e) {
			logger.error(" Export Excel File", e.getMessage());
		}
		return wcf;
	}
	
	/**
	 * 设置单元格
	 * @param fontSize
	 * @param fontColour
	 * @param align
	 * @param background
	 * @param topColour
	 * @param rigthColour
	 * @param bottomColour
	 * @param leftColour
	 * @param topBorderLineStyle
	 * @param rigthBorderLineStyle
	 * @param bottomBorderLineStyle
	 * @param leftBorderLineStyle
	 * @param verticalAlignment
	 * @return
	 */
	public static WritableCellFormat cellFormat(int fontSize,
			Colour fontColour, jxl.format.Alignment align, Colour background, Colour topColour, Colour rigthColour, Colour bottomColour,
			Colour leftColour,
			BorderLineStyle topBorderLineStyle, BorderLineStyle rigthBorderLineStyle, BorderLineStyle bottomBorderLineStyle,
			BorderLineStyle leftBorderLineStyle, VerticalAlignment verticalAlignment) {
		return cellFormat(WritableFont.createFont("華康新儷粗黑"), fontSize, fontColour, align, background, topColour, rigthColour, bottomColour, leftColour, topBorderLineStyle, rigthBorderLineStyle, bottomBorderLineStyle, leftBorderLineStyle, verticalAlignment);
	}
	
	
	/**
	 * 设置单元格
	 * @param fontSize
	 * @param fontColour
	 * @param align
	 * @param background
	 * @param topColour
	 * @param rigthColour
	 * @param bottomColour
	 * @param leftColour
	 * @param topBorderLineStyle
	 * @param rigthBorderLineStyle
	 * @param bottomBorderLineStyle
	 * @param leftBorderLineStyle
	 * @param verticalAlignment
	 * @param wrap
	 * @return
	 */
	public static WritableCellFormat cellFormat(int fontSize,
			Colour fontColour, jxl.format.Alignment align, Colour background, Colour topColour, Colour rigthColour, Colour bottomColour,
			Colour leftColour,
			BorderLineStyle topBorderLineStyle, BorderLineStyle rigthBorderLineStyle, BorderLineStyle bottomBorderLineStyle,
			BorderLineStyle leftBorderLineStyle, VerticalAlignment verticalAlignment, boolean wrap) {
		return cellFormat(WritableFont.createFont("華康新儷粗黑"), fontSize, fontColour, align, background, topColour, rigthColour, bottomColour, leftColour, topBorderLineStyle, rigthBorderLineStyle, bottomBorderLineStyle, leftBorderLineStyle, verticalAlignment, wrap);
	}
	
	/**
	 * 设置单元格
	 * @param fn
	 * @param fontSize
	 * @param fontColour
	 * @param align
	 * @param background
	 * @param topColour
	 * @param rigthColour
	 * @param bottomColour
	 * @param leftColour
	 * @param topBorderLineStyle
	 * @param rigthBorderLineStyle
	 * @param bottomBorderLineStyle
	 * @param leftBorderLineStyle
	 * @param verticalAlignment
	 * @return
	 */
	public static WritableCellFormat cellFormat(String fn, int fontSize,
			Colour fontColour, jxl.format.Alignment align, Colour background, Colour topColour, Colour rigthColour, Colour bottomColour,
			Colour leftColour,
			BorderLineStyle topBorderLineStyle, BorderLineStyle rigthBorderLineStyle, BorderLineStyle bottomBorderLineStyle,
			BorderLineStyle leftBorderLineStyle, VerticalAlignment verticalAlignment) {
		return cellFormat(WritableFont.createFont(fn), fontSize, fontColour, align, background, topColour, rigthColour, bottomColour, leftColour, topBorderLineStyle, rigthBorderLineStyle, bottomBorderLineStyle, leftBorderLineStyle, verticalAlignment);
	}
	
	/**
	 * 设置单元格
	 * @param fontSize
	 * @param fontColour
	 * @param align
	 * @param background
	 * @param borderColour
	 * @param topBorderLineStyle
	 * @param rigthBorderLineStyle
	 * @param bottomBorderLineStyle
	 * @param leftBorderLineStyle
	 * @param verticalAlignment
	 * @return
	 */
	public static WritableCellFormat cellFormat(int fontSize,
			Colour fontColour, jxl.format.Alignment align, Colour background, Colour borderColour,
			BorderLineStyle topBorderLineStyle, BorderLineStyle rigthBorderLineStyle, BorderLineStyle bottomBorderLineStyle,
			BorderLineStyle leftBorderLineStyle, VerticalAlignment verticalAlignment) {
		return cellFormat(WritableFont.createFont("華康新儷粗黑"), fontSize, fontColour, align, background, borderColour, borderColour, borderColour, borderColour, topBorderLineStyle, rigthBorderLineStyle, bottomBorderLineStyle, leftBorderLineStyle, verticalAlignment);
	}
	
	/**
	 * 设置单元格
	 * @param fontSize
	 * @param fontColour
	 * @param align
	 * @param background
	 * @param borderColour
	 * @param topBorderLineStyle
	 * @param rigthBorderLineStyle
	 * @param bottomBorderLineStyle
	 * @param leftBorderLineStyle
	 * @param verticalAlignment
	 * @return
	 */
	public static WritableCellFormat cellFormat(String fn,int fontSize,
			Colour fontColour, jxl.format.Alignment align, Colour background, Colour borderColour,
			BorderLineStyle topBorderLineStyle, BorderLineStyle rigthBorderLineStyle, BorderLineStyle bottomBorderLineStyle,
			BorderLineStyle leftBorderLineStyle, VerticalAlignment verticalAlignment) {
		return cellFormat(WritableFont.createFont(fn), fontSize, fontColour, align, background, borderColour, borderColour, borderColour, borderColour, topBorderLineStyle, rigthBorderLineStyle, bottomBorderLineStyle, leftBorderLineStyle, verticalAlignment);
	}
	
	/**
	 * 设置单元格
	 * @param fn
	 * @param fontSize
	 * @param fontColour
	 * @param align
	 * @param background
	 * @param borderColour
	 * @param topBorderLineStyle
	 * @param rigthBorderLineStyle
	 * @param bottomBorderLineStyle
	 * @param leftBorderLineStyle
	 * @param verticalAlignment
	 * @param format
	 * @return
	 */
	public static WritableCellFormat cellFormat(String fn,int fontSize,
			Colour fontColour, jxl.format.Alignment align, Colour background, Colour borderColour,
			BorderLineStyle topBorderLineStyle, BorderLineStyle rigthBorderLineStyle, BorderLineStyle bottomBorderLineStyle,
			BorderLineStyle leftBorderLineStyle, VerticalAlignment verticalAlignment, DisplayFormat format) {
		return cellFormat(WritableFont.createFont(fn), fontSize, fontColour, align, background, borderColour, borderColour, borderColour, borderColour, topBorderLineStyle, rigthBorderLineStyle, bottomBorderLineStyle, leftBorderLineStyle, verticalAlignment, format);
	}
	
	/**
	 * 设置单元格
	 * @param fontSize
	 * @param fontColour
	 * @param align
	 * @param background
	 * @param borderColour
	 * @param topBorderLineStyle
	 * @param rigthBorderLineStyle
	 * @param bottomBorderLineStyle
	 * @param leftBorderLineStyle
	 * @param verticalAlignment
	 * @return
	 */
	public static WritableCellFormat cellFormat(String fn,int fontSize,
			Colour fontColour, jxl.format.Alignment align, Colour background, Colour borderColour,
			BorderLineStyle topBorderLineStyle, BorderLineStyle rigthBorderLineStyle, BorderLineStyle bottomBorderLineStyle,
			BorderLineStyle leftBorderLineStyle, VerticalAlignment verticalAlignment, boolean wrap) {
		return cellFormat(WritableFont.createFont(fn), fontSize, fontColour, align, background, borderColour, borderColour, borderColour, borderColour, topBorderLineStyle, rigthBorderLineStyle, bottomBorderLineStyle, leftBorderLineStyle, verticalAlignment, wrap);
	}
	
	/**
	 * 设置单元格
	 * @param fontSize
	 * @param fontColour
	 * @param align
	 * @param background
	 * @param borderColour
	 * @param topBorderLineStyle
	 * @param rigthBorderLineStyle
	 * @param bottomBorderLineStyle
	 * @param leftBorderLineStyle
	 * @param verticalAlignment
	 * @return
	 */
	public static WritableCellFormat cellFormat(int fontSize,
			Colour fontColour, jxl.format.Alignment align, Colour background, Colour borderColour,
			BorderLineStyle topBorderLineStyle, BorderLineStyle rigthBorderLineStyle, BorderLineStyle bottomBorderLineStyle,
			BorderLineStyle leftBorderLineStyle, VerticalAlignment verticalAlignment, DisplayFormat format) {
		return cellFormat(WritableFont.createFont("華康新儷粗黑"), fontSize, fontColour, align, background, borderColour, borderColour, borderColour, borderColour, topBorderLineStyle, rigthBorderLineStyle, bottomBorderLineStyle, leftBorderLineStyle, verticalAlignment, format);
	}
	
	/**
	 * 设置单元格
	 * @param fontSize
	 * @param fontColour
	 * @param align
	 * @param background
	 * @param borderColour
	 * @param topBorderLineStyle
	 * @param rigthBorderLineStyle
	 * @param bottomBorderLineStyle
	 * @param leftBorderLineStyle
	 * @param verticalAlignment
	 * @return
	 */
	public static WritableCellFormat cellFormat(int fontSize,
			Colour fontColour, jxl.format.Alignment align, Colour background, Colour borderColour,
			BorderLineStyle topBorderLineStyle, BorderLineStyle rigthBorderLineStyle, BorderLineStyle bottomBorderLineStyle,
			BorderLineStyle leftBorderLineStyle, VerticalAlignment verticalAlignment, boolean wrap) {
		return cellFormat(WritableFont.createFont("華康新儷粗黑"), fontSize, fontColour, align, background, borderColour, borderColour, borderColour, borderColour, topBorderLineStyle, rigthBorderLineStyle, bottomBorderLineStyle, leftBorderLineStyle, verticalAlignment, wrap);
	}
	
	/**
	 * 设置单元格
	 * @param fontSize
	 * @param fontColour
	 * @param align
	 * @param background
	 * @param borderColour
	 * @param topBorderLineStyle
	 * @param rigthBorderLineStyle
	 * @param bottomBorderLineStyle
	 * @param leftBorderLineStyle
	 * @param verticalAlignment
	 * @return
	 */
	public static WritableCellFormat cellFormat(int fontSize,
			Colour fontColour, jxl.format.Alignment align, Colour background, Colour borderColour,
			BorderLineStyle borderLineStyle, VerticalAlignment verticalAlignment) {
		return cellFormat(WritableFont.createFont("華康新儷粗黑"), fontSize, fontColour, align, background, borderColour, borderColour, borderColour, borderColour, borderLineStyle, borderLineStyle, borderLineStyle, borderLineStyle, verticalAlignment);
	}
	
	/**
	 * 设置单元格
	 * @param fontSize
	 * @param fontColour
	 * @param align
	 * @param background
	 * @param borderColour
	 * @param topBorderLineStyle
	 * @param rigthBorderLineStyle
	 * @param bottomBorderLineStyle
	 * @param leftBorderLineStyle
	 * @param verticalAlignment
	 * @return
	 */
	public static WritableCellFormat cellFormat(FontName fn, int fontSize,
			Colour fontColour, jxl.format.Alignment align, Colour background, Colour borderColour,
			BorderLineStyle borderLineStyle, VerticalAlignment verticalAlignment) {
		return cellFormat(WritableFont.createFont("華康新儷粗黑"), fontSize, fontColour, align, background, borderColour, borderColour, borderColour, borderColour, borderLineStyle, borderLineStyle, borderLineStyle, borderLineStyle, verticalAlignment);
	}
	
	/**
	 * 设置单元格
	 * @param fontSize
	 * @param fontColour
	 * @param align
	 * @param background
	 * @param borderColour
	 * @param topBorderLineStyle
	 * @param rigthBorderLineStyle
	 * @param bottomBorderLineStyle
	 * @param leftBorderLineStyle
	 * @param verticalAlignment
	 * @return
	 */
	public static WritableCellFormat cellFormat(String fn, int fontSize,
			Colour fontColour, jxl.format.Alignment align, Colour background, Colour borderColour,
			BorderLineStyle borderLineStyle, VerticalAlignment verticalAlignment) {
		return cellFormat(WritableFont.createFont(fn), fontSize, fontColour, align, background, borderColour, borderColour, borderColour, borderColour, borderLineStyle, borderLineStyle, borderLineStyle, borderLineStyle, verticalAlignment);
	}
	
	
	/**
	 * 设置单元格
	 * @param fn
	 * @param fontSize
	 * @param fontColour
	 * @param align
	 * @param background
	 * @param topColour
	 * @param rigthColour
	 * @param bottomColour
	 * @param leftColour
	 * @param topBorderLineStyle
	 * @param rigthBorderLineStyle
	 * @param bottomBorderLineStyle
	 * @param leftBorderLineStyle
	 * @param verticalAlignment
	 * @return
	 */
	public static WritableCellFormat cellFormat(FontName fn, int fontSize,
			Colour fontColour, jxl.format.Alignment align, Colour background, Colour topColour, Colour rigthColour, Colour bottomColour,
			Colour leftColour,
			BorderLineStyle topBorderLineStyle, BorderLineStyle rigthBorderLineStyle, BorderLineStyle bottomBorderLineStyle,
			BorderLineStyle leftBorderLineStyle, VerticalAlignment verticalAlignment, DisplayFormat format) {
		WritableFont wf = new WritableFont(fn, fontSize, WritableFont.NO_BOLD,
				false, UnderlineStyle.NO_UNDERLINE, fontColour);// 定义格式 字体 下划线 斜体 粗体 颜色
		WritableCellFormat wcf = new WritableCellFormat(wf, format); // 单元格定义
		try {
			wcf.setBackground(null == background ? jxl.format.Colour.WHITE : background); // 设置单元格的背景颜色
			wcf.setBorder(Border.TOP, topBorderLineStyle, topColour); // 表格线
			wcf.setBorder(Border.RIGHT, rigthBorderLineStyle, rigthColour); // 表格线
			wcf.setBorder(Border.BOTTOM, bottomBorderLineStyle, bottomColour); // 表格线
			wcf.setBorder(Border.LEFT, leftBorderLineStyle, leftColour); // 表格线
			wcf.setAlignment(align); // 设置对齐方式 平行显示方式
			wcf.setVerticalAlignment(verticalAlignment);// 水平显示方式
			// wcf.setWrap(true);// 自动换行
		} catch (WriteException e) {
			logger.error(" Export Excel File", e.getMessage());
		}
		return wcf;
	}
	
	
	/**
	 * 设置单元格
	 * @param fn
	 * @param fontSize
	 * @param fontColour
	 * @param align
	 * @param background
	 * @param topColour
	 * @param rigthColour
	 * @param bottomColour
	 * @param leftColour
	 * @param topBorderLineStyle
	 * @param rigthBorderLineStyle
	 * @param bottomBorderLineStyle
	 * @param leftBorderLineStyle
	 * @param verticalAlignment
	 * @param numFormat
	 * @return
	 */
	public static WritableCellFormat cellFormat(String fn, int fontSize,
			Colour fontColour, jxl.format.Alignment align, Colour background, Colour topColour, Colour rigthColour, Colour bottomColour,
			Colour leftColour,
			BorderLineStyle topBorderLineStyle, BorderLineStyle rigthBorderLineStyle, BorderLineStyle bottomBorderLineStyle,
			BorderLineStyle leftBorderLineStyle, VerticalAlignment verticalAlignment, DisplayFormat format) {
		WritableFont wf = new WritableFont(WritableFont.createFont(fn), fontSize, WritableFont.NO_BOLD,
				false, UnderlineStyle.NO_UNDERLINE, fontColour);// 定义格式 字体 下划线 斜体 粗体 颜色
		WritableCellFormat wcf = new WritableCellFormat(wf, format);
		try {
			wcf.setBackground(null == background ? jxl.format.Colour.WHITE : background); // 设置单元格的背景颜色
			wcf.setBorder(Border.TOP, topBorderLineStyle, topColour); // 表格线
			wcf.setBorder(Border.RIGHT, rigthBorderLineStyle, rigthColour); // 表格线
			wcf.setBorder(Border.BOTTOM, bottomBorderLineStyle, bottomColour); // 表格线
			wcf.setBorder(Border.LEFT, leftBorderLineStyle, leftColour); // 表格线
			wcf.setAlignment(align); // 设置对齐方式 平行显示方式
			wcf.setVerticalAlignment(verticalAlignment);// 水平显示方式
			// wcf.setWrap(true);// 自动换行
		} catch (WriteException e) {
			logger.error(" Export Excel File", e.getMessage());
			e.printStackTrace();
		}
		return wcf;
	}
	
	/**
	 * 设置单元格
	 * @param fn
	 * @param fontSize
	 * @param topBottomBorderColour
	 * @param rigthLeftBorderColour
	 * @param topBottomBorderLineStyle
	 * @param rigthLeftBorderLineStyle
	 * @param verticalAlignment
	 * @return
	 */
	public static WritableCellFormat cellFormat(String fn, int fontSize, Colour topBottomBorderColour, Colour rigthLeftBorderColour,
			BorderLineStyle topBottomBorderLineStyle, BorderLineStyle rigthLeftBorderLineStyle, VerticalAlignment verticalAlignment) {
		return cellFormat(WritableFont.createFont(fn), fontSize,
				Colour.BLACK, Alignment.CENTRE, Colour.WHITE, topBottomBorderColour, rigthLeftBorderColour, topBottomBorderColour,
				rigthLeftBorderColour,
				topBottomBorderLineStyle, rigthLeftBorderLineStyle, topBottomBorderLineStyle, rigthLeftBorderLineStyle, VerticalAlignment.CENTRE); 
	}
	
	/**
	 * 设置单元格s
	 * @param fn
	 * @param fontSize
	 * @param borderColour
	 * @param topBottomLeftBorderLineStyle
	 * @param rigthBorderLineStyle
	 * @return
	 */
	public static WritableCellFormat cellFormat(String fn, int fontSize, Colour borderColour, 
			BorderLineStyle topBottomLeftBorderLineStyle, BorderLineStyle rigthBorderLineStyle) {
		return cellFormat(WritableFont.createFont(fn), fontSize,
				Colour.BLACK, Alignment.CENTRE, Colour.WHITE, borderColour, borderColour, borderColour,
				borderColour,
				topBottomLeftBorderLineStyle, rigthBorderLineStyle, topBottomLeftBorderLineStyle, topBottomLeftBorderLineStyle, VerticalAlignment.CENTRE); 
	}
	
	/**
	 * 设置单元格
	 * @param fn
	 * @param fontSize
	 * @param borderColour
	 * @param topBottomLeftBorderLineStyle
	 * @param rigthBorderLineStyle
	 * @return
	 */
	public static WritableCellFormat cellFormat(int fontSize, Colour borderColour, 
			BorderLineStyle topBottomLeftBorderLineStyle, BorderLineStyle rigthBorderLineStyle) {
		return cellFormat("華康新儷粗黑", fontSize, borderColour, topBottomLeftBorderLineStyle, rigthBorderLineStyle);
	}
	
	/**
	 * 设置单元格
	 * @param fn
	 * @param fontSize
	 * @param background
	 * @param borderColour
	 * @param topBottomLeftBorderLineStyle
	 * @param rigthBorderLineStyle
	 * @return
	 */
	public static WritableCellFormat cellFormat(String fn, int fontSize, Colour background, Colour borderColour, 
			BorderLineStyle topBottomLeftBorderLineStyle, BorderLineStyle rigthBorderLineStyle) {
		return cellFormat(WritableFont.createFont(fn), fontSize,
				Colour.BLACK, Alignment.CENTRE, background, borderColour, borderColour, borderColour,
				borderColour,
				topBottomLeftBorderLineStyle, rigthBorderLineStyle, topBottomLeftBorderLineStyle, topBottomLeftBorderLineStyle, VerticalAlignment.CENTRE); 
	}
	
	/**
	 * 设置单元格s
	 * @param fn
	 * @param fontSize
	 * @param topRigthBottomBorderLineStyle
	 * @param borderColour
	 * @param LeftBorderLineStyle
	 * @return
	 */
	public static WritableCellFormat cellFormat(String fn, int fontSize, 
			BorderLineStyle topRigthBottomBorderLineStyle, Colour borderColour, BorderLineStyle LeftBorderLineStyle) {
		return cellFormat(WritableFont.createFont(fn), fontSize,
				Colour.BLACK, Alignment.CENTRE, Colour.WHITE, borderColour, borderColour, borderColour,
				borderColour,
				topRigthBottomBorderLineStyle, topRigthBottomBorderLineStyle, topRigthBottomBorderLineStyle, LeftBorderLineStyle, VerticalAlignment.CENTRE); 
	}
	
	/**
	 * 设置单元格
	 * @param fontSize
	 * @param topRigthBottomBorderLineStyle
	 * @param borderColour
	 * @param LeftBorderLineStyle
	 * @return
	 */
	public static WritableCellFormat cellFormat(int fontSize, 
			BorderLineStyle topRigthBottomBorderLineStyle, Colour borderColour, BorderLineStyle LeftBorderLineStyle) {
		return cellFormat("華康新儷粗黑", fontSize, topRigthBottomBorderLineStyle, borderColour, LeftBorderLineStyle);
	}
	
	/**
	 * 设置单元格
	 * @param fn
	 * @param fontSize
	 * @param topBottomBorderLineStyle
	 * @param rigthLeftBorderLineStyle
	 * @param borderColour
	 * @return
	 */
	public static WritableCellFormat cellFormat(String fn, int fontSize, 
			BorderLineStyle topBottomBorderLineStyle, BorderLineStyle rigthLeftBorderLineStyle, Colour borderColour) {
		return cellFormat(WritableFont.createFont(fn), fontSize,
				Colour.BLACK, Alignment.CENTRE, Colour.WHITE, borderColour, borderColour, borderColour,
				borderColour,
				topBottomBorderLineStyle, rigthLeftBorderLineStyle, topBottomBorderLineStyle, rigthLeftBorderLineStyle, VerticalAlignment.CENTRE); 
	}
	
	/**
	 * 设置单元格
	 * @param fontSize
	 * @param topBottomBorderLineStyle
	 * @param rigthLeftBorderLineStyle
	 * @param borderColour
	 * @return
	 */
	public static WritableCellFormat cellFormat(int fontSize, 
			BorderLineStyle topBottomBorderLineStyle, BorderLineStyle rigthLeftBorderLineStyle, Colour borderColour) {
		return cellFormat("華康新儷粗黑", fontSize, topBottomBorderLineStyle, rigthLeftBorderLineStyle, borderColour);
	}

	/**
	 * 设置单元格 
	 * @param fn 字体
	 * @param fontSize 字号
	 * @param align 对齐方式
	 * @param background 背景
	 * @param borderColour  单元格边线颜色
	 * @return
	 */
	public static WritableCellFormat cellFormat(FontName fn, int fontSize,
			jxl.format.Alignment align, Colour background, Colour borderColour, VerticalAlignment verticalAlignment) {
		return cellFormat(fn, fontSize, Colour.BLACK, align, background,
				borderColour, borderColour, borderColour, borderColour, verticalAlignment);
	}

	/**
	 * 设置单元格
	 * @param fn 字体
	 * @param fontSize 字号
	 * @param align 对齐方式
	 * @param background 背景
	 * @param borderColour 单元格边线颜色
	 * @return
	 */
	public static WritableCellFormat cellFormat(FontName fn, int fontSize,
			jxl.format.Alignment align, VerticalAlignment verticalAlignment) {
		return cellFormat(fn, fontSize, align, Colour.WHITE, Colour.BLACK, verticalAlignment);
	}

	/**
	 * 设置单元格
	 * 
	 * @param fn 字体
	 * @param fontSize字号
	 * @return
	 */
	public static WritableCellFormat cellFormat(String fn, int fontSize, VerticalAlignment verticalAlignment) {
		return cellFormat(WritableFont.createFont(fn), fontSize,
				Alignment.CENTRE, Colour.WHITE, Colour.BLACK, verticalAlignment);
	}

	/**
	 * 设置单元格
	 * @param fn 字体
	 * @return
	 */
	public static WritableCellFormat cellFormat(String fn) {
		return cellFormat(WritableFont.createFont(fn), 10, Alignment.CENTRE,
				Colour.WHITE, Colour.BLACK,VerticalAlignment.CENTRE);
	}

	/**
	 * 设置单元格
	 * @param fontSize字号
	 * @return
	 */
	public static WritableCellFormat cellFormat(int fontSize) {
		return cellFormat(WritableFont.createFont("華康新儷粗黑"), fontSize,
				Alignment.CENTRE, Colour.WHITE, Colour.BLACK, VerticalAlignment.CENTRE);
	}
	
	/**
	 * 设置单元格
	 * @param fontSize字号
	 * @return
	 */
	public static WritableCellFormat cellFormat(int fontSize, VerticalAlignment verticalAlignment) {
		return cellFormat(WritableFont.createFont("華康新儷粗黑"), fontSize,
				Alignment.CENTRE, Colour.WHITE, Colour.BLACK, verticalAlignment);
	}

	/**
	 * 设置单元格
	 * @return
	 */
	public static WritableCellFormat cellFormat() {
		return cellFormat(WritableFont.createFont("華康新儷粗黑"), 10,
				Alignment.CENTRE, Colour.WHITE, Colour.BLACK, VerticalAlignment.CENTRE);
	}
	
	/**
	 * 设置单元格
	 * @param fn 字体
	 * @param fontSize 字号
	 * @param borderColour 边格线颜色
	 * @param align 左中右格式
	 * @param verticalAlignment 水平格式
	 * @return
	 */
	public static WritableCellFormat cellFormat(String fn, int fontSize,  Colour borderColour,jxl.format.Alignment align, VerticalAlignment verticalAlignment) {
		return cellFormat(WritableFont.createFont(fn), fontSize, Colour.BLACK, align, Colour.WHITE, borderColour, borderColour, borderColour, borderColour, verticalAlignment);
	}
	
	/**
	 * 设置单元格
	 * @param fontSize 字号
	 * @param fontColour 字体颜色
	 * @param align 字体居中方式
	 * @param background 单元格背景
	 * @param borderColour 单元格边线颜色
	 * @return
	 */
	public static WritableCellFormat cellFormat(int fontSize, Colour fontColour, jxl.format.Alignment align, Colour background, Colour borderColour, VerticalAlignment verticalAlignment) {
		return cellFormat(WritableFont.createFont("華康新儷粗黑"), fontSize, fontColour, align, background, borderColour, borderColour, borderColour, borderColour, verticalAlignment);
	}
	
	/**
	 * 设置单元格
	 * @param fn 字体
	 * @param fontSize 字号
	 * @param fontColour 字体颜色
	 * @param align 字体居中方式
	 * @param background 单元格背景
	 * @param borderColour 单元格边线颜色
	 * @return
	 */
	public static WritableCellFormat cellFormat(String fn, int fontSize, Colour fontColour, jxl.format.Alignment align, Colour background, Colour borderColour, VerticalAlignment verticalAlignment) {
		return cellFormat(WritableFont.createFont(fn), fontSize, fontColour, align, background, borderColour, borderColour, borderColour, borderColour, verticalAlignment);
	}
	
	
	/**
	 * 设置单元格 
	 * @param fontSize 字号
	 * @param align 对齐方式
	 * @param background 背景
	 * @param borderColour  单元格边线颜色
	 * @return
	 */
	public static WritableCellFormat cellFormat(int fontSize,Colour fontColour,
			jxl.format.Alignment align, Colour borderColour, VerticalAlignment verticalAlignment) {
		return cellFormat(fontSize, fontColour, align, Colour.WHITE, borderColour, verticalAlignment);
	}
	
	/**
	 * 设置单元格 
	 * @param fontSize 字号
	 * @param align 对齐方式
	 * @param background 背景
	 * @param borderColour  单元格边线颜色
	 * @return
	 */
	public static WritableCellFormat cellFormat(String fn, int fontSize,Colour fontColour,
			jxl.format.Alignment align, Colour borderColour, VerticalAlignment verticalAlignment) {
		return cellFormat(fontSize, fontColour, align, Colour.WHITE, borderColour, verticalAlignment);
	}
	
	
	/**
	 * 设置单元格 
	 * @param fontSize 字号
	 * @param fontColour
	 * @param align 对齐方式
	 * @param borderColour  单元格边线颜色
	 * @param numFormat
	 * @param verticalAlignment 
	 * @return
	 */
	public static WritableCellFormat cellFormat(int fontSize,Colour fontColour,
			jxl.format.Alignment align, Colour borderColour, VerticalAlignment verticalAlignment, DisplayFormat format) {
		return cellFormat("華康新儷粗黑", fontSize, fontColour, align, Colour.WHITE, borderColour, borderColour, borderColour, borderColour, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, verticalAlignment, format);
	}
	
	/**
	 * 设置单元格 
	 * @param fontSize 字号
	 * @param fontColour
	 * @param align 对齐方式
	 * @param background 背景
	 * @param borderColour  单元格边线颜色
	 * @param numFormat
	 * @param verticalAlignment 
	 * @return
	 */
	public static WritableCellFormat cellFormat(int fontSize,Colour fontColour, Colour background,
			jxl.format.Alignment align, Colour borderColour, VerticalAlignment verticalAlignment, DisplayFormat format) {
		return cellFormat("華康新儷粗黑", fontSize, fontColour, align, background, borderColour, borderColour, borderColour, borderColour, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, BorderLineStyle.THIN, verticalAlignment, format);
	}


}
