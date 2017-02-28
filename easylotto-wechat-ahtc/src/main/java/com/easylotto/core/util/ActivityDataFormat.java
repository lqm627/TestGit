/**
 * 
 */
package com.easylotto.core.util;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.easylotto.commons.util.DateTimeUtil;
import com.easylotto.core.entity.ActivityLog;
import com.easylotto.core.export.excel.IExcel;

/**
 *
 * @author chenrixiang
 * @version 1.0
 * @create_date 2015年7月10日 上午9:32:02
 *
 */
public class ActivityDataFormat extends IExcel{
	
	
	public static String toString(String obj){
		if(StringUtils.isNotBlank(obj)) return obj;
		return "";
	}
	
	public static void export(HttpServletRequest request, HttpServletResponse response, List<ActivityLog> list){
		IExcel excel = new IExcel();
		OutputStream os = null;
		try {
			response.setCharacterEncoding("UTF-8");
			String excelName = "安徽福彩微信\"走近双色球 免费游北京\"活动"+DateTimeUtil.toString(DateTimeUtil.YYYYMMDDHHMM);
			// 设置response方式,使执行此controller时候自动出现下载页面,而非直接使用excel打开
			response.setContentType("APPLICATION/OCTET-STREAM");
			response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(excelName+".xls", "UTF-8"));
			os = response.getOutputStream();
			// 创建工作薄 // 创建第一个工作表
			excel.create(os);
			ActivityExcel.export(excel, list);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 写入文件
			try {
				excel.writeAndClose();
				os.flush();
				os.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void export(HttpServletRequest request, HttpServletResponse response, List<ActivityLog> list, String sign){
		IExcel excel = new IExcel();
		OutputStream os = null;
		try {
			response.setCharacterEncoding("UTF-8");
			String excelName = "快三加奖_07月18日-08月06日_"+DateTimeUtil.toString(DateTimeUtil.YYYYMMDDHHMM);
			if("2".equals(sign)){
				excelName = "红蓝宝石_7月16日-8月15日_"+DateTimeUtil.toString(DateTimeUtil.YYYYMMDDHHMM);
			}else if("6".equals(sign)){
				excelName = "关注福彩微信号答题北京看开奖_9月10日-9月25日_"+DateTimeUtil.toString(DateTimeUtil.YYYYMMDDHHMM);
			}else if("29".equals(sign)){
				excelName = "作品投票_11月08日"+DateTimeUtil.toString(DateTimeUtil.YYYYMMDDHHMM);
			}else if("30".equals(sign)){
				excelName = "体彩春联活动_11月25日-12月15日"+DateTimeUtil.toString(DateTimeUtil.YYYYMMDDHHMM);
			}else if("31".equals(sign)){
				excelName = "体彩晒票有奖活动_02月03日-02月17日"+DateTimeUtil.toString(DateTimeUtil.YYYYMMDDHHMM);
			}
			
			// 设置response方式,使执行此controller时候自动出现下载页面,而非直接使用excel打开
			response.setContentType("APPLICATION/OCTET-STREAM");
			response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(excelName+".xls", "UTF-8"));
			os = response.getOutputStream();
			// 创建工作薄 // 创建第一个工作表
			excel.create(os);
			if("1".equals(sign)){
				ActivityExcel.export1(excel, list);
			}else if("2".equals(sign)){
				ActivityExcel.export2(excel, list);	
			}else if("6".equals(sign)){
				ActivityExcel.export6(excel, list);	
			}else if("29".equals(sign)){
				ActivityExcel.export29(excel, list);	
			}else if("30".equals(sign)){
				ActivityExcel.export30(excel, list);	
			}else if("31".equals(sign)){
				ActivityExcel.export31(excel, list);	
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 写入文件
			try {
				excel.writeAndClose();
				os.flush();
				os.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	


}
