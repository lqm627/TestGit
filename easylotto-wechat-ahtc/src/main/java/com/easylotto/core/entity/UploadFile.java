package com.easylotto.core.entity;


import java.io.File;

import com.easylotto.core.util.PathUtil;



public class UploadFile {

	private String suffix;//jpg
	private String name;//1.jpg 上传文件名
	private String path;//    upload/file/201505/1.jpg
	private Long size;//文件大小
	private String fileName; //保存文件名
	private String inputName;
	private boolean uploadSuccess = false;
	
	public boolean isUploadSuccess() {
		return uploadSuccess;
	}
	public void setUploadSuccess(boolean uploadSuccess) {
		this.uploadSuccess = uploadSuccess;
	}
	public String getInputName() {
		return inputName;
	}
	public void setInputName(String inputName) {
		this.inputName = inputName;
	}
	public String getSuffix() {
		return suffix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	public Long getSize() {
		return size;
	}
	public void setSize(Long size) {
		this.size = size;
	}
	
	public void delete(){
		File file = new File(File.separator + PathUtil.getTomcatWebRootPath() + getPath());
		if(file.exists()){
			file.delete();
		}
	}
	
	public String getFileName() {
		return fileName;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
}
