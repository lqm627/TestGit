package com.easylotto.core.util;

import java.io.File;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * 
 * @author CreateName: chenrixiang UpdateName: chenrixiang
 * @see QQ：752540797
 * @see E-MAIL：ri.xiang.c@gmail.com
 * @see Function: 系统路径
 * @see PathUtil
 * @see CreateDate: 2015年7月14日 上午9:42:25 UpdateDate: 2015年7月14日 上午9:42:25
 * @see Copyright YCL
 * @since JDK1.7.*
 * @version 1.0
 */
public class PathUtil {
	
	protected static Configuration config;
	
	static {
//		try {
//			config = new PropertiesConfiguration("com/macauslot/cms/other/upload/path.properties");
//		} catch (ConfigurationException e) {
//			e.printStackTrace();
//		} 
	}
	
	public static String getPath(String key, String... defaultPath){
		if(config.containsKey(key)){
			return config.getString(key);
		}else if(null != defaultPath && 0 < defaultPath.length ){
			return defaultPath[0];
		}
		return "";
	}
	
	/**
	 * 获取WEB-INF/classes/
	 * @return
	 */
	public static String getTomcatClassesPath(){
		return Thread.currentThread().getContextClassLoader().getResource("").toString().split("file:/")[1];
	}
	
	/**
	 * 获取WebRoot
	 * @return
	 */
	public static String getTomcatWebRootPath(){
		return getTomcatClassesPath().split("WEB-INF/classes/")[0];
	}
	
	/**
	 * 获取WEB-INF
	 * @return
	 */
	public static String getTomcatWEBINFPath(){
		return getTomcatClassesPath().split("classes/")[0];
	}
	
	/**
	 * 获取项目名
	 * @return
	 */
	public static String getProjectName(){
		String[] str = System.getProperty("user.dir").split("\\\\");
		return str[str.length-1];
	}
	
	/**
	 * 处理系统路径
	 * 
	 */
	public static String replace(String path) {
	  //windows下
	  if("\\".equals(File.separator)){   
		  path = path.replace("/", "\\");
	  }
	  //linux下
	  if("/".equals(File.separator)){   
		  path = path.replace("\\", "/");
	  }
	  return path;
	}

	
	/**
	 * 将文件从临时文件夹移到正式文件夹 (wucx)
	 * @param tempPath 临时文件的保存路径
	 * @param pathKey 对应path.properties文件里面的key
	 */
	public static String renameTo(String tempPath, String pathKey){
		File tempFile=new File(File.separator+PathUtil.getTomcatWebRootPath()+tempPath);
		if(tempFile.exists()){
			File saveFile=new File(File.separator+PathUtil.getTomcatWebRootPath()+PathUtil.getPath(pathKey));
			if(!saveFile.exists() && !saveFile.isDirectory()){//文件夹不存在则创建
				saveFile.mkdir();
			}
			
			String realPath=PathUtil.getPath(pathKey)+tempFile.getName();
			if(tempFile.renameTo(new File(File.separator+PathUtil.getTomcatWebRootPath()+realPath))){
				return realPath;
			}
		}
		return tempPath;//移动不成功就直接返回临时保存路径
	}
	
	/**
	 * 删除图片上传的临时文件
	 * @param tempPath
	 */
	public static void deleteTempFile(String tempPath){
		File tempFile=new File(File.separator+PathUtil.getTomcatWebRootPath()+tempPath);
		if(tempFile.exists()){
			tempFile.delete();
		}
	}
}
