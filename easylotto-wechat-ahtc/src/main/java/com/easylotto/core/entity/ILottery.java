/**
 * 
 */
package com.easylotto.core.entity;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.RuntimeErrorException;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;

import com.easylotto.core.common.Constant;

/**
 * @author CreateName: UpdateName:
 * @see QQ：
 * @see E-MAIL：
 * @see Function: TODO
 * @see ILottery
 * @see CreateDate: 2016年5月26日 下午2:46:32 UpdateDate: 2016年5月26日 下午2:46:32
 * @see Copyright
 * @since JDK1.7.*
 * @version 1.0
 */
public class ILottery {

	private String term;// 彩期
	private String termNum;// 期数
	private String result;// 开奖结果 逗号分隔
	private String content;// 开奖结果无逗号分隔
	private String type;// 彩种类型
	private String[] preCodes;
	private String[] pstCodes;
	//6+1需要用到
	private String[] preCodesTwo;
	private String[] preCodesThird;
	private String[] preCodesFour;
	private String[] preCodesFive;
	private String[] preCodesSix;
	//
	private String[] codes;
	private Date open_time;
	private String preContent;
	private String pstContent;
	private String sizeThan;
	private String oddevenThan;
	//6+1需要用到
	private String preContentTwo;
	private String preContentThird;
	private String preContentFour;
	private String preContentFive;
	private String preContentSix;
	private String id;
	private String name;

	Map<String, String> data;

//	List<ILeaveout> leaveoutData;
//	List<ILeaveout> leaveoutOddeven;
//	List<ILeaveout> leaveoutSize;
//	List<ILeaveout> leaveoutShape;
//	List<ILeaveout> leaveoutSpan;
//	List<ILeaveout> leaveoutParts;
	
	
	
	String prize_content;
	String pool_award;
	
	String firstCount;
	String firstPrize;
	
	String secCount;
	String secPrize;
	
	String thirdCount;
	String thirdPrize;
	
	String forthCount;
	String forthPrize;
	
	String fifthCount;
	String fifthPrize;
	
	String sixCount;
	String sixPrize;
	
	//七乐彩需要用到
	String seventhCount;
	String seventhPrize;
	//
	
	//3D需要用到
	String zhxCount;
	String zhxPrize;
	
	String zx3Count;
	String zx3Prize;
	
	String zx6Count;
	String zx6Prize;
	//
	
	//15选5用到
	String tdCount;
	String tdPrize;

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	@Transient
	public void setTerm() {
		String termNum = term;
		if ("3".equals(type) && 7 == codes.length) {
			termNum = StringUtils.substring(termNum, 2);
		} else if ("27".equals(type) && 3 == codes.length) {
			termNum = StringUtils.substring(termNum, 2);
		}else if("24".equals(type)&& 8 == codes.length){
			termNum = StringUtils.substring(termNum, 2);
		}else if("25".equals(type)&& 5==codes.length){
			termNum = StringUtils.substring(termNum, 2);
		}else if("20".equals(type)&& 5==codes.length){
			termNum = StringUtils.substring(termNum, 2);
		}else if("26".equals(type)&& 7==codes.length){
			termNum = StringUtils.substring(termNum, 2);
		}else if("12".equals(type)&& 3==codes.length){
			termNum = StringUtils.substring(termNum, 2);
		}
		this.termNum = termNum;
	}

	public String getResult() {
		return result;
	}

	@Transient
	public void setResult() {
//		if (StringUtils.isNotBlank(result)) {
//			String[] codes = StringUtils.split(result, ",");
//			if (Constant.SSQ.equals(type) && 7 == codes.length) {
//				setCodes(codes);
//				setPreContent(StringUtils.substring(result, 0, 17));
//				setPstContent(StringUtils.substring(result, 18));
//			}else if(Constant.QLC.equals(type)&& 8 == codes.length){
//				setCodes(codes);
//				setPreContent(StringUtils.substring(result, 0, 20));
//				setPstContent(StringUtils.substring(result, 21));
//			} else if(Constant.FC_25X5.equals(type)&& 5 == codes.length){
//				setCodes(codes);
//				setPreContent(result);
//			} else if(Constant.FC_15X5.equals(type)&& 5 == codes.length){
//				setCodes(codes);
//				setPreContent(result);
//			} else if(Constant.FC_6Plus1.equals(type)&& 7 == codes.length){
//				setCodes(codes);
//				setPreContent(StringUtils.substring(result, 0, 1));
//				setPreContentTwo(StringUtils.substring(result, 2, 3));
//				setPreContentThird(StringUtils.substring(result, 4, 5));
//				setPreContentFour(StringUtils.substring(result, 6, 7));
//				setPreContentFive(StringUtils.substring(result, 8, 9));
//				setPreContentSix(StringUtils.substring(result, 10, 11));
//				setPstContent(StringUtils.substring(result, 12));
//			} else if(Constant.FC_3D.equals(type)&& 3 == codes.length){
//				setCodes(codes);
//				setPreContent(StringUtils.substring(result, 0, 1));
//				setPreContentTwo(StringUtils.substring(result, 2, 3));
//				setPstContent(StringUtils.substring(result, 4));
//			}else if (Constant.K3.equals(type) && 3 == codes.length) {
//				setPreCodes(codes);
//				setCodes(codes);
//				setContent(StringUtils.replace(result, ",", ""));
//
//				Map<String, String> data = new HashMap<String, String>(0);
//				Integer sumValue = 0;
//				Integer subValue = 0;
//				for (String code : codes) {
//					sumValue += Integer.parseInt(code);
//				}
//
//				subValue = Integer.parseInt(codes[2]) - Integer.parseInt(codes[0]);
//
//				data.put("sumValue", "" + sumValue);
//				data.put("subValue", "" + subValue);
//				this.setData(data);
//			} else {
//				throw new RuntimeErrorException(null, "请定义... ");
//			}
//		}
	}
	

