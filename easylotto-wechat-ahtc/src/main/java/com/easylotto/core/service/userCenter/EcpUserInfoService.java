package com.easylotto.core.service.userCenter;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.easylotto.commons.util.JedisUtil;
import com.easylotto.core.dao.EcpUserInfoDao;
import com.easylotto.core.entity.user.EcpUser;
import com.easylotto.core.entity.user.EcpUserFundLog;
import com.easylotto.core.entity.user.EcpUserInfo;
import com.easylotto.core.entity.user.EcpUserKey;

import me.chanjar.weixin.mp.bean.result.WxMpUser;

@Service
@Transactional
public class EcpUserInfoService {

	private final Logger logger = LogManager.getLogger(EcpUserInfoService.class);

	@Autowired
	private EcpUserInfoDao ecpUserInfoDao;

	@Autowired
	private JedisUtil jedisTemplate;

	@Deprecated
	public int save(WxMpUser user) {
		return this.ecpUserInfoDao.save(user);
	}

	public int save(WxMpUser user, int type) {
		return this.ecpUserInfoDao.save(user, type);
	}

	public String createUserKey(EcpUserInfo bean) {
		String key = (System.currentTimeMillis() + RandomUtils.nextLong()) + "";
		createUserKey(bean, key);
		return key;
	}

	public void createUserKey(EcpUserInfo bean, String key) {
		this.ecpUserInfoDao.create(bean, key);
		logger.info(" --------------------------------------->>>  REDIS USER_INFO: " + key);
		jedisTemplate.set("USER_INFO:" + key, JSON.toJSONString(bean), 60 * 60, TimeUnit.SECONDS);
	}

	public boolean createUserKey(String openId, String key, String type) {
		EcpUserInfo bean = findByOpenId(openId, type);
		createUserKey(bean, key);
		return null == bean;
	}

	public boolean createUserKey(EcpUserInfo bean, String openId, String key) {
		createUserKey(bean, key);
		return null == bean;
	}

	public void addEcpUser(final EcpUserInfo ecpUserInfo) {
		String sql = "insert into ecp_user(vc_account,int_account_id,vc_open_id,dec_lottery_bean) values(?,?,?,?)";
		double beans = 100;
		Object[] args = { ecpUserInfo.getVc_nickname(), ecpUserInfo.getInt_account_id(), ecpUserInfo.getVc_open_id(),
				beans };
		this.ecpUserInfoDao.update(sql, args);
	}

	public EcpUserInfo findByOpenId(String openId, String type) {
		EcpUserInfo ecpUserInfo = null;
		try {
			String sql = "select * from ecp_user_info where vc_open_id=? AND INT_WECHAT_TYPE=?";

			Object[] args = { openId, type };
			List<EcpUserInfo> list = this.ecpUserInfoDao.find(sql, args);
			if (list != null && list.size() > 0) {
				ecpUserInfo = list.get(0);
				return ecpUserInfo;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public EcpUser findEcpUserById(long id) {
		EcpUser ecpUser = null;
		String sql = "select * from ecp_user where int_account_id=?";
		List<EcpUser> list = this.ecpUserInfoDao.query(sql, new Object[] { id });

		if (list != null && list.size() > 0) {
			ecpUser = list.get(0);
			return ecpUser;
		} else {
			return null;
		}
	}

	public EcpUserKey findBy(String key, int type) {
		EcpUserKey bean = find(key);
		if (null != bean)
			return bean;
		bean = this.ecpUserInfoDao.findBy(key, type);
		if (null != bean) {
			EcpUserInfo userInfo = this.findByOpenId(bean.getOpen_id(), String.valueOf(type));
			jedisTemplate.set("USER_INFO:" + key, JSON.toJSONString(userInfo), 60 * 60, TimeUnit.SECONDS);
		}
		return bean;
	}

	public EcpUserKey getByOpenId(String openid, int type) {
		return this.ecpUserInfoDao.findByOpenId(openid, type);
	}

	public void delete() {
		ecpUserInfoDao.delete();
	}

	public EcpUserKey find(String key) {
		if (jedisTemplate.exists("USER_INFO:" + key)) {
			String value = jedisTemplate.get("USER_INFO:" + key);
			EcpUserInfo ecpUserInfo = JSON.parseObject(value, EcpUserInfo.class);
			EcpUserKey bean = new EcpUserKey(ecpUserInfo.getInt_account_id(), ecpUserInfo.getVc_open_id(), key);
			if (logger.isDebugEnabled()) {
				logger.debug("  --------------->   REDIS 命中 key={" + key + "}, state={" + (null != bean) + "}");
			}
			return bean;
		}
		return null;
	}

	public void updateUserOS(Long id, String osName) {
		ecpUserInfoDao.updateUserOS(id, osName);
	}
	
	/**
     * 替换四个字节的字符 '\xF0\x9F\x98\x84\xF0\x9F）的解决方案 😁
     * @author ChenGuiYong
     * @data 2015年8月11日 上午10:31:50
     * @param content
     * @return
     */
    public static String removeFourChar(String content) {
        byte[] conbyte = content.getBytes();
        for (int i = 0; i < conbyte.length; i++) {
            if ((conbyte[i] & 0xF8) == 0xF0) {
                for (int j = 0; j < 4; j++) {                          
                    conbyte[i+j]=0x30;                     
                }  
                i += 3;
            }
        }
        content = new String(conbyte);
        return content.replaceAll("0000", "");
    }

	public void updateUserInfo(EcpUserInfo userInfo, int accountId) {
		
		try {
			if(StringUtils.isNotBlank(userInfo.getVc_nickname())){
				userInfo.setVc_nickname(removeFourChar(userInfo.getVc_nickname()));
			}
			
			if(StringUtils.isNotBlank(userInfo.getVc_name())){
				userInfo.setVc_name(removeFourChar(userInfo.getVc_name()));
			}
		} catch (Exception e) {
			logger.error("", e);
		}
		ecpUserInfoDao.updateUserInfo(userInfo, accountId);
	}

	public void updateUserInfoByWX(final WxMpUser user, final Long accountId) {
		try {
			if(StringUtils.isNotBlank(user.getNickname())){
				user.setNickname(removeFourChar(user.getNickname()));
			}
		} catch (Exception e) {
			logger.error("", e);
		}
		ecpUserInfoDao.updateUserInfoByWX(user, accountId);
	}

	public String getAccountId(String openId) {
		return ecpUserInfoDao.getAccountId(openId);
	}

	public void updateUserStatus(long accountId) {
		ecpUserInfoDao.updateUserStatus(accountId);
	}

	public void addUnsubLog(EcpUserFundLog ecpUserFundLog) {
		ecpUserInfoDao.addUnsubLog(ecpUserFundLog);
	}

}
