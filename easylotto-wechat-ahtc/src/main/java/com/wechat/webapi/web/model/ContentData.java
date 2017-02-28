package com.wechat.webapi.web.model;

import java.util.Date;

public class ContentData {
	
	private int id;
	private int intWechatType;//公众号账号
	private int intCategory;//文章分类
	private String vcTitle;//标题
	private String vcSummary;//概要描述
	private String vcImage;//封面图片
	private Date dtPublic;//发表时间
	private String vcAuthor;//作者
	private String vcContent;//内容
	private String vcOrginalUrl;//原文链接
	private int createUserId;	
	private int modifyUserId;
	private int state;
	private Date createDate;
	private String createDateStr;
	private Date modifyDate;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIntWechatType() {
		return intWechatType;
	}
	public void setIntWechatType(int intWechatType) {
		this.intWechatType = intWechatType;
	}
	public int getIntCategory() {
		return intCategory;
	}
	public void setIntCategory(int intCategory) {
		this.intCategory = intCategory;
	}
	public String getVcTitle() {
		return vcTitle;
	}
	public void setVcTitle(String vcTitle) {
		this.vcTitle = vcTitle;
	}
	public String getVcSummary() {
		return vcSummary;
	}
	public void setVcSummary(String vcSummary) {
		this.vcSummary = vcSummary;
	}
	public String getVcImage() {
		return vcImage;
	}
	public void setVcImage(String vcImage) {
		this.vcImage = vcImage;
	}
	public Date getDtPublic() {
		return dtPublic;
	}
	public void setDtPublic(Date dtPublic) {
		this.dtPublic = dtPublic;
	}
	public String getVcAuthor() {
		return vcAuthor;
	}
	public void setVcAuthor(String vcAuthor) {
		this.vcAuthor = vcAuthor;
	}
	public String getVcContent() {
		return vcContent;
	}
	public void setVcContent(String vcContent) {
		this.vcContent = vcContent;
	}
	public String getVcOrginalUrl() {
		return vcOrginalUrl;
	}
	public void setVcOrginalUrl(String vcOrginalUrl) {
		this.vcOrginalUrl = vcOrginalUrl;
	}
	public int getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(int createUserId) {
		this.createUserId = createUserId;
	}
	public int getModifyUserId() {
		return modifyUserId;
	}
	public void setModifyUserId(int modifyUserId) {
		this.modifyUserId = modifyUserId;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getCreateDateStr() {
		return createDateStr;
	}
	public void setCreateDateStr(String createDateStr) {
		this.createDateStr = createDateStr;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	
	
}