	public void setResult(String result) {
		this.result = result;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String[] getPreCodes() {
		return preCodes;
	}

	public void setPreCodes(String[] preCodes) {
		this.preCodes = preCodes;
	}

	public String[] getPstCodes() {
		return pstCodes;
	}

	public void setPstCodes(String[] pstCodes) {
		this.pstCodes = pstCodes;
	}

	public String[] getCodes() {
		return codes;
	}

	public void setCodes(String[] codes) {
		this.codes = codes;
	}

	public String getTermNum() {
		return termNum;
	}

	public void setTermNum(String termNum) {
		this.termNum = termNum;
	}

	public Map<String, String> getData() {
		return data;
	}

	public void setData(Map<String, String> data) {
		this.data = data;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getOpen_time() {
		return open_time;
	}

	public void setOpen_time(Date open_time) {
		this.open_time = open_time;
	}

//	public List<ILeaveout> getLeaveoutData() {
//		return leaveoutData;
//	}
//
//	public void setLeaveoutData(List<ILeaveout> leaveoutData) {
//		this.leaveoutData = leaveoutData;
//	}
//
//	public List<ILeaveout> getLeaveoutOddeven() {
//		return leaveoutOddeven;
//	}
//
//	public void setLeaveoutOddeven(List<ILeaveout> leaveoutOddeven) {
//		this.leaveoutOddeven = leaveoutOddeven;
//	}
//
//	public List<ILeaveout> getLeaveoutSize() {
//		return leaveoutSize;
//	}
//
//	public void setLeaveoutSize(List<ILeaveout> leaveoutSize) {
//		this.leaveoutSize = leaveoutSize;
//	}
//
//	public List<ILeaveout> getLeaveoutShape() {
//		return leaveoutShape;
//	}
//
//	public void setLeaveoutShape(List<ILeaveout> leaveoutShape) {
//		this.leaveoutShape = leaveoutShape;
//	}
//
//	public List<ILeaveout> getLeaveoutSpan() {
//		return leaveoutSpan;
//	}
//
//	public void setLeaveoutSpan(List<ILeaveout> leaveoutSpan) {
//		this.leaveoutSpan = leaveoutSpan;
//	}
//
//	public List<ILeaveout> getLeaveoutParts() {
//		return leaveoutParts;
//	}
//
//	public void setLeaveoutParts(List<ILeaveout> leaveoutParts) {
//		this.leaveoutParts = leaveoutParts;
//	}

	public String getPreContent() {
		return preContent;
	}

	public void setPreContent(String preContent) {
		this.preContent = preContent;
	}

	public String getPstContent() {
		return pstContent;
	}

	public void setPstContent(String pstContent) {
		this.pstContent = pstContent;
	}

	public String getPrize_content() {
		return prize_content;
	}

	public void setPrize_content(String prize_content) {
		this.prize_content = prize_content;
	}

	public String getPool_award() {
		return pool_award;
	}

	public void setPool_award(String pool_award) {
		this.pool_award = pool_award;
	}

	public String getFirstCount() {
		return firstCount;
	}

	public void setFirstCount(String firstCount) {
		this.firstCount = firstCount;
	}

	public String getFirstPrize() {
		return firstPrize;
	}

	public void setFirstPrize(String firstPrize) {
		this.firstPrize = firstPrize;
	}

	public String getSecCount() {
		return secCount;
	}

	public void setSecCount(String secCount) {
		this.secCount = secCount;
	}

	public String getSecPrize() {
		return secPrize;
	}

	public void setSecPrize(String secPrize) {
		this.secPrize = secPrize;
	}

	public String getThirdCount() {
		return thirdCount;
	}

	public void setThirdCount(String thirdCount) {
		this.thirdCount = thirdCount;
	}

	public String getThirdPrize() {
		return thirdPrize;
	}

	public void setThirdPrize(String thirdPrize) {
		this.thirdPrize = thirdPrize;
	}

	public String getForthCount() {
		return forthCount;
	}

	public void setForthCount(String forthCount) {
		this.forthCount = forthCount;
	}

	public String getForthPrize() {
		return forthPrize;
	}

	public void setForthPrize(String forthPrize) {
		this.forthPrize = forthPrize;
	}

	public String getFifthCount() {
		return fifthCount;
	}

	public void setFifthCount(String fifthCount) {
		this.fifthCount = fifthCount;
	}

	public String getFifthPrize() {
		return fifthPrize;
	}

	public void setFifthPrize(String fifthPrize) {
		this.fifthPrize = fifthPrize;
	}

	public String getSixCount() {
		return sixCount;
	}

	public void setSixCount(String sixCount) {
		this.sixCount = sixCount;
	}

	public String getSixPrize() {
		return sixPrize;
	}

	public void setSixPrize(String sixPrize) {
		this.sixPrize = sixPrize;
	}

	public String getSeventhCount() {
		return seventhCount;
	}

	public void setSeventhCount(String seventhCount) {
		this.seventhCount = seventhCount;
	}

	public String getSeventhPrize() {
		return seventhPrize;
	}

	public void setSeventhPrize(String seventhPrize) {
		this.seventhPrize = seventhPrize;
	}

	public String getZhxCount() {
		return zhxCount;
	}

	public void setZhxCount(String zhxCount) {
		this.zhxCount = zhxCount;
	}

	public String getZhxPrize() {
		return zhxPrize;
	}

	public void setZhxPrize(String zhxPrize) {
		this.zhxPrize = zhxPrize;
	}

	public String getZx3Count() {
		return zx3Count;
	}

	public void setZx3Count(String zx3Count) {
		this.zx3Count = zx3Count;
	}

	public String getZx3Prize() {
		return zx3Prize;
	}

	public void setZx3Prize(String zx3Prize) {
		this.zx3Prize = zx3Prize;
	}

	public String getZx6Count() {
		return zx6Count;
	}

	public void setZx6Count(String zx6Count) {
		this.zx6Count = zx6Count;
	}

	public String getZx6Prize() {
		return zx6Prize;
	}

	public void setZx6Prize(String zx6Prize) {
		this.zx6Prize = zx6Prize;
	}

	public String getTdCount() {
		return tdCount;
	}

	public void setTdCount(String tdCount) {
		this.tdCount = tdCount;
	}

	public String getTdPrize() {
		return tdPrize;
	}

	public void setTdPrize(String tdPrize) {
		this.tdPrize = tdPrize;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String[] getPreCodesTwo() {
		return preCodesTwo;
	}

	public void setPreCodesTwo(String[] preCodesTwo) {
		this.preCodesTwo = preCodesTwo;
	}

	public String[] getPreCodesThird() {
		return preCodesThird;
	}

	public void setPreCodesThird(String[] preCodesThird) {
		this.preCodesThird = preCodesThird;
	}

	public String[] getPreCodesFour() {
		return preCodesFour;
	}

	public void setPreCodesFour(String[] preCodesFour) {
		this.preCodesFour = preCodesFour;
	}

	public String[] getPreCodesFive() {
		return preCodesFive;
	}

	public void setPreCodesFive(String[] preCodesFive) {
		this.preCodesFive = preCodesFive;
	}

	public String[] getPreCodesSix() {
		return preCodesSix;
	}

	public void setPreCodesSix(String[] preCodesSix) {
		this.preCodesSix = preCodesSix;
	}

	public String getPreContentTwo() {
		return preContentTwo;
	}

	public void setPreContentTwo(String preContentTwo) {
		this.preContentTwo = preContentTwo;
	}

	public String getPreContentThird() {
		return preContentThird;
	}

	public void setPreContentThird(String preContentThird) {
		this.preContentThird = preContentThird;
	}

	public String getPreContentFour() {
		return preContentFour;
	}

	public void setPreContentFour(String preContentFour) {
		this.preContentFour = preContentFour;
	}

	public String getPreContentFive() {
		return preContentFive;
	}

	public void setPreContentFive(String preContentFive) {
		this.preContentFive = preContentFive;
	}

	public String getPreContentSix() {
		return preContentSix;
	}

	public void setPreContentSix(String preContentSix) {
		this.preContentSix = preContentSix;
	}

	public String getSizeThan() {
		return sizeThan;
	}

	public void setSizeThan(String sizeThan) {
		this.sizeThan = sizeThan;
	}

	public String getOddevenThan() {
		return oddevenThan;
	}

	public void setOddevenThan(String oddevenThan) {
		this.oddevenThan = oddevenThan;
	}
	
	

}
