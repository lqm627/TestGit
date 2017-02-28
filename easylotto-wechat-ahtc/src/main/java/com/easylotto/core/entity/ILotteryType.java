/**
 * 
 */
package com.easylotto.core.entity;



/**
 * @author CreateName: UpdateName:
 * @see QQ：
 * @see E-MAIL：
 * @see Function: TODO
 * @see ILotteryType
 * @see CreateDate: 2016年5月26日 下午2:46:32 UpdateDate: 2016年5月26日 下午2:46:32
 * @see Copyright
 * @since JDK1.7.*
 * @version 1.0
 */
public class ILotteryType {

	private String content;// 开奖结果无逗号分隔
	private String type;// 彩种类型
	private String name;
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


}
