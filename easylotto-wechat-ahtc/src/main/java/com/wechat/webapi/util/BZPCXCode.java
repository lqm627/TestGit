package com.wechat.webapi.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wechat.webapi.web.controller.QController;

public class BZPCXCode {

	/*
	获取物流识别码接口
	@param pSerialCode20Bytes: 彩票物流码
	@param pAuthKey22Bytes: 22字节验证密钥④
	@return
	非零指针:转换得到的10字节物流识别码① 
	NULL:获取物流识别码失败
	*/
	private native String BZPGetLogisticalID(String pSerialCode20Bytes, String pAuthKey22Bytes);

	/*
	二维码数据真伪校验接口
	@param pLogisticalID10Bytes: 10字节物流识别码①
	@param pAuthCode12Bytes: 12字节验证码②
	@param pVerificationCode32Bytes: 32字节数据校验码③
	@param pAuthKey22Bytes: 22字节验证密钥④
	@return
	-1: 校验参数错误
	0: 数据校验失败
	1: 数据校验成功
	*/
	private native int BZPDataVerify(String pLogisticalID10Bytes, String pAuthCode12Bytes, String pVerificationCode32Bytes, String pAuthKey22Bytes);

	/*
	从二维码数据获取本票号信息接口
	@param pLogisticalID10Bytes: 10字节物流识别码①
	@param pAuthCode12Bytes: 12字节验证码②
	@param pVerificationCode32Bytes: 32字节数据校验码③
	@param pAuthKey22Bytes: 22字节验证密钥④
	@return
	非空: 10字节彩票流水号⑥
	空(NULL): 获取本票号信息失败
	*/
	private native String BZPGetSerialCode(String pLogisticalID10Bytes, String pAuthCode12Bytes, String pVerificationCode32Bytes, String pAuthKey22Bytes);


	
	protected static final Logger logger = LoggerFactory.getLogger(BZPCXCode.class);
	
	/**
	 * @param qrcodeValue 二维码值
	 * 以二维码数据 "http://xxxxxx.com011144589163115488513817"为例，
	 * 其活动代码为“01”，物流识别码为“1144589163”，验证码为"115488513817"。
	 * 彩票物流码为"0999-16001-0000001-100-3"，手工验证码为"115488513817"。
	 * 活动代码对应的批次代码为“099916001”，对应的后台数据为“099916001.JYSJ”。
	 * 该批次促销活动对应的验证秘钥为: "524349484b455658445153"。
	 * 与该物流识别码相应的后台校验数据为"1144589163dfab7e31b4fea871ee179d3706483b9f"。
	 *
	 * @return
	 */
	public static int check(String qrcodeValue){
		
		logger.info("\n二维码值：\n" + qrcodeValue);
		
		int iret = -1;
		String sret = "";
		String logisticalID = "";
		String authCode = "";
		
		if(null == qrcodeValue || "".equals(qrcodeValue) || 24 != qrcodeValue.length()){
			return iret;
		}
		String _sign = qrcodeValue.substring(0, 2);
		String _logisticsCode = qrcodeValue.substring(2, 12);
		String _authCode = qrcodeValue.substring(12);
		
		
		
		logisticalID = _logisticsCode;
		authCode = _authCode;
		
//		logisticalID = "1144589163";
//		authCode = "115488513817";
		
		String serialCode = "09991600100000011003";//"0999-16001-0000001-100-3"
		
		String verificationCode = "dfab7e31b4fea871ee179d3706483b9f";
		String authKey = "524349484b455658445153";
		
		BZPCXCode bzp = new BZPCXCode();

		//如果为手工输入彩票物流码和手工验证码参与活动，需要转换出物流识别码
		sret = bzp.BZPGetLogisticalID(serialCode, authKey);
		if(sret == null || sret.length() <= 0)
			logger.info("\n获取物流识别码失败！\n");
		else
			logger.info("\n物流识别码：" + sret + "\n");

		//验证是否为真实数据
		iret = bzp.BZPDataVerify(logisticalID, authCode, verificationCode, authKey);
		if(iret == 1)
			logger.info("\n数据校验成功。\n");
		else if(iret == 0)
			logger.info("\n数据校验失败！\n");
		else if(iret == -1)
			logger.info("\n输入参数错误！\n");

		//获取流水号
		sret = bzp.BZPGetSerialCode(logisticalID, authCode, verificationCode, authKey);
		if(sret == null || sret.length() <= 0)
			logger.info("\n获取流水号信息失败！\n\n");
		else
			logger.info("\n彩票流水号：" + sret + "\n\n");
		
		return iret;
	}
	//
	// 以二维码数据 "http://xxxxxx.com011144589163115488513817"为例，
	// 其活动代码为“01”，物流识别码为“1144589163”，验证码为"115488513817"。
	// 彩票物流码为"0999-16001-0000001-100-3"，手工验证码为"115488513817"。
	// 活动代码对应的批次代码为“099916001”，对应的后台数据为“099916001.JYSJ”。
	// 该批次促销活动对应的验证秘钥为: "524349484b455658445153"。
	// 与该物流识别码相应的后台校验数据为"1144589163dfab7e31b4fea871ee179d3706483b9f"。
	//
//	public static void main(String[] args)
//	{
//		BZPCXCode.check(null);
//		String value = "011144589163115488513817";
//		System.out.println(value.length());
//	}
	

	static
	{
//		String path = PathUtil.getTomcatClassesPath();
////		path = path.replace("file:", "");
		System.load("/usr/java/wechat/config/libBZPCXCode.so");
	}
}
