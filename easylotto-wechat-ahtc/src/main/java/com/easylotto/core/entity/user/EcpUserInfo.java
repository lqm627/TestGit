package com.easylotto.core.entity.user;

import java.util.Date;

import javax.persistence.Table;

@Table(name="ecp_user_info")
public class EcpUserInfo extends AbstractEcpUserInfo implements java.io.Serializable {

	public EcpUserInfo() {
	}

	public EcpUserInfo(Long intAccountId, String vcName, String vcNickname,String vcSex,
			Date dtBirthdate, String vcUserAddress, String vcEmail,
			String vcMobile, String vcMemo, String vcRecomName,
			Date dtRecomTime, Long intVerify, Long intSafe, Long intRecomType,
			String vcCardNo, Long intAwardNotify, Long intBankType,
			String vcOpenBank, String vcBankAccount, String vcBandProvince,
			String vcBandCity, String vcPswQuestion, String vcPswAnswer,
			String vcUserProvince, String vcUserCity, String vcZip,
			String vcLanguage,String vcHeadimgurl,Date dtSubTime,Integer intSubStatus,String vcOpenId, String vcOsName, Integer intWechatType) {
		super(intAccountId, vcName, vcSex, vcNickname, dtBirthdate, vcUserAddress, vcEmail,
				vcMobile, vcMemo, vcRecomName, dtRecomTime, intVerify, intSafe,
				intRecomType, vcCardNo, intAwardNotify, intBankType, vcOpenBank,
				vcBankAccount, vcBandProvince, vcBandCity, vcPswQuestion, vcPswAnswer,
				vcUserProvince, vcUserCity, vcZip, vcHeadimgurl, vcHeadimgurl, dtSubTime, intSubStatus, vcOpenId,vcOsName,intWechatType);
	}

	public EcpUserInfo(Long intAccountId) {
		super(intAccountId);
	}

}
