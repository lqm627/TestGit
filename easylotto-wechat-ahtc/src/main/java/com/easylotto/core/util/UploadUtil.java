package com.easylotto.core.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.easylotto.core.entity.UploadFile;



public class UploadUtil {

	public static final int BUF_SIZE = 2 * 1024;
	/**
	 * 
	 * @param req
	 * @param suffix "|jpg|png|"
	 * @param path "upload/file/201505/"
	 * @return
	 */
	public synchronized static List<UploadFile> uploads(String suffix, String path,
			HttpServletRequest request) {
		return uploads(suffix, path, request, "");
	}
	
	/**
	 * 
	 * @param req
	 * @param suffix "|jpg|png|"
	 * @param path "upload/file/201505/"
	 * @return
	 */
	public synchronized static List<UploadFile> uploads(String suffix, String path,
			HttpServletRequest request, String name) {
		try {
			//创建一个通用的多部分解析器  
			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
			//判断 request 是否有文件上传,即多部分请求  
			if(multipartResolver.isMultipart(request)){
			    //转换成多部分request    
			    MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;  
			    List<UploadFile> list = new ArrayList<UploadFile>(0);
			    //取得request中的所有文件名  
			    Iterator<String> iter = multiRequest.getFileNames();
			    UploadFile file = null;
				String fileName = null;
				String fileSuffix = null;
				String[] _temp = null;
//				String road ="/".equals(File.separator) ? "/" + PathUtil.getTomcatWebRootPath() : "" ;
			    while(iter.hasNext()){  
			    	file = new UploadFile();
//			        //记录上传过程起始时的时间，用来计算上传时间  
			    	try {
				        int pre = (int) System.currentTimeMillis();  
				        //取得上传文件  
				        MultipartFile multipartFile = multiRequest.getFile(iter.next());  
				        if(null != multipartFile){  
					        Double MaxSize=8192d;
					        Double picSize=(Double.valueOf(multipartFile.getSize())/1024);
					        if(picSize>MaxSize){
					        	return null;
					        }else{
					            //取得当前上传文件的文件名称  
					        	fileName = multipartFile.getOriginalFilename().trim();  
					            //如果名称不为“”,说明该文件存在，否则说明该文件不存在  
					            if(StringUtils.isNotBlank(fileName)){
					            	
						        	file.setInputName(multipartFile.getName());
									_temp = fileName.split("\\.");
									if(0 == _temp.length){
										continue;
									}
									fileSuffix = _temp[_temp.length - 1];
									if (StringUtils.isNotEmpty(suffix) && !suffix.contains("|" + fileSuffix.toLowerCase() + "|")) {// 检测文件后缀
										continue;
									}
									File dstFile = new File(File.separator  + path);
									if (!dstFile.exists()) {
										dstFile.mkdirs();
									}
									file.setSize(multipartFile.getSize());
									file.setSuffix(fileSuffix);
									
									file.setName(fileName.substring(0, fileName.lastIndexOf(".")));
					                //重命名上传后的文件名  
					                //定义上传路径  
					                String _path = "";
					                
					                if(StringUtils.isNotBlank(name)){
					                	Thread.sleep(10);
					                	_path = path + name + "." + fileSuffix;
					                	file.setFileName(name + "." + fileSuffix);
					                }else{
					                	Thread.sleep(10);
					                	name = System.currentTimeMillis()+"";
					                	_path = path + name + "." + fileSuffix;
					                	file.setFileName(name + "." + fileSuffix);
					                	name = "";
					                }  
					              
					                file.setPath(_path);
					                File localFile = new File(File.separator  + _path);
					                multipartFile.transferTo(localFile);
					                file.setUploadSuccess(true);
					                list.add(file);
					            } 
					        }
 
				        }  
//				        //记录上传该文件后的时间  
				        int finaltime = (int) System.currentTimeMillis();  
				        System.out.println(finaltime - pre);  						
					} catch (Exception e) {
						 file.setUploadSuccess(false);
			             list.add(file);
					}
			    }  
			    return list;
			}
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @param req
	 * @param suffix "|jpg|png|"
	 * @param path "upload/file/201505/"
	 * @return
	 */
	public static UploadFile upload(HttpServletRequest req, String suffix,
			String path) {
		return upload(req, suffix, path, "");
	}
	

	/**
	 * 
	 * @param req
	 * @param suffix "|jpg|png|"
	 * @param path "upload/file/201505/"
	 * @return
	 */
	public static UploadFile upload(HttpServletRequest req, String suffix,
			String path, String name) {
		List<UploadFile> list = uploads(suffix, path, req, name);
		if(null != list && 0 < list.size()){
			return list.get(0);
		}
		return null;
	}

	private static void saveFile(InputStream input, File dst)
			throws IOException {
		OutputStream out = null;
		try {
			if (dst.exists()) {
				out = new BufferedOutputStream(new FileOutputStream(dst, false), BUF_SIZE);
			} else {
				out = new BufferedOutputStream(new FileOutputStream(dst), BUF_SIZE);
			}
			byte[] buffer = new byte[BUF_SIZE];
			int len = 0;
			while ((len = input.read(buffer)) > 0) {
				out.write(buffer, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != input) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != out) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 文件上传
	 * @param file
	 * @param fileName
	 * @param bean
	 */
	public  static String upload(File file, String fileName){
		InputStream is =  null;
		File fileLast=null;
		try {
			is = new FileInputStream(file);
			String path = "/".equals(File.separator) ? "/" + PathUtil.getTomcatWebRootPath() : "" ;
			File parentFile = new File(path + File.separator + "upload");
			
			File descFile = new File(parentFile.getPath(), fileName);
			if(!parentFile.exists()){
				parentFile.mkdirs();
			}
			descFile.setReadable(true);//设置可读权限
			descFile.setWritable(true);//设置可写权限
			OutputStream os=new FileOutputStream(descFile);
			byte[] buffer=new byte[1024];
			int length=0;

			while(-1!=(length=(is.read(buffer))))
			{
				os.write(buffer, 0, length);
			}
			is.close();
			os.close();
			
			path = "/var/www/html" ;//E:/
			parentFile = new File(path);
			if(file.exists()){
				parentFile = new File(path + File.separator + "upload");
				if(!parentFile.exists()){
					parentFile.mkdir();
				}
				File copyFile = new File(parentFile.getPath(), fileName);
				fileLast=copyFile;
				if(!copyFile.exists()){
					FileUtils.copyFile(descFile, copyFile);
				}
				copyFile.setReadable(true);//设置可读权限
				copyFile.setWritable(true);//设置可写权限
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			file.deleteOnExit();
		}
		return fileLast.toString();
	}

//	public static void main(String[] args) {
//		System.out.println("s.ss.jpg".substring(0, "s.ss.jpg".lastIndexOf(".")));
//	}
}
